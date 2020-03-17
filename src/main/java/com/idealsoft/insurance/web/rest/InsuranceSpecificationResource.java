package com.idealsoft.insurance.web.rest;

import com.idealsoft.insurance.service.InsuranceSpecificationService;
import com.idealsoft.insurance.web.rest.errors.BadRequestAlertException;
import com.idealsoft.insurance.service.dto.InsuranceSpecificationDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.idealsoft.insurance.domain.InsuranceSpecification}.
 */
@RestController
@RequestMapping("/api")
public class InsuranceSpecificationResource {

    private final Logger log = LoggerFactory.getLogger(InsuranceSpecificationResource.class);

    private static final String ENTITY_NAME = "insuranceSpecification";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final InsuranceSpecificationService insuranceSpecificationService;

    public InsuranceSpecificationResource(InsuranceSpecificationService insuranceSpecificationService) {
        this.insuranceSpecificationService = insuranceSpecificationService;
    }

    /**
     * {@code POST  /insurance-specifications} : Create a new insuranceSpecification.
     *
     * @param insuranceSpecificationDTO the insuranceSpecificationDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new insuranceSpecificationDTO, or with status {@code 400 (Bad Request)} if the insuranceSpecification has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/insurance-specifications")
    public ResponseEntity<InsuranceSpecificationDTO> createInsuranceSpecification(@RequestBody InsuranceSpecificationDTO insuranceSpecificationDTO) throws URISyntaxException {
        log.debug("REST request to save InsuranceSpecification : {}", insuranceSpecificationDTO);
        if (insuranceSpecificationDTO.getId() != null) {
            throw new BadRequestAlertException("A new insuranceSpecification cannot already have an ID", ENTITY_NAME, "idexists");
        }
        InsuranceSpecificationDTO result = insuranceSpecificationService.save(insuranceSpecificationDTO);
        return ResponseEntity.created(new URI("/api/insurance-specifications/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /insurance-specifications} : Updates an existing insuranceSpecification.
     *
     * @param insuranceSpecificationDTO the insuranceSpecificationDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated insuranceSpecificationDTO,
     * or with status {@code 400 (Bad Request)} if the insuranceSpecificationDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the insuranceSpecificationDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/insurance-specifications")
    public ResponseEntity<InsuranceSpecificationDTO> updateInsuranceSpecification(@RequestBody InsuranceSpecificationDTO insuranceSpecificationDTO) throws URISyntaxException {
        log.debug("REST request to update InsuranceSpecification : {}", insuranceSpecificationDTO);
        if (insuranceSpecificationDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        InsuranceSpecificationDTO result = insuranceSpecificationService.save(insuranceSpecificationDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, insuranceSpecificationDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /insurance-specifications} : get all the insuranceSpecifications.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of insuranceSpecifications in body.
     */
    @GetMapping("/insurance-specifications")
    public ResponseEntity<List<InsuranceSpecificationDTO>> getAllInsuranceSpecifications(Pageable pageable) {
        log.debug("REST request to get a page of InsuranceSpecifications");
        Page<InsuranceSpecificationDTO> page = insuranceSpecificationService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /insurance-specifications/:id} : get the "id" insuranceSpecification.
     *
     * @param id the id of the insuranceSpecificationDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the insuranceSpecificationDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/insurance-specifications/{id}")
    public ResponseEntity<InsuranceSpecificationDTO> getInsuranceSpecification(@PathVariable Long id) {
        log.debug("REST request to get InsuranceSpecification : {}", id);
        Optional<InsuranceSpecificationDTO> insuranceSpecificationDTO = insuranceSpecificationService.findOne(id);
        return ResponseUtil.wrapOrNotFound(insuranceSpecificationDTO);
    }

    /**
     * {@code DELETE  /insurance-specifications/:id} : delete the "id" insuranceSpecification.
     *
     * @param id the id of the insuranceSpecificationDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/insurance-specifications/{id}")
    public ResponseEntity<Void> deleteInsuranceSpecification(@PathVariable Long id) {
        log.debug("REST request to delete InsuranceSpecification : {}", id);
        insuranceSpecificationService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
