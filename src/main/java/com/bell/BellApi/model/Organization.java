package com.bell.BellApi.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Organization")
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
    @Column(name = "short_name", length = 50, nullable = false)
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
     *  Organization offices
     */
    @OneToMany
    private Set<Office> offices;

    /**
     *  Is active
     */
    @Column(name = "is_active")
    private Boolean isActive;

    public Organization() {
    }

    public Organization(Long id, String name, String fullName, String inn, String kpp, String address, String phone, Boolean isActive) {
        this.id = id;
        this.name = name;
        this.fullName = fullName;
        this.inn = inn;
        this.kpp = kpp;
        this.address = address;
        this.phone = phone;
        this.isActive = isActive;
    }

    /**
     *  Getter for {@link #id}
     *  @return Organization id
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
     *  @return Organization short name
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
     *  @return Organization full name
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
     *  @return Organization inn
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
     *  @return Organization kpp
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
     *  @return Organization address
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
     *  @return Organization phone number
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
     *  Getter for {@link #offices}
     *  @return offices
     */
    public Set<Office> getOffices() {
        if(this.offices == null){
            this.offices = new HashSet<>();
        }
        return offices;
    }

    /**
     *  Add office if {@link #offices}
     *  @param office
     */
    public void addOffices(Office office) {
        getOffices().add(office);
    }

    /**
     *  Getter for {@link #isActive}
     *  @return true if Organization is active
     */
    public boolean isActive() {
        return isActive;
    }

    /**
     *  Setter for {@link #isActive}
     */
    public void setActive(Boolean active) {
        isActive = active;
    }

}
