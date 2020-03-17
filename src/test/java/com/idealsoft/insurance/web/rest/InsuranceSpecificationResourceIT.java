package com.idealsoft.insurance.web.rest;

import com.idealsoft.insurance.InsuranceApplicationApp;
import com.idealsoft.insurance.domain.InsuranceSpecification;
import com.idealsoft.insurance.repository.InsuranceSpecificationRepository;
import com.idealsoft.insurance.service.InsuranceSpecificationService;
import com.idealsoft.insurance.service.dto.InsuranceSpecificationDTO;
import com.idealsoft.insurance.service.mapper.InsuranceSpecificationMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link InsuranceSpecificationResource} REST controller.
 */
@SpringBootTest(classes = InsuranceApplicationApp.class)

@AutoConfigureMockMvc
@WithMockUser
public class InsuranceSpecificationResourceIT {

    private static final String DEFAULT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION_AR = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION_AR = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION_EN = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION_EN = "BBBBBBBBBB";

    @Autowired
    private InsuranceSpecificationRepository insuranceSpecificationRepository;

    @Autowired
    private InsuranceSpecificationMapper insuranceSpecificationMapper;

    @Autowired
    private InsuranceSpecificationService insuranceSpecificationService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restInsuranceSpecificationMockMvc;

    private InsuranceSpecification insuranceSpecification;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static InsuranceSpecification createEntity(EntityManager em) {
        InsuranceSpecification insuranceSpecification = new InsuranceSpecification()
            .code(DEFAULT_CODE)
            .descriptionAr(DEFAULT_DESCRIPTION_AR)
            .descriptionEn(DEFAULT_DESCRIPTION_EN);
        return insuranceSpecification;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static InsuranceSpecification createUpdatedEntity(EntityManager em) {
        InsuranceSpecification insuranceSpecification = new InsuranceSpecification()
            .code(UPDATED_CODE)
            .descriptionAr(UPDATED_DESCRIPTION_AR)
            .descriptionEn(UPDATED_DESCRIPTION_EN);
        return insuranceSpecification;
    }

    @BeforeEach
    public void initTest() {
        insuranceSpecification = createEntity(em);
    }

    @Test
    @Transactional
    public void createInsuranceSpecification() throws Exception {
        int databaseSizeBeforeCreate = insuranceSpecificationRepository.findAll().size();

        // Create the InsuranceSpecification
        InsuranceSpecificationDTO insuranceSpecificationDTO = insuranceSpecificationMapper.toDto(insuranceSpecification);
        restInsuranceSpecificationMockMvc.perform(post("/api/insurance-specifications")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(insuranceSpecificationDTO)))
            .andExpect(status().isCreated());

        // Validate the InsuranceSpecification in the database
        List<InsuranceSpecification> insuranceSpecificationList = insuranceSpecificationRepository.findAll();
        assertThat(insuranceSpecificationList).hasSize(databaseSizeBeforeCreate + 1);
        InsuranceSpecification testInsuranceSpecification = insuranceSpecificationList.get(insuranceSpecificationList.size() - 1);
        assertThat(testInsuranceSpecification.getCode()).isEqualTo(DEFAULT_CODE);
        assertThat(testInsuranceSpecification.getDescriptionAr()).isEqualTo(DEFAULT_DESCRIPTION_AR);
        assertThat(testInsuranceSpecification.getDescriptionEn()).isEqualTo(DEFAULT_DESCRIPTION_EN);
    }

    @Test
    @Transactional
    public void createInsuranceSpecificationWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = insuranceSpecificationRepository.findAll().size();

        // Create the InsuranceSpecification with an existing ID
        insuranceSpecification.setId(1L);
        InsuranceSpecificationDTO insuranceSpecificationDTO = insuranceSpecificationMapper.toDto(insuranceSpecification);

