package com.idealsoft.insurance.service.dto;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Lob;
import com.idealsoft.insurance.domain.enumeration.InstanceDetailsStatus;

/**
 * A DTO for the {@link com.idealsoft.insurance.domain.InsuranceInstanceDetails} entity.
 */
public class InsuranceInstanceDetailsDTO implements Serializable {
    
    private Long id;

    @Lob
    private byte[] image;

    private String imageContentType;
    private String comments;

    private InstanceDetailsStatus status;


    private Long specificationId;

    private Long insuranceInstanceId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getImageContentType() {
        return imageContentType;
    }

    public void setImageContentType(String imageContentType) {
        this.imageContentType = imageContentType;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public InstanceDetailsStatus getStatus() {
        return status;
    }

    public void setStatus(InstanceDetailsStatus status) {
        this.status = status;
    }

    public Long getSpecificationId() {
        return specificationId;
    }

    public void setSpecificationId(Long insuranceSpecificationId) {
        this.specificationId = insuranceSpecificationId;
    }

    public Long getInsuranceInstanceId() {
        return insuranceInstanceId;
    }

    public void setInsuranceInstanceId(Long insuranceInstanceId) {
        this.insuranceInstanceId = insuranceInstanceId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        InsuranceInstanceDetailsDTO insuranceInstanceDetailsDTO = (InsuranceInstanceDetailsDTO) o;
        if (insuranceInstanceDetailsDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), insuranceInstanceDetailsDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "InsuranceInstanceDetailsDTO{" +
            "id=" + getId() +
            ", image='" + getImage() + "'" +
            ", comments='" + getComments() + "'" +
            ", status='" + getStatus() + "'" +
            ", specificationId=" + getSpecificationId() +
            ", insuranceInstanceId=" + getInsuranceInstanceId() +
            "}";
    }
}
