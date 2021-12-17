package com.bell.BellApi.dto.user.request;

import com.bell.BellApi.model.Document;
import com.bell.BellApi.model.User;

import java.util.Date;

public class UserRequest {
    private Long id;

    private Long officeId;

    private String firstName;

    private String secondName;

    private String middleName;

    private String position;

    private String phone;

    private String docName;

    private String docCode;

    private String docNumber;

    private Date docDate;

    private String citizenshipCode;

    private boolean isIdentified;

    public UserRequest(Long id, Long officeId, String firstName, String secondName, String middleName, String position, String phone, String docName, String docCode, String docNumber, Date docDate, String citizenshipCode, boolean isIdentified) {
        this.id = id;
        this.officeId = officeId;
        this.firstName = firstName;
        this.secondName = secondName;
        this.middleName = middleName;
        this.position = position;
        this.phone = phone;
        this.docName = docName;
        this.docCode = docCode;
        this.docNumber = docNumber;
        this.docDate = docDate;
        this.citizenshipCode = citizenshipCode;
        this.isIdentified = isIdentified;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOfficeId() {
        return officeId;
    }

    public void setOfficeId(Long officeId) {
        this.officeId = officeId;
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

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDocName() {
        return docName;
    }

    public void setDocName(String docName) {
        this.docName = docName;
    }

    public String getDocCode() {
        return docCode;
    }

    public void setDocCode(String docCode) {
        this.docCode = docCode;
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

    public String getCitizenshipCode() {
        return citizenshipCode;
    }

    public void setCitizenshipCode(String citizenshipCode) {
        this.citizenshipCode = citizenshipCode;
    }

    public boolean getIdentified() {
        return isIdentified;
    }

    public void setIdentified(boolean identified) {
        isIdentified = identified;
    }

    public void validateForSave(){
        if(getOfficeId() == null){
            throw new IllegalStateException("Missing required parameter officeId");
        }
        validateFields();
    }
    public void validateForUpdate(){
        if(getId() == null){
            throw new IllegalStateException("Missing required parameter id");
        }
        validateFields();
    }

    public boolean validateDocument() {
        if (getDocCode() != null || getDocName() != null) {
            if (getDocDate() == null) {
                throw new IllegalStateException("Missing required parameter docDate");
            }
            if (getDocNumber() == null) {
                throw new IllegalStateException("Missing required parameter docNumber");
            }
            return true;
        }
        return false;
    }

    public boolean validateCitizenship(){
        return getCitizenshipCode() != null;
    }

    public void fillDocument(Document document){
        document.setDocDate(getDocDate());
        document.setDocNumber(getDocNumber());
    }

    private void validateFields(){
        if(getFirstName() == null){
            throw new IllegalStateException("Missing required parameter firstName");
        }
        if(getPosition() == null){
            throw new IllegalStateException("Missing required parameter position");
        }
    }

    public static User mapToEntity(UserRequest userRequest){
        User user = new User();
        user.setId(userRequest.getId());
        user.setFirstName(userRequest.getFirstName());
        user.setSecondName(userRequest.getSecondName());
        user.setMiddleName(userRequest.getMiddleName());
        user.setPhone(userRequest.getPhone());
        user.setIdentified(userRequest.getIdentified());
        return user;
    }
}
