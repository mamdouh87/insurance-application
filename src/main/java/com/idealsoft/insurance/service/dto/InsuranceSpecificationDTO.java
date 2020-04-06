package com.idealsoft.insurance.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.idealsoft.insurance.domain.InsuranceSpecification} entity.
 */
public class InsuranceSpecificationDTO implements Serializable {

    private Long id;

    private String code;

    private String descriptionAr;

    private String descriptionEn;


    private Long insurenceObjectTypeId;

    private String imageURL;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescriptionAr() {
        return descriptionAr;
    }

    public void setDescriptionAr(String descriptionAr) {
        this.descriptionAr = descriptionAr;
    }

    public String getDescriptionEn() {
        return descriptionEn;
    }

    public void setDescriptionEn(String descriptionEn) {
        this.descriptionEn = descriptionEn;
    }

    public Long getInsurenceObjectTypeId() {
        return insurenceObjectTypeId;
    }

    public void setInsurenceObjectTypeId(Long insuranceObjectTypeId) {
        this.insurenceObjectTypeId = insuranceObjectTypeId;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        InsuranceSpecificationDTO insuranceSpecificationDTO = (InsuranceSpecificationDTO) o;
        if (insuranceSpecificationDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), insuranceSpecificationDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "InsuranceSpecificationDTO{" +
            "id=" + getId() +
            ", code='" + getCode() + "'" +
            ", descriptionAr='" + getDescriptionAr() + "'" +
            ", descriptionEn='" + getDescriptionEn() + "'" +
            ", insurenceObjectTypeId=" + getInsurenceObjectTypeId() +
            ", imageURL=" + getImageURL() +
            "}";
    }
}
