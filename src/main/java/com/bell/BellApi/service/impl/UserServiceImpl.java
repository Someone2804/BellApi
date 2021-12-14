package com.bell.BellApi.service.impl;

import com.bell.BellApi.dao.CountryDao;
import com.bell.BellApi.dao.DocumentDao;
import com.bell.BellApi.dao.DocumentNameDao;
import com.bell.BellApi.dao.OfficeDao;
import com.bell.BellApi.dao.PositionDao;
import com.bell.BellApi.dao.UserDao;
import com.bell.BellApi.dto.filter.UserFilter;
import com.bell.BellApi.dto.user.request.UserRequest;
import com.bell.BellApi.dto.user.response.UserDtoAll;
import com.bell.BellApi.dto.user.response.UserDtoId;
import com.bell.BellApi.model.Document;
import com.bell.BellApi.model.User;
import com.bell.BellApi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserDao userDao;
    private final OfficeDao officeDao;
    private final DocumentDao documentDao;
    private final DocumentNameDao documentNameDao;
    private final CountryDao countryDao;
    private final PositionDao positionDao;

    @Autowired
    public UserServiceImpl(UserDao userDao, OfficeDao officeDao, DocumentDao documentDao, DocumentNameDao documentNameDao, CountryDao countryDao, PositionDao positionDao) {
        this.userDao = userDao;
        this.officeDao = officeDao;
        this.documentDao = documentDao;
        this.documentNameDao = documentNameDao;
        this.countryDao = countryDao;
        this.positionDao = positionDao;
    }

    @Transactional(transactionManager = "jpa")
    @Override
    public void add(UserRequest user) {
        user.validateForSave();
        User result = new User();
        user.fillUser(result);
        result.setOffice(officeDao.getReference(user.getOfficeId()));
        result.addPosition(positionDao.getByName(user.getPosition()));
        if(user.validateCitizenship()){
            result.setCitizenship(countryDao.getByCode(user.getCitizenshipCode()));
        }
        if(user.validateDocument()) {
            Document document = new Document();
            document.setDocumentName(documentNameDao.getByNameAndCode(user.getDocName(), user.getDocCode()));
            user.fillDocument(document);
            document.setUser(result);
            documentDao.save(document);
        }
        userDao.save(result);
    }


    @Transactional(transactionManager = "jpa")
    @Override
    public void update(UserRequest user) {

    }

    @Transactional(transactionManager = "jpa")
    @Override
    public List<UserDtoAll> getAll(UserFilter filter) {
        filter.validate();
        return UserDtoAll.mapToDtoList(userDao.getAll(filter));
    }

    @Override
    public UserDtoId getById(Long id) {
        return userDao.getById(id);
    }
}
