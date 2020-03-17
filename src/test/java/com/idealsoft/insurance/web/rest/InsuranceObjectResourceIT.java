package com.idealsoft.insurance.web.rest;

import com.idealsoft.insurance.InsuranceApplicationApp;
import com.idealsoft.insurance.domain.InsuranceObject;
import com.idealsoft.insurance.repository.InsuranceObjectRepository;
import com.idealsoft.insurance.service.InsuranceObjectService;
import com.idealsoft.insurance.service.dto.InsuranceObjectDTO;
import com.idealsoft.insurance.service.mapper.InsuranceObjectMapper;

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
 * Integration tests for the {@link InsuranceObjectResource} REST controller.
 */
@SpringBootTest(classes = InsuranceApplicationApp.class)

@AutoConfigureMockMvc
@WithMockUser
public class InsuranceObjectResourceIT {

    private static final String DEFAULT_IDENTIFIER_1 = "AAAAAAAAAA";
    private static final String UPDATED_IDENTIFIER_1 = "BBBBBBBBBB";

    private static final String DEFAULT_IDENTIFIER_2 = "AAAAAAAAAA";
    private static final String UPDATED_IDENTIFIER_2 = "BBBBBBBBBB";

    private static final String DEFAULT_IDENTIFIER_3 = "AAAAAAAAAA";
    private static final String UPDATED_IDENTIFIER_3 = "BBBBBBBBBB";

    @Autowired
    private InsuranceObjectRepository insuranceObjectRepository;

    @Autowired
    private InsuranceObjectMapper insuranceObjectMapper;

    @Autowired
    private InsuranceObjectService insuranceObjectService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restInsuranceObjectMockMvc;

    private InsuranceObject insuranceObject;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static InsuranceObject createEntity(EntityManager em) {
        InsuranceObject insuranceObject = new InsuranceObject()
            .identifier1(DEFAULT_IDENTIFIER_1)
            .identifier2(DEFAULT_IDENTIFIER_2)
            .identifier3(DEFAULT_IDENTIFIER_3);
        return insuranceObject;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static InsuranceObject createUpdatedEntity(EntityManager em) {
        InsuranceObject insuranceObject = new InsuranceObject()
            .identifier1(UPDATED_IDENTIFIER_1)
            .identifier2(UPDATED_IDENTIFIER_2)
            .identifier3(UPDATED_IDENTIFIER_3);
        return insuranceObject;
    }

    @BeforeEach
    public void initTest() {
        insuranceObject = createEntity(em);
    }

    @Test
    @Transactional
    public void createInsuranceObject() throws Exception {
        int databaseSizeBeforeCreate = insuranceObjectRepository.findAll().size();

        // Create the InsuranceObject
        InsuranceObjectDTO insuranceObjectDTO = insuranceObjectMapper.toDto(insuranceObject);
        restInsuranceObjectMockMvc.perform(post("/api/insurance-objects")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(insuranceObjectDTO)))
            .andExpect(status().isCreated());

        // Validate the InsuranceObject in the database
        List<InsuranceObject> insuranceObjectList = insuranceObjectRepository.findAll();
        assertThat(insuranceObjectList).hasSize(databaseSizeBeforeCreate + 1);
        InsuranceObject testInsuranceObject = insuranceObjectList.get(insuranceObjectList.size() - 1);
        assertThat(testInsuranceObject.getIdentifier1()).isEqualTo(DEFAULT_IDENTIFIER_1);
        assertThat(testInsuranceObject.getIdentifier2()).isEqualTo(DEFAULT_IDENTIFIER_2);
        assertThat(testInsuranceObject.getIdentifier3()).isEqualTo(DEFAULT_IDENTIFIER_3);
    }

    @Test
    @Transactional
    public void createInsuranceObjectWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = insuranceObjectRepository.findAll().size();

        // Create the InsuranceObject with an existing ID
        insuranceObject.setId(1L);
        InsuranceObjectDTO insuranceObjectDTO = insuranceObjectMapper.toDto(insuranceObject);

