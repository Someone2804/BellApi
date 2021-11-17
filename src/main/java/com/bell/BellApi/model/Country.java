package com.bell.BellApi.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "country")
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
    @Column(name = "citizenship_name", nullable = false)
    private String citizenshipName;

    /**
     *  Citizenship code
     */
    @Column(name = "citizenship_code", nullable = false)
    private String citizenshipCode;

    /**
     *  Documents belonging to this citizenship
     */
    @Column(name = "document", nullable = false)
    @OneToMany(mappedBy = "citizenship")
    private List<Document> documents;

    /**
     *  Constructor for Hibernate
     */
    public Country() {
    }

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
     *  Getter for {@link #documents}
     *  @return documents
     */
    public List<Document> getDocuments() {
        if(this.documents == null){
            this.documents = new ArrayList<>();
        }
        return documents;
    }

    /**
     *  Add document in {@link #documents}
     *  @param document
     */
    public void addDocument(Document document) {
        getDocuments().add(document);
    }
}
