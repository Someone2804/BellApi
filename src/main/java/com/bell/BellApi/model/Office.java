package com.bell.BellApi.model;

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;
import java.util.HashSet;
import java.util.Set;


/**
 *  Office
 */
@Entity
@Table(name = "Office")
@NamedQueries({
        @NamedQuery(
                name = "Office.getById",
                query = "select o from Office o WHERE id = :id"
)
})
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
    @Column(name = "office_name", length = 50, nullable = false)
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
     *  Workers
     */
    @OneToMany(mappedBy = "office")
    private Set<User> workers;

    /**
     *  Organization
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "organization_id", nullable = false)
    private Organization organization;

    /**
     *  Is active
     */
    @Column(name = "is_active", nullable = false)
    private Boolean isActive;

    /**
     *  Getter for {@link #id}
     *  @return Office id
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
     *  @return Office name
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
     *  @return Office address
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
     *  @return Office phone number
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
     *  Getter for {@link #workers}
     *  @return Office workers
     */
    public Set<User> getWorkers() {
        if(this.workers == null){
            this.workers = new HashSet<>();
        }
        return workers;
    }

    /**
     * Add worker in {@link #workers}
     * @param user
     */
    public void addWorkers(User user) {
        getWorkers().add(user);
    }

    /**
     *  Getter for {@link #organization}
     *  @return organization
     */
    public Organization getOrganization() {
        return organization;
    }

    /**
     *  Setter for {@link #organization}
     */
    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    /**
     *  Getter for {@link #isActive}
     *  @return true if office is active
     */
    public Boolean isActive() {
        return isActive;
    }

    /**
     *  Setter for {@link #isActive}
     */
    public void setActive(Boolean active) {
        isActive = active;
    }
}
