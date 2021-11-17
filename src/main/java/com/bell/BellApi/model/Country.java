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
@Table(name = "Country")
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
    private int citizenshipCode;

    /**
     *  Documents belonging to this citizenship
     */
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
    public int getCitizenshipCode() {
        return citizenshipCode;
    }

    /**
     *  Setter for {@link #citizenshipCode}
     */
    public void setCitizenshipCode(int citizenshipCode) {
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
