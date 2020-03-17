package com.idealsoft.insurance.web.rest;

import com.idealsoft.insurance.service.InsuranceObjectService;
import com.idealsoft.insurance.web.rest.errors.BadRequestAlertException;
import com.idealsoft.insurance.service.dto.InsuranceObjectDTO;

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
 * REST controller for managing {@link com.idealsoft.insurance.domain.InsuranceObject}.
 */
@RestController
@RequestMapping("/api")
public class InsuranceObjectResource {

    private final Logger log = LoggerFactory.getLogger(InsuranceObjectResource.class);

    private static final String ENTITY_NAME = "insuranceObject";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final InsuranceObjectService insuranceObjectService;

    public InsuranceObjectResource(InsuranceObjectService insuranceObjectService) {
        this.insuranceObjectService = insuranceObjectService;
    }

    /**
     * {@code POST  /insurance-objects} : Create a new insuranceObject.
     *
     * @param insuranceObjectDTO the insuranceObjectDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new insuranceObjectDTO, or with status {@code 400 (Bad Request)} if the insuranceObject has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/insurance-objects")
    public ResponseEntity<InsuranceObjectDTO> createInsuranceObject(@RequestBody InsuranceObjectDTO insuranceObjectDTO) throws URISyntaxException {
        log.debug("REST request to save InsuranceObject : {}", insuranceObjectDTO);
        if (insuranceObjectDTO.getId() != null) {
            throw new BadRequestAlertException("A new insuranceObject cannot already have an ID", ENTITY_NAME, "idexists");
        }
        InsuranceObjectDTO result = insuranceObjectService.save(insuranceObjectDTO);
        return ResponseEntity.created(new URI("/api/insurance-objects/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /insurance-objects} : Updates an existing insuranceObject.
     *
     * @param insuranceObjectDTO the insuranceObjectDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated insuranceObjectDTO,
     * or with status {@code 400 (Bad Request)} if the insuranceObjectDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the insuranceObjectDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/insurance-objects")
    public ResponseEntity<InsuranceObjectDTO> updateInsuranceObject(@RequestBody InsuranceObjectDTO insuranceObjectDTO) throws URISyntaxException {
        log.debug("REST request to update InsuranceObject : {}", insuranceObjectDTO);
        if (insuranceObjectDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        InsuranceObjectDTO result = insuranceObjectService.save(insuranceObjectDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, insuranceObjectDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /insurance-objects} : get all the insuranceObjects.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of insuranceObjects in body.
     */
    @GetMapping("/insurance-objects")
    public ResponseEntity<List<InsuranceObjectDTO>> getAllInsuranceObjects(Pageable pageable) {
        log.debug("REST request to get a page of InsuranceObjects");
        Page<InsuranceObjectDTO> page = insuranceObjectService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /insurance-objects/:id} : get the "id" insuranceObject.
     *
     * @param id the id of the insuranceObjectDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the insuranceObjectDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/insurance-objects/{id}")
    public ResponseEntity<InsuranceObjectDTO> getInsuranceObject(@PathVariable Long id) {
        log.debug("REST request to get InsuranceObject : {}", id);
        Optional<InsuranceObjectDTO> insuranceObjectDTO = insuranceObjectService.findOne(id);
        return ResponseUtil.wrapOrNotFound(insuranceObjectDTO);
    }

    /**
     * {@code DELETE  /insurance-objects/:id} : delete the "id" insuranceObject.
     *
     * @param id the id of the insuranceObjectDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/insurance-objects/{id}")
    public ResponseEntity<Void> deleteInsuranceObject(@PathVariable Long id) {
        log.debug("REST request to delete InsuranceObject : {}", id);
        insuranceObjectService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