        // An entity with an existing ID cannot be created, so this API call must fail
        restInsuranceSpecificationMockMvc.perform(post("/api/insurance-specifications")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(insuranceSpecificationDTO)))
            .andExpect(status().isBadRequest());

        // Validate the InsuranceSpecification in the database
        List<InsuranceSpecification> insuranceSpecificationList = insuranceSpecificationRepository.findAll();
        assertThat(insuranceSpecificationList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllInsuranceSpecifications() throws Exception {
        // Initialize the database
        insuranceSpecificationRepository.saveAndFlush(insuranceSpecification);

        // Get all the insuranceSpecificationList
        restInsuranceSpecificationMockMvc.perform(get("/api/insurance-specifications?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(insuranceSpecification.getId().intValue())))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE)))
            .andExpect(jsonPath("$.[*].descriptionAr").value(hasItem(DEFAULT_DESCRIPTION_AR)))
            .andExpect(jsonPath("$.[*].descriptionEn").value(hasItem(DEFAULT_DESCRIPTION_EN)));
    }
    
    @Test
    @Transactional
    public void getInsuranceSpecification() throws Exception {
        // Initialize the database
        insuranceSpecificationRepository.saveAndFlush(insuranceSpecification);

        // Get the insuranceSpecification
        restInsuranceSpecificationMockMvc.perform(get("/api/insurance-specifications/{id}", insuranceSpecification.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(insuranceSpecification.getId().intValue()))
            .andExpect(jsonPath("$.code").value(DEFAULT_CODE))
            .andExpect(jsonPath("$.descriptionAr").value(DEFAULT_DESCRIPTION_AR))
            .andExpect(jsonPath("$.descriptionEn").value(DEFAULT_DESCRIPTION_EN));
    }

    @Test
    @Transactional
    public void getNonExistingInsuranceSpecification() throws Exception {
        // Get the insuranceSpecification
        restInsuranceSpecificationMockMvc.perform(get("/api/insurance-specifications/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateInsuranceSpecification() throws Exception {
        // Initialize the database
        insuranceSpecificationRepository.saveAndFlush(insuranceSpecification);

        int databaseSizeBeforeUpdate = insuranceSpecificationRepository.findAll().size();

        // Update the insuranceSpecification
        InsuranceSpecification updatedInsuranceSpecification = insuranceSpecificationRepository.findById(insuranceSpecification.getId()).get();
        // Disconnect from session so that the updates on updatedInsuranceSpecification are not directly saved in db
        em.detach(updatedInsuranceSpecification);
        updatedInsuranceSpecification
            .code(UPDATED_CODE)
            .descriptionAr(UPDATED_DESCRIPTION_AR)
            .descriptionEn(UPDATED_DESCRIPTION_EN);
        InsuranceSpecificationDTO insuranceSpecificationDTO = insuranceSpecificationMapper.toDto(updatedInsuranceSpecification);

        restInsuranceSpecificationMockMvc.perform(put("/api/insurance-specifications")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(insuranceSpecificationDTO)))
            .andExpect(status().isOk());

        // Validate the InsuranceSpecification in the database
        List<InsuranceSpecification> insuranceSpecificationList = insuranceSpecificationRepository.findAll();
        assertThat(insuranceSpecificationList).hasSize(databaseSizeBeforeUpdate);
        InsuranceSpecification testInsuranceSpecification = insuranceSpecificationList.get(insuranceSpecificationList.size() - 1);
        assertThat(testInsuranceSpecification.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testInsuranceSpecification.getDescriptionAr()).isEqualTo(UPDATED_DESCRIPTION_AR);
        assertThat(testInsuranceSpecification.getDescriptionEn()).isEqualTo(UPDATED_DESCRIPTION_EN);
    }

    @Test
    @Transactional
    public void updateNonExistingInsuranceSpecification() throws Exception {
        int databaseSizeBeforeUpdate = insuranceSpecificationRepository.findAll().size();

        // Create the InsuranceSpecification
        InsuranceSpecificationDTO insuranceSpecificationDTO = insuranceSpecificationMapper.toDto(insuranceSpecification);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restInsuranceSpecificationMockMvc.perform(put("/api/insurance-specifications")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(insuranceSpecificationDTO)))
            .andExpect(status().isBadRequest());

        // Validate the InsuranceSpecification in the database
        List<InsuranceSpecification> insuranceSpecificationList = insuranceSpecificationRepository.findAll();
        assertThat(insuranceSpecificationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteInsuranceSpecification() throws Exception {
        // Initialize the database
        insuranceSpecificationRepository.saveAndFlush(insuranceSpecification);

        int databaseSizeBeforeDelete = insuranceSpecificationRepository.findAll().size();

        // Delete the insuranceSpecification
        restInsuranceSpecificationMockMvc.perform(delete("/api/insurance-specifications/{id}", insuranceSpecification.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<InsuranceSpecification> insuranceSpecificationList = insuranceSpecificationRepository.findAll();
        assertThat(insuranceSpecificationList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
