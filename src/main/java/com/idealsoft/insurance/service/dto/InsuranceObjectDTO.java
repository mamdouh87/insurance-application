package com.idealsoft.insurance.service.dto;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 * A DTO for the {@link com.idealsoft.insurance.domain.InsuranceObject} entity.
 */
public class InsuranceObjectDTO implements Serializable {

    private Long id;

    private String identifier1;

    private String identifier2;

    private String identifier3;

    private List<InsuranceInstanceDTO> insuranceInstanceDTOS;

    private Long typeId;

    private String typeDescEn;
    private String typeDescAr;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdentifier1() {
        return identifier1;
    }

    public void setIdentifier1(String identifier1) {
        this.identifier1 = identifier1;
    }

    public String getIdentifier2() {
        return identifier2;
    }

    public void setIdentifier2(String identifier2) {
        this.identifier2 = identifier2;
    }

    public String getIdentifier3() {
        return identifier3;
    }

    public void setIdentifier3(String identifier3) {
        this.identifier3 = identifier3;
    }

    public List<InsuranceInstanceDTO> getInsuranceInstanceDTOS() {
        return insuranceInstanceDTOS;
    }

    public void setInsuranceInstanceDTOS(List<InsuranceInstanceDTO> insuranceInstanceDTOS) {
        this.insuranceInstanceDTOS = insuranceInstanceDTOS;
    }

    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long insuranceObjectTypeId) {
        this.typeId = insuranceObjectTypeId;
    }

    public String getTypeDescEn() {
        return typeDescEn;
    }

    public void setTypeDescEn(String typeDescEn) {
        this.typeDescEn = typeDescEn;
    }

    public String getTypeDescAr() {
        return typeDescAr;
    }

    public void setTypeDescAr(String typeDescAr) {
        this.typeDescAr = typeDescAr;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        InsuranceObjectDTO insuranceObjectDTO = (InsuranceObjectDTO) o;
        if (insuranceObjectDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), insuranceObjectDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "InsuranceObjectDTO{" +
            "id=" + getId() +
            ", identifier1='" + getIdentifier1() + "'" +
            ", identifier2='" + getIdentifier2() + "'" +
            ", identifier3='" + getIdentifier3() + "'" +
            ", typeId=" + getTypeId() +
            "}";
    }
}
