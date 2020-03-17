package com.idealsoft.insurance.web.rest;

import com.idealsoft.insurance.domain.InsuranceObject;
import com.idealsoft.insurance.repository.InsuranceObjectRepository;
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
 * REST controller for managing {@link com.idealsoft.insurance.domain.InsuranceObject}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class InsuranceObjectResource {

    private final Logger log = LoggerFactory.getLogger(InsuranceObjectResource.class);

    private static final String ENTITY_NAME = "insuranceObject";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final InsuranceObjectRepository insuranceObjectRepository;

    public InsuranceObjectResource(InsuranceObjectRepository insuranceObjectRepository) {
        this.insuranceObjectRepository = insuranceObjectRepository;
    }

    /**
     * {@code POST  /insurance-objects} : Create a new insuranceObject.
     *
     * @param insuranceObject the insuranceObject to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new insuranceObject, or with status {@code 400 (Bad Request)} if the insuranceObject has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/insurance-objects")
    public ResponseEntity<InsuranceObject> createInsuranceObject(@RequestBody InsuranceObject insuranceObject) throws URISyntaxException {
        log.debug("REST request to save InsuranceObject : {}", insuranceObject);
        if (insuranceObject.getId() != null) {
            throw new BadRequestAlertException("A new insuranceObject cannot already have an ID", ENTITY_NAME, "idexists");
        }
        InsuranceObject result = insuranceObjectRepository.save(insuranceObject);
        return ResponseEntity.created(new URI("/api/insurance-objects/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /insurance-objects} : Updates an existing insuranceObject.
     *
     * @param insuranceObject the insuranceObject to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated insuranceObject,
     * or with status {@code 400 (Bad Request)} if the insuranceObject is not valid,
     * or with status {@code 500 (Internal Server Error)} if the insuranceObject couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/insurance-objects")
    public ResponseEntity<InsuranceObject> updateInsuranceObject(@RequestBody InsuranceObject insuranceObject) throws URISyntaxException {
        log.debug("REST request to update InsuranceObject : {}", insuranceObject);
        if (insuranceObject.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        InsuranceObject result = insuranceObjectRepository.save(insuranceObject);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, insuranceObject.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /insurance-objects} : get all the insuranceObjects.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of insuranceObjects in body.
     */
    @GetMapping("/insurance-objects")
    public List<InsuranceObject> getAllInsuranceObjects() {
        log.debug("REST request to get all InsuranceObjects");
        return insuranceObjectRepository.findAll();
    }

    /**
     * {@code GET  /insurance-objects/:id} : get the "id" insuranceObject.
     *
     * @param id the id of the insuranceObject to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the insuranceObject, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/insurance-objects/{id}")
    public ResponseEntity<InsuranceObject> getInsuranceObject(@PathVariable Long id) {
        log.debug("REST request to get InsuranceObject : {}", id);
        Optional<InsuranceObject> insuranceObject = insuranceObjectRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(insuranceObject);
    }

    /**
     * {@code DELETE  /insurance-objects/:id} : delete the "id" insuranceObject.
     *
     * @param id the id of the insuranceObject to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/insurance-objects/{id}")
    public ResponseEntity<Void> deleteInsuranceObject(@PathVariable Long id) {
        log.debug("REST request to delete InsuranceObject : {}", id);
        insuranceObjectRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