        // An entity with an existing ID cannot be created, so this API call must fail
        restInsuranceObjectMockMvc.perform(post("/api/insurance-objects")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(insuranceObjectDTO)))
            .andExpect(status().isBadRequest());

        // Validate the InsuranceObject in the database
        List<InsuranceObject> insuranceObjectList = insuranceObjectRepository.findAll();
        assertThat(insuranceObjectList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllInsuranceObjects() throws Exception {
        // Initialize the database
        insuranceObjectRepository.saveAndFlush(insuranceObject);

        // Get all the insuranceObjectList
        restInsuranceObjectMockMvc.perform(get("/api/insurance-objects?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(insuranceObject.getId().intValue())))
            .andExpect(jsonPath("$.[*].identifier1").value(hasItem(DEFAULT_IDENTIFIER_1)))
            .andExpect(jsonPath("$.[*].identifier2").value(hasItem(DEFAULT_IDENTIFIER_2)))
            .andExpect(jsonPath("$.[*].identifier3").value(hasItem(DEFAULT_IDENTIFIER_3)));
    }
    
    @Test
    @Transactional
    public void getInsuranceObject() throws Exception {
        // Initialize the database
        insuranceObjectRepository.saveAndFlush(insuranceObject);

        // Get the insuranceObject
        restInsuranceObjectMockMvc.perform(get("/api/insurance-objects/{id}", insuranceObject.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(insuranceObject.getId().intValue()))
            .andExpect(jsonPath("$.identifier1").value(DEFAULT_IDENTIFIER_1))
            .andExpect(jsonPath("$.identifier2").value(DEFAULT_IDENTIFIER_2))
            .andExpect(jsonPath("$.identifier3").value(DEFAULT_IDENTIFIER_3));
    }

    @Test
    @Transactional
    public void getNonExistingInsuranceObject() throws Exception {
        // Get the insuranceObject
        restInsuranceObjectMockMvc.perform(get("/api/insurance-objects/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateInsuranceObject() throws Exception {
        // Initialize the database
        insuranceObjectRepository.saveAndFlush(insuranceObject);

        int databaseSizeBeforeUpdate = insuranceObjectRepository.findAll().size();

        // Update the insuranceObject
        InsuranceObject updatedInsuranceObject = insuranceObjectRepository.findById(insuranceObject.getId()).get();
        // Disconnect from session so that the updates on updatedInsuranceObject are not directly saved in db
        em.detach(updatedInsuranceObject);
        updatedInsuranceObject
            .identifier1(UPDATED_IDENTIFIER_1)
            .identifier2(UPDATED_IDENTIFIER_2)
            .identifier3(UPDATED_IDENTIFIER_3);
        InsuranceObjectDTO insuranceObjectDTO = insuranceObjectMapper.toDto(updatedInsuranceObject);

        restInsuranceObjectMockMvc.perform(put("/api/insurance-objects")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(insuranceObjectDTO)))
            .andExpect(status().isOk());

        // Validate the InsuranceObject in the database
        List<InsuranceObject> insuranceObjectList = insuranceObjectRepository.findAll();
        assertThat(insuranceObjectList).hasSize(databaseSizeBeforeUpdate);
        InsuranceObject testInsuranceObject = insuranceObjectList.get(insuranceObjectList.size() - 1);
        assertThat(testInsuranceObject.getIdentifier1()).isEqualTo(UPDATED_IDENTIFIER_1);
        assertThat(testInsuranceObject.getIdentifier2()).isEqualTo(UPDATED_IDENTIFIER_2);
        assertThat(testInsuranceObject.getIdentifier3()).isEqualTo(UPDATED_IDENTIFIER_3);
    }

    @Test
    @Transactional
    public void updateNonExistingInsuranceObject() throws Exception {
        int databaseSizeBeforeUpdate = insuranceObjectRepository.findAll().size();

        // Create the InsuranceObject
        InsuranceObjectDTO insuranceObjectDTO = insuranceObjectMapper.toDto(insuranceObject);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restInsuranceObjectMockMvc.perform(put("/api/insurance-objects")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(insuranceObjectDTO)))
            .andExpect(status().isBadRequest());

        // Validate the InsuranceObject in the database
        List<InsuranceObject> insuranceObjectList = insuranceObjectRepository.findAll();
        assertThat(insuranceObjectList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteInsuranceObject() throws Exception {
        // Initialize the database
        insuranceObjectRepository.saveAndFlush(insuranceObject);

        int databaseSizeBeforeDelete = insuranceObjectRepository.findAll().size();

        // Delete the insuranceObject
        restInsuranceObjectMockMvc.perform(delete("/api/insurance-objects/{id}", insuranceObject.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<InsuranceObject> insuranceObjectList = insuranceObjectRepository.findAll();
        assertThat(insuranceObjectList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
