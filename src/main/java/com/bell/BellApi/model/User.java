package com.bell.BellApi.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;
import java.util.ArrayList;
import java.util.List;


/**
 * User
 */
@Entity
@Table(name = "usr")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Hibernate service field
     */
    @Version
    private Integer version;

    /**
     *  First name
     */
    @Column(name = "first_name", length = 50, nullable = false)
    private String firstName;

    /**
     *  Second name
     */
    @Column(name = "second_name", length = 50)
    private String secondName;

    /**
     *  Middle name
     */
    @Column(name = "middle_name", length = 50)
    private String middleName;

    /**
     *  Position
     */
    @Column(name = "position", nullable = false)
    private String position;

    /**
     *  Phone number
     */
    @Column(name = "phone", length = 30)
    private String phone;

    /**
     *  User document
     */
    @Column(name = "document")
    @OneToMany
    private List<Document> document;

    /**
     *  Office
     */
    @Column(name = "office")
    @ManyToOne
    private Office office;

    /**
     *  Validation of documents
     */
    @Column(name = "is_identified")
    private boolean isIdentified;

    /**
     *  Constructor for Hibernate
     */
    public User() {
    }

    /**
     * Getter for {@link #id}
     * @return User id
     */
    public Long getId() {
        return id;
    }

    /**
     *  Setter for {@link #id}
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     *  Getter for {@link #firstName}
     *  @return User first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     *  Setter for {@link #firstName}
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     *  Getter for {@link #secondName}
     *  @return User second name
     */
    public String getSecondName() {
        return secondName;
    }

    /**
     *  Setter for {@link #secondName}
     */
    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    /**
     *  Getter for {@link #middleName}
     *  @return User middle name
     */
    public String getMiddleName() {
        return middleName;
    }

    /**
     *  Setter for {@link #middleName}
     */
    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    /**
     *  Getter for {@link #position}
     *  @return User position
     */
    public String getPosition() {
        return position;
    }

    /**
     *  Setter for {@link #position}
     */
    public void setPosition(String position) {
        this.position = position;
    }

    /**
     *  Getter for {@link #phone}
     *  @return User phone number
     */
    public String getPhone() {
        return phone;
    }

    /**
     *  Setter for {@link #phone}
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     *  Getter for {@link #document}
     *  @return User documents
     */
    public List<Document> getDocument() {
        if(this.document == null){
            this.document = new ArrayList<>();
        }
        return document;
    }

    /**
     *  Add document in {@link #document}
     *  @param document
     */
    public void addDocument(Document document) {
        getDocument().add(document);
    }

    /**
     *  Getter for {@link #isIdentified}
     *  @return true if User is identified
     */
    public boolean isIdentified() {
        return isIdentified;
    }

    /**
     *  Setter for {@link #isIdentified}
     */
    public void setIdentified(boolean identified) {
        isIdentified = identified;
    }

    /**
     *  Getter for {@link #office}
     *  @return office
     */
    public Office getOffice() {
        return office;
    }

    /**
     *  Setter for {@link #office}
     */
    public void setOffice(Office office) {
        this.office = office;
    }
}
