package com.idealsoft.insurance.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

import com.idealsoft.insurance.domain.enumeration.InstanceDetailsStatus;

/**
 * A InsuranceInstanceDetails.
 */
@Entity
@Table(name = "insurance_instance_details")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class InsuranceInstanceDetails implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Lob
    @Column(name = "image")
    private byte[] image;

    @Column(name = "image_content_type")
    private String imageContentType;

    @Column(name = "comments")
    private String comments;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private InstanceDetailsStatus status;

    @ManyToOne
    @JsonIgnoreProperties("insuranceInstanceDetails")
    private InsuranceSpecification specification;

    @ManyToOne
    @JsonIgnoreProperties("details")
    private InsuranceInstance insuranceInstance;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public byte[] getImage() {
        return image;
    }

    public InsuranceInstanceDetails image(byte[] image) {
        this.image = image;
        return this;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getImageContentType() {
        return imageContentType;
    }

    public InsuranceInstanceDetails imageContentType(String imageContentType) {
        this.imageContentType = imageContentType;
        return this;
    }

    public void setImageContentType(String imageContentType) {
        this.imageContentType = imageContentType;
    }

    public String getComments() {
        return comments;
    }

    public InsuranceInstanceDetails comments(String comments) {
        this.comments = comments;
        return this;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public InstanceDetailsStatus getStatus() {
        return status;
    }

    public InsuranceInstanceDetails status(InstanceDetailsStatus status) {
        this.status = status;
        return this;
    }

    public void setStatus(InstanceDetailsStatus status) {
        this.status = status;
    }

    public InsuranceSpecification getSpecification() {
        return specification;
    }

    public InsuranceInstanceDetails specification(InsuranceSpecification insuranceSpecification) {
        this.specification = insuranceSpecification;
        return this;
    }

    public void setSpecification(InsuranceSpecification insuranceSpecification) {
        this.specification = insuranceSpecification;
    }

    public InsuranceInstance getInsuranceInstance() {
        return insuranceInstance;
    }

    public InsuranceInstanceDetails insuranceInstance(InsuranceInstance insuranceInstance) {
        this.insuranceInstance = insuranceInstance;
        return this;
    }

    public void setInsuranceInstance(InsuranceInstance insuranceInstance) {
        this.insuranceInstance = insuranceInstance;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof InsuranceInstanceDetails)) {
            return false;
        }
        return id != null && id.equals(((InsuranceInstanceDetails) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "InsuranceInstanceDetails{" +
            "id=" + getId() +
            ", image='" + getImage() + "'" +
            ", imageContentType='" + getImageContentType() + "'" +
            ", comments='" + getComments() + "'" +
            ", status='" + getStatus() + "'" +
            "}";
    }
}
