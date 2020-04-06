package com.idealsoft.insurance.web.rest;

import com.idealsoft.insurance.InsuranceApplicationApp;
import com.idealsoft.insurance.domain.InsuranceObjectType;
import com.idealsoft.insurance.repository.InsuranceObjectTypeRepository;
import com.idealsoft.insurance.service.InsuranceObjectTypeService;
import com.idealsoft.insurance.service.dto.InsuranceObjectTypeDTO;
import com.idealsoft.insurance.service.mapper.InsuranceObjectTypeMapper;

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
 * Integration tests for the {@link InsuranceObjectTypeResource} REST controller.
 */
@SpringBootTest(classes = InsuranceApplicationApp.class)

@AutoConfigureMockMvc
@WithMockUser
public class InsuranceObjectTypeResourceIT {

    private static final String DEFAULT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION_AR = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION_AR = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION_EN = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION_EN = "BBBBBBBBBB";

    @Autowired
    private InsuranceObjectTypeRepository insuranceObjectTypeRepository;

    @Autowired
    private InsuranceObjectTypeMapper insuranceObjectTypeMapper;

    @Autowired
    private InsuranceObjectTypeService insuranceObjectTypeService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restInsuranceObjectTypeMockMvc;

    private InsuranceObjectType insuranceObjectType;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static InsuranceObjectType createEntity(EntityManager em) {
        InsuranceObjectType insuranceObjectType = new InsuranceObjectType()
            .code(DEFAULT_CODE)
            .descriptionAr(DEFAULT_DESCRIPTION_AR)
            .descriptionEn(DEFAULT_DESCRIPTION_EN);
        return insuranceObjectType;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static InsuranceObjectType createUpdatedEntity(EntityManager em) {
        InsuranceObjectType insuranceObjectType = new InsuranceObjectType()
            .code(UPDATED_CODE)
            .descriptionAr(UPDATED_DESCRIPTION_AR)
            .descriptionEn(UPDATED_DESCRIPTION_EN);
        return insuranceObjectType;
    }

    @BeforeEach
    public void initTest() {
        insuranceObjectType = createEntity(em);
    }

    @Test
    @Transactional
    public void createInsuranceObjectType() throws Exception {
        int databaseSizeBeforeCreate = insuranceObjectTypeRepository.findAll().size();

        // Create the InsuranceObjectType
        InsuranceObjectTypeDTO insuranceObjectTypeDTO = insuranceObjectTypeMapper.toDto(insuranceObjectType);
        restInsuranceObjectTypeMockMvc.perform(post("/api/insurance-object-types")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(insuranceObjectTypeDTO)))
            .andExpect(status().isCreated());

        // Validate the InsuranceObjectType in the database
        List<InsuranceObjectType> insuranceObjectTypeList = insuranceObjectTypeRepository.findAll();
        assertThat(insuranceObjectTypeList).hasSize(databaseSizeBeforeCreate + 1);
        InsuranceObjectType testInsuranceObjectType = insuranceObjectTypeList.get(insuranceObjectTypeList.size() - 1);
        assertThat(testInsuranceObjectType.getCode()).isEqualTo(DEFAULT_CODE);
        assertThat(testInsuranceObjectType.getDescriptionAr()).isEqualTo(DEFAULT_DESCRIPTION_AR);
        assertThat(testInsuranceObjectType.getDescriptionEn()).isEqualTo(DEFAULT_DESCRIPTION_EN);
    }

    @Test
    @Transactional
    public void createInsuranceObjectTypeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = insuranceObjectTypeRepository.findAll().size();

        // Create the InsuranceObjectType with an existing ID
        insuranceObjectType.setId(1L);
        InsuranceObjectTypeDTO insuranceObjectTypeDTO = insuranceObjectTypeMapper.toDto(insuranceObjectType);

