package com.idealsoft.insurance.web.rest;

import com.idealsoft.insurance.InsuranceApplicationApp;
import com.idealsoft.insurance.domain.InsuranceInstanceDetails;
import com.idealsoft.insurance.repository.InsuranceInstanceDetailsRepository;
import com.idealsoft.insurance.service.InsuranceInstanceDetailsService;
import com.idealsoft.insurance.service.dto.InsuranceInstanceDetailsDTO;
import com.idealsoft.insurance.service.mapper.InsuranceInstanceDetailsMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Base64Utils;
import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.idealsoft.insurance.domain.enumeration.InstanceDetailsStatus;
/**
 * Integration tests for the {@link InsuranceInstanceDetailsResource} REST controller.
 */
@SpringBootTest(classes = InsuranceApplicationApp.class)

@AutoConfigureMockMvc
@WithMockUser
public class InsuranceInstanceDetailsResourceIT {

    private static final byte[] DEFAULT_IMAGE = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_IMAGE = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_IMAGE_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_IMAGE_CONTENT_TYPE = "image/png";

    private static final String DEFAULT_COMMENTS = "AAAAAAAAAA";
    private static final String UPDATED_COMMENTS = "BBBBBBBBBB";

    private static final InstanceDetailsStatus DEFAULT_STATUS = InstanceDetailsStatus.Excellent;
    private static final InstanceDetailsStatus UPDATED_STATUS = InstanceDetailsStatus.VeryGood;

    @Autowired
    private InsuranceInstanceDetailsRepository insuranceInstanceDetailsRepository;

    @Autowired
    private InsuranceInstanceDetailsMapper insuranceInstanceDetailsMapper;

    @Autowired
    private InsuranceInstanceDetailsService insuranceInstanceDetailsService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restInsuranceInstanceDetailsMockMvc;

    private InsuranceInstanceDetails insuranceInstanceDetails;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static InsuranceInstanceDetails createEntity(EntityManager em) {
        InsuranceInstanceDetails insuranceInstanceDetails = new InsuranceInstanceDetails()
            .image(DEFAULT_IMAGE)
            .imageContentType(DEFAULT_IMAGE_CONTENT_TYPE)
            .comments(DEFAULT_COMMENTS)
            .status(DEFAULT_STATUS);
        return insuranceInstanceDetails;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static InsuranceInstanceDetails createUpdatedEntity(EntityManager em) {
        InsuranceInstanceDetails insuranceInstanceDetails = new InsuranceInstanceDetails()
            .image(UPDATED_IMAGE)
            .imageContentType(UPDATED_IMAGE_CONTENT_TYPE)
            .comments(UPDATED_COMMENTS)
            .status(UPDATED_STATUS);
        return insuranceInstanceDetails;
    }

    @BeforeEach
    public void initTest() {
        insuranceInstanceDetails = createEntity(em);
    }

    @Test
    @Transactional
    public void createInsuranceInstanceDetails() throws Exception {
        int databaseSizeBeforeCreate = insuranceInstanceDetailsRepository.findAll().size();

        // Create the InsuranceInstanceDetails
        InsuranceInstanceDetailsDTO insuranceInstanceDetailsDTO = insuranceInstanceDetailsMapper.toDto(insuranceInstanceDetails);
        restInsuranceInstanceDetailsMockMvc.perform(post("/api/insurance-instance-details")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(insuranceInstanceDetailsDTO)))
            .andExpect(status().isCreated());

        // Validate the InsuranceInstanceDetails in the database
        List<InsuranceInstanceDetails> insuranceInstanceDetailsList = insuranceInstanceDetailsRepository.findAll();
        assertThat(insuranceInstanceDetailsList).hasSize(databaseSizeBeforeCreate + 1);
        InsuranceInstanceDetails testInsuranceInstanceDetails = insuranceInstanceDetailsList.get(insuranceInstanceDetailsList.size() - 1);
        assertThat(testInsuranceInstanceDetails.getImage()).isEqualTo(DEFAULT_IMAGE);
        assertThat(testInsuranceInstanceDetails.getImageContentType()).isEqualTo(DEFAULT_IMAGE_CONTENT_TYPE);
        assertThat(testInsuranceInstanceDetails.getComments()).isEqualTo(DEFAULT_COMMENTS);
        assertThat(testInsuranceInstanceDetails.getStatus()).isEqualTo(DEFAULT_STATUS);
    }

    @Test
    @Transactional
    public void createInsuranceInstanceDetailsWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = insuranceInstanceDetailsRepository.findAll().size();

        // Create the InsuranceInstanceDetails with an existing ID
        insuranceInstanceDetails.setId(1L);
        InsuranceInstanceDetailsDTO insuranceInstanceDetailsDTO = insuranceInstanceDetailsMapper.toDto(insuranceInstanceDetails);

