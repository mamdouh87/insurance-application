package com.idealsoft.insurance.web.rest;

import com.idealsoft.insurance.InsuranceApplicationApp;
import com.idealsoft.insurance.domain.InsuranceInstance;
import com.idealsoft.insurance.repository.InsuranceInstanceRepository;

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
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link InsuranceInstanceResource} REST controller.
 */
@SpringBootTest(classes = InsuranceApplicationApp.class)

@AutoConfigureMockMvc
@WithMockUser
public class InsuranceInstanceResourceIT {

    private static final Instant DEFAULT_INSTANCE_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_INSTANCE_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    @Autowired
    private InsuranceInstanceRepository insuranceInstanceRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restInsuranceInstanceMockMvc;

    private InsuranceInstance insuranceInstance;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static InsuranceInstance createEntity(EntityManager em) {
        InsuranceInstance insuranceInstance = new InsuranceInstance()
            .instanceDate(DEFAULT_INSTANCE_DATE);
        return insuranceInstance;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static InsuranceInstance createUpdatedEntity(EntityManager em) {
        InsuranceInstance insuranceInstance = new InsuranceInstance()
            .instanceDate(UPDATED_INSTANCE_DATE);
        return insuranceInstance;
    }

    @BeforeEach
    public void initTest() {
        insuranceInstance = createEntity(em);
    }

    @Test
    @Transactional
    public void createInsuranceInstance() throws Exception {
        int databaseSizeBeforeCreate = insuranceInstanceRepository.findAll().size();

        // Create the InsuranceInstance
        restInsuranceInstanceMockMvc.perform(post("/api/insurance-instances")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(insuranceInstance)))
            .andExpect(status().isCreated());

        // Validate the InsuranceInstance in the database
        List<InsuranceInstance> insuranceInstanceList = insuranceInstanceRepository.findAll();
        assertThat(insuranceInstanceList).hasSize(databaseSizeBeforeCreate + 1);
        InsuranceInstance testInsuranceInstance = insuranceInstanceList.get(insuranceInstanceList.size() - 1);
        assertThat(testInsuranceInstance.getInstanceDate()).isEqualTo(DEFAULT_INSTANCE_DATE);
    }

    @Test
    @Transactional
    public void createInsuranceInstanceWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = insuranceInstanceRepository.findAll().size();

        // Create the InsuranceInstance with an existing ID
        insuranceInstance.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restInsuranceInstanceMockMvc.perform(post("/api/insurance-instances")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(insuranceInstance)))
            .andExpect(status().isBadRequest());

        // Validate the InsuranceInstance in the database
        List<InsuranceInstance> insuranceInstanceList = insuranceInstanceRepository.findAll();
        assertThat(insuranceInstanceList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllInsuranceInstances() throws Exception {
        // Initialize the database
        insuranceInstanceRepository.saveAndFlush(insuranceInstance);

        // Get all the insuranceInstanceList
        restInsuranceInstanceMockMvc.perform(get("/api/insurance-instances?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(insuranceInstance.getId().intValue())))
            .andExpect(jsonPath("$.[*].instanceDate").value(hasItem(DEFAULT_INSTANCE_DATE.toString())));
    }
    
    @Test
    @Transactional
    public void getInsuranceInstance() throws Exception {
        // Initialize the database
        insuranceInstanceRepository.saveAndFlush(insuranceInstance);

        // Get the insuranceInstance
        restInsuranceInstanceMockMvc.perform(get("/api/insurance-instances/{id}", insuranceInstance.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(insuranceInstance.getId().intValue()))
            .andExpect(jsonPath("$.instanceDate").value(DEFAULT_INSTANCE_DATE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingInsuranceInstance() throws Exception {
        // Get the insuranceInstance
        restInsuranceInstanceMockMvc.perform(get("/api/insurance-instances/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateInsuranceInstance() throws Exception {
        // Initialize the database
        insuranceInstanceRepository.saveAndFlush(insuranceInstance);

        int databaseSizeBeforeUpdate = insuranceInstanceRepository.findAll().size();

        // Update the insuranceInstance
        InsuranceInstance updatedInsuranceInstance = insuranceInstanceRepository.findById(insuranceInstance.getId()).get();
        // Disconnect from session so that the updates on updatedInsuranceInstance are not directly saved in db
        em.detach(updatedInsuranceInstance);
        updatedInsuranceInstance
            .instanceDate(UPDATED_INSTANCE_DATE);

        restInsuranceInstanceMockMvc.perform(put("/api/insurance-instances")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedInsuranceInstance)))
            .andExpect(status().isOk());

        // Validate the InsuranceInstance in the database
        List<InsuranceInstance> insuranceInstanceList = insuranceInstanceRepository.findAll();
        assertThat(insuranceInstanceList).hasSize(databaseSizeBeforeUpdate);
        InsuranceInstance testInsuranceInstance = insuranceInstanceList.get(insuranceInstanceList.size() - 1);
        assertThat(testInsuranceInstance.getInstanceDate()).isEqualTo(UPDATED_INSTANCE_DATE);
    }

    @Test
    @Transactional
    public void updateNonExistingInsuranceInstance() throws Exception {
        int databaseSizeBeforeUpdate = insuranceInstanceRepository.findAll().size();

        // Create the InsuranceInstance

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restInsuranceInstanceMockMvc.perform(put("/api/insurance-instances")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(insuranceInstance)))
            .andExpect(status().isBadRequest());

        // Validate the InsuranceInstance in the database
        List<InsuranceInstance> insuranceInstanceList = insuranceInstanceRepository.findAll();
        assertThat(insuranceInstanceList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteInsuranceInstance() throws Exception {
        // Initialize the database
        insuranceInstanceRepository.saveAndFlush(insuranceInstance);

        int databaseSizeBeforeDelete = insuranceInstanceRepository.findAll().size();

        // Delete the insuranceInstance
        restInsuranceInstanceMockMvc.perform(delete("/api/insurance-instances/{id}", insuranceInstance.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<InsuranceInstance> insuranceInstanceList = insuranceInstanceRepository.findAll();
        assertThat(insuranceInstanceList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