        // An entity with an existing ID cannot be created, so this API call must fail
        restInsuranceObjectTypeMockMvc.perform(post("/api/insurance-object-types")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(insuranceObjectTypeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the InsuranceObjectType in the database
        List<InsuranceObjectType> insuranceObjectTypeList = insuranceObjectTypeRepository.findAll();
        assertThat(insuranceObjectTypeList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllInsuranceObjectTypes() throws Exception {
        // Initialize the database
        insuranceObjectTypeRepository.saveAndFlush(insuranceObjectType);

        // Get all the insuranceObjectTypeList
        restInsuranceObjectTypeMockMvc.perform(get("/api/insurance-object-types?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(insuranceObjectType.getId().intValue())))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE)))
            .andExpect(jsonPath("$.[*].descriptionAr").value(hasItem(DEFAULT_DESCRIPTION_AR)))
            .andExpect(jsonPath("$.[*].descriptionEn").value(hasItem(DEFAULT_DESCRIPTION_EN)));
    }
    
    @Test
    @Transactional
    public void getInsuranceObjectType() throws Exception {
        // Initialize the database
        insuranceObjectTypeRepository.saveAndFlush(insuranceObjectType);

        // Get the insuranceObjectType
        restInsuranceObjectTypeMockMvc.perform(get("/api/insurance-object-types/{id}", insuranceObjectType.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(insuranceObjectType.getId().intValue()))
            .andExpect(jsonPath("$.code").value(DEFAULT_CODE))
            .andExpect(jsonPath("$.descriptionAr").value(DEFAULT_DESCRIPTION_AR))
            .andExpect(jsonPath("$.descriptionEn").value(DEFAULT_DESCRIPTION_EN));
    }

    @Test
    @Transactional
    public void getNonExistingInsuranceObjectType() throws Exception {
        // Get the insuranceObjectType
        restInsuranceObjectTypeMockMvc.perform(get("/api/insurance-object-types/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateInsuranceObjectType() throws Exception {
        // Initialize the database
        insuranceObjectTypeRepository.saveAndFlush(insuranceObjectType);

        int databaseSizeBeforeUpdate = insuranceObjectTypeRepository.findAll().size();

        // Update the insuranceObjectType
        InsuranceObjectType updatedInsuranceObjectType = insuranceObjectTypeRepository.findById(insuranceObjectType.getId()).get();
        // Disconnect from session so that the updates on updatedInsuranceObjectType are not directly saved in db
        em.detach(updatedInsuranceObjectType);
        updatedInsuranceObjectType
            .code(UPDATED_CODE)
            .descriptionAr(UPDATED_DESCRIPTION_AR)
            .descriptionEn(UPDATED_DESCRIPTION_EN);
        InsuranceObjectTypeDTO insuranceObjectTypeDTO = insuranceObjectTypeMapper.toDto(updatedInsuranceObjectType);

        restInsuranceObjectTypeMockMvc.perform(put("/api/insurance-object-types")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(insuranceObjectTypeDTO)))
            .andExpect(status().isOk());

        // Validate the InsuranceObjectType in the database
        List<InsuranceObjectType> insuranceObjectTypeList = insuranceObjectTypeRepository.findAll();
        assertThat(insuranceObjectTypeList).hasSize(databaseSizeBeforeUpdate);
        InsuranceObjectType testInsuranceObjectType = insuranceObjectTypeList.get(insuranceObjectTypeList.size() - 1);
        assertThat(testInsuranceObjectType.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testInsuranceObjectType.getDescriptionAr()).isEqualTo(UPDATED_DESCRIPTION_AR);
        assertThat(testInsuranceObjectType.getDescriptionEn()).isEqualTo(UPDATED_DESCRIPTION_EN);
    }

    @Test
    @Transactional
    public void updateNonExistingInsuranceObjectType() throws Exception {
        int databaseSizeBeforeUpdate = insuranceObjectTypeRepository.findAll().size();

        // Create the InsuranceObjectType
        InsuranceObjectTypeDTO insuranceObjectTypeDTO = insuranceObjectTypeMapper.toDto(insuranceObjectType);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restInsuranceObjectTypeMockMvc.perform(put("/api/insurance-object-types")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(insuranceObjectTypeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the InsuranceObjectType in the database
        List<InsuranceObjectType> insuranceObjectTypeList = insuranceObjectTypeRepository.findAll();
        assertThat(insuranceObjectTypeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteInsuranceObjectType() throws Exception {
        // Initialize the database
        insuranceObjectTypeRepository.saveAndFlush(insuranceObjectType);

        int databaseSizeBeforeDelete = insuranceObjectTypeRepository.findAll().size();

        // Delete the insuranceObjectType
        restInsuranceObjectTypeMockMvc.perform(delete("/api/insurance-object-types/{id}", insuranceObjectType.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<InsuranceObjectType> insuranceObjectTypeList = insuranceObjectTypeRepository.findAll();
        assertThat(insuranceObjectTypeList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