        // An entity with an existing ID cannot be created, so this API call must fail
        restInsuranceInstanceDetailsMockMvc.perform(post("/api/insurance-instance-details")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(insuranceInstanceDetailsDTO)))
            .andExpect(status().isBadRequest());

        // Validate the InsuranceInstanceDetails in the database
        List<InsuranceInstanceDetails> insuranceInstanceDetailsList = insuranceInstanceDetailsRepository.findAll();
        assertThat(insuranceInstanceDetailsList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllInsuranceInstanceDetails() throws Exception {
        // Initialize the database
        insuranceInstanceDetailsRepository.saveAndFlush(insuranceInstanceDetails);

        // Get all the insuranceInstanceDetailsList
        restInsuranceInstanceDetailsMockMvc.perform(get("/api/insurance-instance-details?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(insuranceInstanceDetails.getId().intValue())))
            .andExpect(jsonPath("$.[*].imageContentType").value(hasItem(DEFAULT_IMAGE_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].image").value(hasItem(Base64Utils.encodeToString(DEFAULT_IMAGE))))
            .andExpect(jsonPath("$.[*].comments").value(hasItem(DEFAULT_COMMENTS)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())));
    }
    
    @Test
    @Transactional
    public void getInsuranceInstanceDetails() throws Exception {
        // Initialize the database
        insuranceInstanceDetailsRepository.saveAndFlush(insuranceInstanceDetails);

        // Get the insuranceInstanceDetails
        restInsuranceInstanceDetailsMockMvc.perform(get("/api/insurance-instance-details/{id}", insuranceInstanceDetails.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(insuranceInstanceDetails.getId().intValue()))
            .andExpect(jsonPath("$.imageContentType").value(DEFAULT_IMAGE_CONTENT_TYPE))
            .andExpect(jsonPath("$.image").value(Base64Utils.encodeToString(DEFAULT_IMAGE)))
            .andExpect(jsonPath("$.comments").value(DEFAULT_COMMENTS))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingInsuranceInstanceDetails() throws Exception {
        // Get the insuranceInstanceDetails
        restInsuranceInstanceDetailsMockMvc.perform(get("/api/insurance-instance-details/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateInsuranceInstanceDetails() throws Exception {
        // Initialize the database
        insuranceInstanceDetailsRepository.saveAndFlush(insuranceInstanceDetails);

        int databaseSizeBeforeUpdate = insuranceInstanceDetailsRepository.findAll().size();

        // Update the insuranceInstanceDetails
        InsuranceInstanceDetails updatedInsuranceInstanceDetails = insuranceInstanceDetailsRepository.findById(insuranceInstanceDetails.getId()).get();
        // Disconnect from session so that the updates on updatedInsuranceInstanceDetails are not directly saved in db
        em.detach(updatedInsuranceInstanceDetails);
        updatedInsuranceInstanceDetails
            .image(UPDATED_IMAGE)
            .imageContentType(UPDATED_IMAGE_CONTENT_TYPE)
            .comments(UPDATED_COMMENTS)
            .status(UPDATED_STATUS);
        InsuranceInstanceDetailsDTO insuranceInstanceDetailsDTO = insuranceInstanceDetailsMapper.toDto(updatedInsuranceInstanceDetails);

        restInsuranceInstanceDetailsMockMvc.perform(put("/api/insurance-instance-details")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(insuranceInstanceDetailsDTO)))
            .andExpect(status().isOk());

        // Validate the InsuranceInstanceDetails in the database
        List<InsuranceInstanceDetails> insuranceInstanceDetailsList = insuranceInstanceDetailsRepository.findAll();
        assertThat(insuranceInstanceDetailsList).hasSize(databaseSizeBeforeUpdate);
        InsuranceInstanceDetails testInsuranceInstanceDetails = insuranceInstanceDetailsList.get(insuranceInstanceDetailsList.size() - 1);
        assertThat(testInsuranceInstanceDetails.getImage()).isEqualTo(UPDATED_IMAGE);
        assertThat(testInsuranceInstanceDetails.getImageContentType()).isEqualTo(UPDATED_IMAGE_CONTENT_TYPE);
        assertThat(testInsuranceInstanceDetails.getComments()).isEqualTo(UPDATED_COMMENTS);
        assertThat(testInsuranceInstanceDetails.getStatus()).isEqualTo(UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void updateNonExistingInsuranceInstanceDetails() throws Exception {
        int databaseSizeBeforeUpdate = insuranceInstanceDetailsRepository.findAll().size();

        // Create the InsuranceInstanceDetails
        InsuranceInstanceDetailsDTO insuranceInstanceDetailsDTO = insuranceInstanceDetailsMapper.toDto(insuranceInstanceDetails);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restInsuranceInstanceDetailsMockMvc.perform(put("/api/insurance-instance-details")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(insuranceInstanceDetailsDTO)))
            .andExpect(status().isBadRequest());

        // Validate the InsuranceInstanceDetails in the database
        List<InsuranceInstanceDetails> insuranceInstanceDetailsList = insuranceInstanceDetailsRepository.findAll();
        assertThat(insuranceInstanceDetailsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteInsuranceInstanceDetails() throws Exception {
        // Initialize the database
        insuranceInstanceDetailsRepository.saveAndFlush(insuranceInstanceDetails);

        int databaseSizeBeforeDelete = insuranceInstanceDetailsRepository.findAll().size();

        // Delete the insuranceInstanceDetails
        restInsuranceInstanceDetailsMockMvc.perform(delete("/api/insurance-instance-details/{id}", insuranceInstanceDetails.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<InsuranceInstanceDetails> insuranceInstanceDetailsList = insuranceInstanceDetailsRepository.findAll();
        assertThat(insuranceInstanceDetailsList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
