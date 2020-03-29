package com.idealsoft.insurance.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;
import java.util.HashSet;
import java.util.Set;

/**
 * A InsuranceObject.
 */
@Entity
@Table(name = "insurance_object")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class InsuranceObject extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "identifier_1")
    private String identifier1;

    @Column(name = "identifier_2")
    private String identifier2;

    @Column(name = "identifier_3")
    private String identifier3;

    @OneToMany(mappedBy = "insuranceObject",fetch = FetchType.EAGER)
   // @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<InsuranceInstance> instances = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdentifier1() {
        return identifier1;
    }

    public InsuranceObject identifier1(String identifier1) {
        this.identifier1 = identifier1;
        return this;
    }

    public void setIdentifier1(String identifier1) {
        this.identifier1 = identifier1;
    }

    public String getIdentifier2() {
        return identifier2;
    }

    public InsuranceObject identifier2(String identifier2) {
        this.identifier2 = identifier2;
        return this;
    }

    public void setIdentifier2(String identifier2) {
        this.identifier2 = identifier2;
    }

    public String getIdentifier3() {
        return identifier3;
    }

    public InsuranceObject identifier3(String identifier3) {
        this.identifier3 = identifier3;
        return this;
    }

    public void setIdentifier3(String identifier3) {
        this.identifier3 = identifier3;
    }

    public Set<InsuranceInstance> getInstances() {
        return instances;
    }

    public InsuranceObject instances(Set<InsuranceInstance> insuranceInstances) {
        this.instances = insuranceInstances;
        return this;
    }

    public InsuranceObject addInstance(InsuranceInstance insuranceInstance) {
        this.instances.add(insuranceInstance);
        insuranceInstance.setInsuranceObject(this);
        return this;
    }

    public InsuranceObject removeInstance(InsuranceInstance insuranceInstance) {
        this.instances.remove(insuranceInstance);
        insuranceInstance.setInsuranceObject(null);
        return this;
    }

    public void setInstances(Set<InsuranceInstance> insuranceInstances) {
        this.instances = insuranceInstances;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof InsuranceObject)) {
            return false;
        }
        return id != null && id.equals(((InsuranceObject) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "InsuranceObject{" +
            "id=" + getId() +
            ", identifier1='" + getIdentifier1() + "'" +
            ", identifier2='" + getIdentifier2() + "'" +
            ", identifier3='" + getIdentifier3() + "'" +
            "}";
    }
}
