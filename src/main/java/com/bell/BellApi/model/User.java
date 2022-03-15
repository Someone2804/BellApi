package com.bell.BellApi.model;

import com.bell.BellApi.model.role.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Version;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;


/**
 * User
 */
@Entity
@Table(name = "Usr")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Hibernate service field
     */
    @Version
    private Integer version;

    @Column(name = "username", length = 30, nullable = false, unique = true)
    private String username;

    @Column(name = "password", length = 72, nullable = false)
    private String password;

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "usr_role", joinColumns = @JoinColumn(name = "usr_id"))
    @Column(name = "name")
    @Enumerated(value = EnumType.STRING)
    private Set<Role> roles;

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
     *  Phone number
     */
    @Column(name = "phone", length = 30)
    private String phone;

    /**
     *  Position
     */
    @ManyToMany
    @JoinTable(
            joinColumns = @JoinColumn(name = "usr_id", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "position_id", nullable = false)
    )
    private Set<Position> position;

    /**
     *  User document
     */
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "user")
    private Document document;

    /**
     *  Office
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "office_id", nullable = false)
    private Office office;

    /**
     *  Citizenship
     */
    @ManyToOne
    @JoinColumn(name = "citizenship_id")
    private Country citizenship;

    /**
     *  Validation of documents
     */
    @Column(name = "is_identified")
    private boolean isIdentified;

    public User() {
    }

    public User(Long id, String firstName, Set<Position> position) {
        this.id = id;
        this.firstName = firstName;
        this.position = position;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<SimpleGrantedAuthority> authority = new ArrayList<>();
        roles.forEach(role -> authority.add(new SimpleGrantedAuthority(role.name())));
        return authority;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
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
     *  @return User positions
     */
    public Set<Position> getPosition() {
        if(this.position == null){
            this.position = new HashSet<>();
        }
        return position;
    }

    /**
     *  Add position in {@link #position}
     *  @param position
     */
    public void addPosition(Position position) {
        getPosition().add(position);
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
    public Document getDocument() {
        return document;
    }

    /**
     *  Setter for {@link #document}
     */
    public void setDocument(Document document) {
        this.document = document;
    }

    /**
     *  Getter for {@link #citizenship}
     *  @return citizenship entity
     */
    public Country getCitizenship() {
        return citizenship;
    }

    /**
     *  Setter for {@link #citizenship}
     */
    public void setCitizenship(Country citizenship) {
        this.citizenship = citizenship;
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
