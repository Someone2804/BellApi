package com.bell.BellApi.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;


/**
 *  Office
 */
@Entity
@Table(name = "office")
public class Office {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Hibernate service field
     */
    @Version
    private Integer version;

    /**
     *  Name
     */
    @Column(name = "name", length = 50, nullable = false)
    private String name;

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
    public Office() {
    }

    /**
     *  Getter for {@link #id}
     *  @return
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
