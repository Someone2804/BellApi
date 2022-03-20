package com.bell.BellApi.service.impl;

import com.bell.BellApi.dao.CountryDao;
import com.bell.BellApi.dao.DocumentDao;
import com.bell.BellApi.dao.DocumentNameDao;
import com.bell.BellApi.dao.OfficeDao;
import com.bell.BellApi.dao.PositionDao;
import com.bell.BellApi.dao.RoleDao;
import com.bell.BellApi.dao.UserDao;
import com.bell.BellApi.dto.filter.UserFilter;
import com.bell.BellApi.dto.user.request.UserRequest;
import com.bell.BellApi.dto.user.response.UserDtoAll;
import com.bell.BellApi.dto.user.response.UserDtoId;
import com.bell.BellApi.model.Document;
import com.bell.BellApi.model.role.ERole;
import com.bell.BellApi.model.user.SecurityUser;
import com.bell.BellApi.model.user.User;
import com.bell.BellApi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * {@inheritDoc}
 */
@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserDao userDao;
    private final OfficeDao officeDao;
    private final DocumentDao documentDao;
    private final DocumentNameDao documentNameDao;
    private final CountryDao countryDao;
    private final PositionDao positionDao;
    private final RoleDao roleDao;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserDao userDao, OfficeDao officeDao, DocumentDao documentDao, DocumentNameDao documentNameDao, CountryDao countryDao, PositionDao positionDao, RoleDao roleDao, PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.officeDao = officeDao;
        this.documentDao = documentDao;
        this.documentNameDao = documentNameDao;
        this.countryDao = countryDao;
        this.positionDao = positionDao;
        this.roleDao = roleDao;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userDao.getByUsername(username);
    }

    /**
     * {@inheritDoc}
     */
    @Transactional
    @Override
    public void add(UserRequest user) {
        if(user == null){
            throw new IllegalArgumentException("UserRequest is null");
        }
        user.validateForSave();
        User result = UserRequest.mapToEntity(user);
        result.setSecurityUser(new SecurityUser(user.getUsername(), passwordEncoder.encode(user.getPassword())));
        result.getSecurityUser().setRole(roleDao.getByName(ERole.ROLE_USER));
        result.setOffice(officeDao.getReference(user.getOfficeId()));
        result.addPosition(positionDao.getByName(user.getPosition()));
        if(user.isCitizenshipExist()){
            result.setCitizenship(countryDao.getByCode(user.getCitizenshipCode()));
        }
        if(user.isDocumentExist()) {
            user.validateDocument();
            Document document = createDocument(user);
            document.setUser(result);
            documentDao.save(document);
        }
        userDao.save(result);
    }


    /**
     * {@inheritDoc}
     */
    @Transactional
    @Override
    public void update(UserRequest user) {
        if(user == null){
            throw new IllegalArgumentException("UserRequest is null");
        }
        user.validateForUpdate();
        User result = UserRequest.mapToEntity(user);
        if(user.getOfficeId() != null){
            result.setOffice(officeDao.getReference(user.getOfficeId()));
        }
        if(user.isCitizenshipExist()){
            result.setCitizenship(countryDao.getByCode(user.getCitizenshipCode()));
        }
        User fromDb = userDao.update(result);
        fromDb.addPosition(positionDao.getByName(user.getPosition()));
        if(user.isDocumentExist()) {
            user.validateDocument();
            Document document = createDocument(user);
            document.setUser(fromDb);
            document.setId(fromDb.getId());
            documentDao.update(document);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Transactional(readOnly = true)
    @Override
    public List<UserDtoAll> getAll(UserFilter filter) {
        if(filter == null){
            throw new IllegalArgumentException("UserFilter is null");
        }
        filter.validate();
        return UserDtoAll.mapToDtoList(userDao.getAll(filter));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UserDtoId getById(Long id) {
        if(id == null){
            throw new IllegalArgumentException("Id is null");
        }
        return userDao.getById(id);
    }

    private Document createDocument(UserRequest user){
        Document document = new Document();
        if(user.getDocName() != null) {
            document.setDocumentName(documentNameDao.getByName(user.getDocName()));
        }else{
            document.setDocumentName(documentNameDao.getByCode(user.getDocCode()));
        }
        document.setDocDate(user.getDocDate());
        document.setDocNumber(user.getDocNumber());
        return document;
    }


}
