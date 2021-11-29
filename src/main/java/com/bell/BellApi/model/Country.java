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
@Table(name = "Citizenship")
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Hibernate service field
     */
    @Version
    private Integer version;

    /**
     * Citizenship name
     */
    @Column(name = "citizenship_name", length = 50, nullable = false)
    private String citizenshipName;

    /**
     *  Citizenship code
     */
    @Column(name = "citizenship_code", length = 10, nullable = false)
    private String citizenshipCode;

    /**
     *  Documents belonging to this citizenship
     */
    @OneToMany(mappedBy = "citizenship")
    private Set<User> users;

    /**
     *  Getter for {@link #citizenshipName}
     *  @return citizenship name
     */
    public String getCitizenshipName() {
        return citizenshipName;
    }

    /**
     *  Setter for {@link #citizenshipName}
     */
    public void setCitizenshipName(String citizenshipName) {
        this.citizenshipName = citizenshipName;
    }

    /**
     *  Getter for {@link #citizenshipCode}
     *  @return citizenship code
     */
    public String getCitizenshipCode() {
        return citizenshipCode;
    }

    /**
     *  Setter for {@link #citizenshipCode}
     */
    public void setCitizenshipCode(String citizenshipCode) {
        this.citizenshipCode = citizenshipCode;
    }

    /**
     *  Getter for {@link #users}
     *  @return documents
     */
    public Set<User> getUsers() {
        if(this.users == null){
            this.users = new HashSet<>();
        }
        return users;
    }

    /**
     *  Add document in {@link #users}
     *  @param user
     */
    public void addUser(User user) {
        getUsers().add(user);
    }
}
