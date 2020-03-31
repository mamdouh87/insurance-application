package com.idealsoft.insurance.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.idealsoft.insurance.domain.InsuranceObjectType} entity.
 */
public class InsuranceObjectTypeDTO implements Serializable {
    
    private Long id;

    private String code;

    private String descriptionAr;

    private String descriptionEn;


    private Long objectTypeId;
    
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

    public Long getObjectTypeId() {
        return objectTypeId;
    }

    public void setObjectTypeId(Long insuranceSpecificationId) {
        this.objectTypeId = insuranceSpecificationId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        InsuranceObjectTypeDTO insuranceObjectTypeDTO = (InsuranceObjectTypeDTO) o;
        if (insuranceObjectTypeDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), insuranceObjectTypeDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "InsuranceObjectTypeDTO{" +
            "id=" + getId() +
            ", code='" + getCode() + "'" +
            ", descriptionAr='" + getDescriptionAr() + "'" +
            ", descriptionEn='" + getDescriptionEn() + "'" +
            ", objectTypeId=" + getObjectTypeId() +
            "}";
    }
}
