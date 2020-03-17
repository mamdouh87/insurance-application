package com.idealsoft.insurance.web.rest;

import com.idealsoft.insurance.domain.InsuranceInstanceDetails;
import com.idealsoft.insurance.repository.InsuranceInstanceDetailsRepository;
import com.idealsoft.insurance.web.rest.errors.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.idealsoft.insurance.domain.InsuranceInstanceDetails}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class InsuranceInstanceDetailsResource {

    private final Logger log = LoggerFactory.getLogger(InsuranceInstanceDetailsResource.class);

    private static final String ENTITY_NAME = "insuranceInstanceDetails";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final InsuranceInstanceDetailsRepository insuranceInstanceDetailsRepository;

    public InsuranceInstanceDetailsResource(InsuranceInstanceDetailsRepository insuranceInstanceDetailsRepository) {
        this.insuranceInstanceDetailsRepository = insuranceInstanceDetailsRepository;
    }

    /**
     * {@code POST  /insurance-instance-details} : Create a new insuranceInstanceDetails.
     *
     * @param insuranceInstanceDetails the insuranceInstanceDetails to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new insuranceInstanceDetails, or with status {@code 400 (Bad Request)} if the insuranceInstanceDetails has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/insurance-instance-details")
    public ResponseEntity<InsuranceInstanceDetails> createInsuranceInstanceDetails(@RequestBody InsuranceInstanceDetails insuranceInstanceDetails) throws URISyntaxException {
        log.debug("REST request to save InsuranceInstanceDetails : {}", insuranceInstanceDetails);
        if (insuranceInstanceDetails.getId() != null) {
            throw new BadRequestAlertException("A new insuranceInstanceDetails cannot already have an ID", ENTITY_NAME, "idexists");
        }
        InsuranceInstanceDetails result = insuranceInstanceDetailsRepository.save(insuranceInstanceDetails);
        return ResponseEntity.created(new URI("/api/insurance-instance-details/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /insurance-instance-details} : Updates an existing insuranceInstanceDetails.
     *
     * @param insuranceInstanceDetails the insuranceInstanceDetails to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated insuranceInstanceDetails,
     * or with status {@code 400 (Bad Request)} if the insuranceInstanceDetails is not valid,
     * or with status {@code 500 (Internal Server Error)} if the insuranceInstanceDetails couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/insurance-instance-details")
    public ResponseEntity<InsuranceInstanceDetails> updateInsuranceInstanceDetails(@RequestBody InsuranceInstanceDetails insuranceInstanceDetails) throws URISyntaxException {
        log.debug("REST request to update InsuranceInstanceDetails : {}", insuranceInstanceDetails);
        if (insuranceInstanceDetails.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        InsuranceInstanceDetails result = insuranceInstanceDetailsRepository.save(insuranceInstanceDetails);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, insuranceInstanceDetails.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /insurance-instance-details} : get all the insuranceInstanceDetails.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of insuranceInstanceDetails in body.
     */
    @GetMapping("/insurance-instance-details")
    public List<InsuranceInstanceDetails> getAllInsuranceInstanceDetails() {
        log.debug("REST request to get all InsuranceInstanceDetails");
        return insuranceInstanceDetailsRepository.findAll();
    }

    /**
     * {@code GET  /insurance-instance-details/:id} : get the "id" insuranceInstanceDetails.
     *
     * @param id the id of the insuranceInstanceDetails to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the insuranceInstanceDetails, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/insurance-instance-details/{id}")
    public ResponseEntity<InsuranceInstanceDetails> getInsuranceInstanceDetails(@PathVariable Long id) {
        log.debug("REST request to get InsuranceInstanceDetails : {}", id);
        Optional<InsuranceInstanceDetails> insuranceInstanceDetails = insuranceInstanceDetailsRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(insuranceInstanceDetails);
    }

    /**
     * {@code DELETE  /insurance-instance-details/:id} : delete the "id" insuranceInstanceDetails.
     *
     * @param id the id of the insuranceInstanceDetails to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/insurance-instance-details/{id}")
    public ResponseEntity<Void> deleteInsuranceInstanceDetails(@PathVariable Long id) {
        log.debug("REST request to delete InsuranceInstanceDetails : {}", id);
        insuranceInstanceDetailsRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
