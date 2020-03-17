package com.idealsoft.insurance.web.rest;

import com.idealsoft.insurance.service.InsuranceInstanceDetailsService;
import com.idealsoft.insurance.web.rest.errors.BadRequestAlertException;
import com.idealsoft.insurance.service.dto.InsuranceInstanceDetailsDTO;

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
 * REST controller for managing {@link com.idealsoft.insurance.domain.InsuranceInstanceDetails}.
 */
@RestController
@RequestMapping("/api")
public class InsuranceInstanceDetailsResource {

    private final Logger log = LoggerFactory.getLogger(InsuranceInstanceDetailsResource.class);

    private static final String ENTITY_NAME = "insuranceInstanceDetails";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final InsuranceInstanceDetailsService insuranceInstanceDetailsService;

    public InsuranceInstanceDetailsResource(InsuranceInstanceDetailsService insuranceInstanceDetailsService) {
        this.insuranceInstanceDetailsService = insuranceInstanceDetailsService;
    }

    /**
     * {@code POST  /insurance-instance-details} : Create a new insuranceInstanceDetails.
     *
     * @param insuranceInstanceDetailsDTO the insuranceInstanceDetailsDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new insuranceInstanceDetailsDTO, or with status {@code 400 (Bad Request)} if the insuranceInstanceDetails has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/insurance-instance-details")
    public ResponseEntity<InsuranceInstanceDetailsDTO> createInsuranceInstanceDetails(@RequestBody InsuranceInstanceDetailsDTO insuranceInstanceDetailsDTO) throws URISyntaxException {
        log.debug("REST request to save InsuranceInstanceDetails : {}", insuranceInstanceDetailsDTO);
        if (insuranceInstanceDetailsDTO.getId() != null) {
            throw new BadRequestAlertException("A new insuranceInstanceDetails cannot already have an ID", ENTITY_NAME, "idexists");
        }
        InsuranceInstanceDetailsDTO result = insuranceInstanceDetailsService.save(insuranceInstanceDetailsDTO);
        return ResponseEntity.created(new URI("/api/insurance-instance-details/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /insurance-instance-details} : Updates an existing insuranceInstanceDetails.
     *
     * @param insuranceInstanceDetailsDTO the insuranceInstanceDetailsDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated insuranceInstanceDetailsDTO,
     * or with status {@code 400 (Bad Request)} if the insuranceInstanceDetailsDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the insuranceInstanceDetailsDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/insurance-instance-details")
    public ResponseEntity<InsuranceInstanceDetailsDTO> updateInsuranceInstanceDetails(@RequestBody InsuranceInstanceDetailsDTO insuranceInstanceDetailsDTO) throws URISyntaxException {
        log.debug("REST request to update InsuranceInstanceDetails : {}", insuranceInstanceDetailsDTO);
        if (insuranceInstanceDetailsDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        InsuranceInstanceDetailsDTO result = insuranceInstanceDetailsService.save(insuranceInstanceDetailsDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, insuranceInstanceDetailsDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /insurance-instance-details} : get all the insuranceInstanceDetails.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of insuranceInstanceDetails in body.
     */
    @GetMapping("/insurance-instance-details")
    public ResponseEntity<List<InsuranceInstanceDetailsDTO>> getAllInsuranceInstanceDetails(Pageable pageable) {
        log.debug("REST request to get a page of InsuranceInstanceDetails");
        Page<InsuranceInstanceDetailsDTO> page = insuranceInstanceDetailsService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /insurance-instance-details/:id} : get the "id" insuranceInstanceDetails.
     *
     * @param id the id of the insuranceInstanceDetailsDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the insuranceInstanceDetailsDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/insurance-instance-details/{id}")
    public ResponseEntity<InsuranceInstanceDetailsDTO> getInsuranceInstanceDetails(@PathVariable Long id) {
        log.debug("REST request to get InsuranceInstanceDetails : {}", id);
        Optional<InsuranceInstanceDetailsDTO> insuranceInstanceDetailsDTO = insuranceInstanceDetailsService.findOne(id);
        return ResponseUtil.wrapOrNotFound(insuranceInstanceDetailsDTO);
    }

    /**
     * {@code DELETE  /insurance-instance-details/:id} : delete the "id" insuranceInstanceDetails.
     *
     * @param id the id of the insuranceInstanceDetailsDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/insurance-instance-details/{id}")
    public ResponseEntity<Void> deleteInsuranceInstanceDetails(@PathVariable Long id) {
        log.debug("REST request to delete InsuranceInstanceDetails : {}", id);
        insuranceInstanceDetailsService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
