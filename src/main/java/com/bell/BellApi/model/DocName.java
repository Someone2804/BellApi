package com.bell.BellApi.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;
import java.util.Set;


@Entity
@Table(name = "Document_name")
public class DocName {

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
     *  Documents
     */
    @OneToMany(mappedBy = "documentName")
    private Set<Document> documents;

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
}
