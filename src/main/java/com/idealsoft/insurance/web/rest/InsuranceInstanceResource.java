package com.idealsoft.insurance.web.rest;

import com.idealsoft.insurance.service.InsuranceInstanceService;
import com.idealsoft.insurance.web.rest.errors.BadRequestAlertException;
import com.idealsoft.insurance.service.dto.InsuranceInstanceDTO;

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
 * REST controller for managing {@link com.idealsoft.insurance.domain.InsuranceInstance}.
 */
@RestController
@RequestMapping("/api")
public class InsuranceInstanceResource {

    private final Logger log = LoggerFactory.getLogger(InsuranceInstanceResource.class);

    private static final String ENTITY_NAME = "insuranceInstance";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final InsuranceInstanceService insuranceInstanceService;

    public InsuranceInstanceResource(InsuranceInstanceService insuranceInstanceService) {
        this.insuranceInstanceService = insuranceInstanceService;
    }

    /**
     * {@code POST  /insurance-instances} : Create a new insuranceInstance.
     *
     * @param insuranceInstanceDTO the insuranceInstanceDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new insuranceInstanceDTO, or with status {@code 400 (Bad Request)} if the insuranceInstance has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/insurance-instances")
    public ResponseEntity<InsuranceInstanceDTO> createInsuranceInstance(@RequestBody InsuranceInstanceDTO insuranceInstanceDTO) throws URISyntaxException {
        log.debug("REST request to save InsuranceInstance : {}", insuranceInstanceDTO);
        if (insuranceInstanceDTO.getId() != null) {
            throw new BadRequestAlertException("A new insuranceInstance cannot already have an ID", ENTITY_NAME, "idexists");
        }
        InsuranceInstanceDTO result = insuranceInstanceService.save(insuranceInstanceDTO);
        return ResponseEntity.created(new URI("/api/insurance-instances/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }
    /**
     * {@code POST  /insurance-instances} : Create a new insuranceInstance.
     *
     * @param insuranceInstanceDTO the insuranceInstanceDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new insuranceInstanceDTO, or with status {@code 400 (Bad Request)} if the insuranceInstance has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/insurance-instances-with-details")
    public ResponseEntity<InsuranceInstanceDTO> createInsuranceInstanceWithDetails(@RequestBody InsuranceInstanceDTO insuranceInstanceDTO) throws URISyntaxException {
        log.debug("REST request to save InsuranceInstance : {}", insuranceInstanceDTO);
        if (insuranceInstanceDTO.getId() != null) {
            throw new BadRequestAlertException("A new insuranceInstance cannot already have an ID", ENTITY_NAME, "idexists");
        }
        InsuranceInstanceDTO result = insuranceInstanceService.createInsuranceInstanceWithDetails(insuranceInstanceDTO);
        return ResponseEntity.created(new URI("/api/insurance-instances/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /insurance-instances} : Updates an existing insuranceInstance.
     *
     * @param insuranceInstanceDTO the insuranceInstanceDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated insuranceInstanceDTO,
     * or with status {@code 400 (Bad Request)} if the insuranceInstanceDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the insuranceInstanceDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/insurance-instances")
    public ResponseEntity<InsuranceInstanceDTO> updateInsuranceInstance(@RequestBody InsuranceInstanceDTO insuranceInstanceDTO) throws URISyntaxException {
        log.debug("REST request to update InsuranceInstance : {}", insuranceInstanceDTO);
        if (insuranceInstanceDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        InsuranceInstanceDTO result = insuranceInstanceService.save(insuranceInstanceDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, insuranceInstanceDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /insurance-instances} : get all the insuranceInstances.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of insuranceInstances in body.
     */
    @GetMapping("/insurance-instances")
    public ResponseEntity<List<InsuranceInstanceDTO>> getAllInsuranceInstances(Pageable pageable) {
        log.debug("REST request to get a page of InsuranceInstances");
        Page<InsuranceInstanceDTO> page = insuranceInstanceService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /insurance-instances/:id} : get the "id" insuranceInstance.
     *
     * @param id the id of the insuranceInstanceDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the insuranceInstanceDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/insurance-instances/{id}")
    public ResponseEntity<InsuranceInstanceDTO> getInsuranceInstance(@PathVariable Long id) {
        log.debug("REST request to get InsuranceInstance : {}", id);
        Optional<InsuranceInstanceDTO> insuranceInstanceDTO = insuranceInstanceService.findOne(id);
        return ResponseUtil.wrapOrNotFound(insuranceInstanceDTO);
    }

    /**
     * {@code DELETE  /insurance-instances/:id} : delete the "id" insuranceInstance.
     *
     * @param id the id of the insuranceInstanceDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/insurance-instances/{id}")
    public ResponseEntity<Void> deleteInsuranceInstance(@PathVariable Long id) {
        log.debug("REST request to delete InsuranceInstance : {}", id);
        insuranceInstanceService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
