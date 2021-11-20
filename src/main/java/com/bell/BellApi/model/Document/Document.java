package com.bell.BellApi.model.Document;


import com.bell.BellApi.model.User.User;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Version;
import java.util.Date;

@Entity
@Table(name = "Document")
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
     * Document name
     */
    @ManyToOne
    @JoinColumn(name = "doc_name_id", nullable = false)
    private DocName documentName;

    /**
     *  User
     */
    @OneToOne(mappedBy = "document", optional = false)
    @MapsId
    private User user;


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
}
