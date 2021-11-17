package com.bell.BellApi.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Version;
import java.util.Date;

@Entity
@Table(name = "document")
public class Document {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Hibernate service field
     */
    @Version
    private Integer version;


    /**
     *  Document code
     */
    @Column(name = "doc_code", length = 10, nullable = false)
    private int docCode;

    /**
     *  Document name
     */
    @Column(name = "doc_name", length = 50, nullable = false)
    private String docName;

    /**
     *  Document number
     */
    @Column(name = "doc_number", length = 20, nullable = false)
    private String docNumber;

    /**
     *  Document date
     */
    @Column(name = "doc_date", nullable = false)
    private Date docDate;

    /**
     *  Citizenship
     */
    @Column(name = "citizenship", nullable = false)
    @ManyToOne
    private Country citizenship;

    @Column(name = "usr", nullable = false)
    @ManyToOne
    private User user;

    /**
     *  Constructor for Hibernate
     */
    public Document() {
    }

    /**
     *  Getter for {@link #docCode}
     *  @return document code
     */
    public int getDocCode() {
        return docCode;
    }

    /**
     *  Setter for {@link #docCode}
     */
    public void setDocCode(int docCode) {
        this.docCode = docCode;
    }

    /**
     *  Getter for {@link #docName}
     *  @return document name
     */
    public String getDocName() {
        return docName;
    }

    /**
     *  Setter for {@link #docName}
     */
    public void setDocName(String docName) {
        this.docName = docName;
    }

    /**
     *  Getter for {@link #docNumber}
     *  @return document number
     */
    public String getDocNumber() {
        return docNumber;
    }

    /**
     *  Setter for {@link #docNumber}
     */
    public void setDocNumber(String docNumber) {
        this.docNumber = docNumber;
    }

    /**
     *  Getter for {@link #docDate}
     *  @return document date
     */
    public Date getDocDate() {
        return docDate;
    }

    /**
     *  Setter for {@link #docDate}
     */
    public void setDocDate(Date docDate) {
        this.docDate = docDate;
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
}
