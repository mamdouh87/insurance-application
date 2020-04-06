package com.idealsoft.insurance.service.dto;

import java.time.Instant;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.idealsoft.insurance.domain.InsuranceInstance} entity.
 */
public class InsuranceInstanceDTO implements Serializable {

    private Long id;

    private Instant instanceDate;


    private Long userId;

    private Long insuranceObjectId;

    private Integer status;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getInstanceDate() {
        return instanceDate;
    }

    public void setInstanceDate(Instant instanceDate) {
        this.instanceDate = instanceDate;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getInsuranceObjectId() {
        return insuranceObjectId;
    }

    public void setInsuranceObjectId(Long insuranceObjectId) {
        this.insuranceObjectId = insuranceObjectId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        InsuranceInstanceDTO insuranceInstanceDTO = (InsuranceInstanceDTO) o;
        if (insuranceInstanceDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), insuranceInstanceDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "InsuranceInstanceDTO{" +
            "id=" + getId() +
            ", instanceDate='" + getInstanceDate() + "'" +
            ", userId=" + getUserId() +
            ", status=" + getStatus() +
            ", insuranceObjectId=" + getInsuranceObjectId() +
            "}";
    }


}
