package com.bell.BellApi.dto.user.response;

import com.bell.BellApi.model.User;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserDtoId {

    private Long id;

    private String firstName;

    private String secondName;

    private String middleName;

    private String phone;

    private List<String> position;

    private String docName;

    private String docNumber;

    private Date docDate;

    private String citizenshipName;

    private String citizenshipCode;

    private boolean isIdentified;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<String> getPosition() {
        if(position == null){
            position = new ArrayList<>();
        }
        return position;
    }

    public void addPosition(String position) {
        getPosition().add(position);
    }

    public String getDocName() {
        return docName;
    }

    public void setDocName(String docName) {
        this.docName = docName;
    }

    public String getDocNumber() {
        return docNumber;
    }

    public void setDocNumber(String docNumber) {
        this.docNumber = docNumber;
    }

    public Date getDocDate() {
        return docDate;
    }

    public void setDocDate(Date docDate) {
        this.docDate = docDate;
    }

    public String getCitizenshipName() {
        return citizenshipName;
    }

    public void setCitizenshipName(String citizenshipName) {
        this.citizenshipName = citizenshipName;
    }

    public String getCitizenshipCode() {
        return citizenshipCode;
    }

    public void setCitizenshipCode(String citizenshipCode) {
        this.citizenshipCode = citizenshipCode;
    }

    public boolean isIdentified() {
        return isIdentified;
    }

    public void setIdentified(boolean identified) {
        isIdentified = identified;
    }

    public static UserDtoId mapToDto(User user){
        UserDtoId result = new UserDtoId();
        result.setId(user.getId());
        result.setFirstName(user.getFirstName());
        result.setSecondName(user.getSecondName());
        result.setMiddleName(user.getMiddleName());
        result.setPhone(user.getPhone());
        if(user.getDocument() != null) {
            result.setDocName(user.getDocument().getDocumentName().getDocName());
            result.setDocNumber(user.getDocument().getDocNumber());
            result.setDocDate(user.getDocument().getDocDate());
        }
        if(user.getCitizenship() != null) {
            result.setCitizenshipName(user.getCitizenship().getCitizenshipName());
            result.setCitizenshipCode(user.getCitizenship().getCitizenshipCode());
        }
        result.setIdentified(user.isIdentified());
        return result;
    }
}
