package com.bell.BellApi.model.User;


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
@Table(name = "Worker_position")
public class Position {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Hibernate service field
     */
    @Version
    private Integer version;

    /**
     *  Position name
     */
    @Column(name = "position_name")
    private String positionName;

    /**
     *  Workers
     */
    @OneToMany(mappedBy = "position")
    private Set<User> workers;

    /**
     * Getter for {@link #positionName}
     * @return position name
     */
    public String getPositionName() {
        return positionName;
    }

    /**
     *  Setter for {@link #positionName}
     */
    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    /**
     * Getter for {@link #workers}
     * @return workers
     */
    public Set<User> getWorkers() {
        if(this.workers == null){
            this.workers = new HashSet<>();
        }
        return workers;
    }

    /**
     *  Add worker in {@link #workers}
     *  @param worker
     */
    public void addWorkers(User worker) {
        getWorkers().add(worker);
    }
}
