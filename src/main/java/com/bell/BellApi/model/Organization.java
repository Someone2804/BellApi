package com.bell.BellApi.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name = "organization")
public class Organization {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     *  Hibernate service field
     */
    @Version
    private Integer version;

    /**
     *  Name
     */
    @Column(name = "name", length = 50, nullable = false)
    private String name;

    /**
     *  Full name
     */
    @Column(name = "full_name", length = 50, nullable = false)
    private String fullName;

    /**
     *  Number of INN
     */
    @Column(name = "inn", length = 12, nullable = false)
    private String inn;

    /**
     *  Number of KPP
     */
    @Column(name = "kpp", length = 9, nullable = false)
    private String kpp;

    /**
     *  Address
     */
    @Column(name = "address", length = 50, nullable = false)
    private String address;

    /**
     *  Phone number
     */
    @Column(name = "phone", length = 30)
    private String phone;

    /**
     *  Is active
     */
    @Column(name = "is_active")
    private boolean isActive;

    /**
     * Constructor for Hibernate
     */
    public Organization() {
    }

    /**
     *  Getter for {@link #id}
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
     *  Getter for {@link #name}
     *  @return
     */
    public String getName() {
        return name;
    }

    /**
     *  Setter for {@link #name}
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *  Getter for {@link #fullName}
     *  @return
     */
    public String getFullName() {
        return fullName;
    }

    /**
     *  Setter for {@link #fullName}
     */
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    /**
     *  Getter for {@link #inn}
     *  @return
     */
    public String getInn() {
        return inn;
    }

    /**
     *  Setter for {@link #inn}
     */
    public void setInn(String inn) {
        this.inn = inn;
    }

    /**
     *  Getter for {@link #kpp}
     *  @return
     */
    public String getKpp() {
        return kpp;
    }

    /**
     *  Setter for {@link #kpp}
     */
    public void setKpp(String kpp) {
        this.kpp = kpp;
    }

    /**
     *  Getter for {@link #address}
     *  @return
     */
    public String getAddress() {
        return address;
    }

    /**
     *  Setter for {@link #address}
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     *  Getter for {@link #phone}
     *  @return
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
     *  Getter for {@link #isActive}
     *  @return
     */
    public boolean isActive() {
        return isActive;
    }

    /**
     *  Setter for {@link #isActive}
     */
    public void setActive(boolean active) {
        isActive = active;
    }
}
