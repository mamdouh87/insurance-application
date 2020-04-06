package com.idealsoft.insurance.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A InsuranceSpecification.
 */
@Entity
@Table(name = "insurance_specification")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class InsuranceSpecification extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "code")
    private String code;

    @Column(name = "description_ar")
    private String descriptionAr;

    @Column(name = "description_en")
    private String descriptionEn;

    @Column(name = "image_url")
    private String imageURL;

    @ManyToOne
    @JoinColumn(name="insurance_object_type_id")
    @JsonIgnoreProperties("insuranceSpecifications")
    private InsuranceObjectType insurenceObjectType;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public InsuranceSpecification code(String code) {
        this.code = code;
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescriptionAr() {
        return descriptionAr;
    }

    public InsuranceSpecification descriptionAr(String descriptionAr) {
        this.descriptionAr = descriptionAr;
        return this;
    }

    public void setDescriptionAr(String descriptionAr) {
        this.descriptionAr = descriptionAr;
    }

    public String getDescriptionEn() {
        return descriptionEn;
    }

    public InsuranceSpecification descriptionEn(String descriptionEn) {
        this.descriptionEn = descriptionEn;
        return this;
    }

    public void setDescriptionEn(String descriptionEn) {
        this.descriptionEn = descriptionEn;
    }

    public InsuranceObjectType getInsurenceObjectType() {
        return insurenceObjectType;
    }

    public InsuranceSpecification insurenceObjectType(InsuranceObjectType insuranceObjectType) {
        this.insurenceObjectType = insuranceObjectType;
        return this;
    }

    public void setInsurenceObjectType(InsuranceObjectType insuranceObjectType) {
        this.insurenceObjectType = insuranceObjectType;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof InsuranceSpecification)) {
            return false;
        }
        return id != null && id.equals(((InsuranceSpecification) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "InsuranceSpecification{" +
            "id=" + getId() +
            ", code='" + getCode() + "'" +
            ", descriptionAr='" + getDescriptionAr() + "'" +
            ", descriptionEn='" + getDescriptionEn() + "'" +
            "}";
    }
}
