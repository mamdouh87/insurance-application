package com.idealsoft.insurance.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

/**
 * A InsuranceInstance.
 */
@Entity
@Table(name = "insurance_instance")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class InsuranceInstance extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "instance_date")
    private Instant instanceDate;

    @OneToOne
    @JoinColumn(name="created_by_user")
    private User user;

    @OneToMany(mappedBy = "insuranceInstance")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<InsuranceInstanceDetails> details = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties("instances")
    private InsuranceObject insuranceObject;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getInstanceDate() {
        return instanceDate;
    }

    public InsuranceInstance instanceDate(Instant instanceDate) {
        this.instanceDate = instanceDate;
        return this;
    }

    public void setInstanceDate(Instant instanceDate) {
        this.instanceDate = instanceDate;
    }

    public User getUser() {
        return user;
    }

    public InsuranceInstance user(User user) {
        this.user = user;
        return this;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<InsuranceInstanceDetails> getDetails() {
        return details;
    }

    public InsuranceInstance details(Set<InsuranceInstanceDetails> insuranceInstanceDetails) {
        this.details = insuranceInstanceDetails;
        return this;
    }

    public InsuranceInstance addDetails(InsuranceInstanceDetails insuranceInstanceDetails) {
        this.details.add(insuranceInstanceDetails);
        insuranceInstanceDetails.setInsuranceInstance(this);
        return this;
    }

    public InsuranceInstance removeDetails(InsuranceInstanceDetails insuranceInstanceDetails) {
        this.details.remove(insuranceInstanceDetails);
        insuranceInstanceDetails.setInsuranceInstance(null);
        return this;
    }

    public void setDetails(Set<InsuranceInstanceDetails> insuranceInstanceDetails) {
        this.details = insuranceInstanceDetails;
    }

    public InsuranceObject getInsuranceObject() {
        return insuranceObject;
    }

    public InsuranceInstance insuranceObject(InsuranceObject insuranceObject) {
        this.insuranceObject = insuranceObject;
        return this;
    }

    public void setInsuranceObject(InsuranceObject insuranceObject) {
        this.insuranceObject = insuranceObject;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof InsuranceInstance)) {
            return false;
        }
        return id != null && id.equals(((InsuranceInstance) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "InsuranceInstance{" +
            "id=" + getId() +
            ", instanceDate='" + getInstanceDate() + "'" +
            "}";
    }
}
