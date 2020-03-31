package com.idealsoft.insurance.web.rest;

import com.idealsoft.insurance.service.InsuranceObjectTypeService;
import com.idealsoft.insurance.web.rest.errors.BadRequestAlertException;
import com.idealsoft.insurance.service.dto.InsuranceObjectTypeDTO;

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
 * REST controller for managing {@link com.idealsoft.insurance.domain.InsuranceObjectType}.
 */
@RestController
@RequestMapping("/api")
public class InsuranceObjectTypeResource {

    private final Logger log = LoggerFactory.getLogger(InsuranceObjectTypeResource.class);

    private static final String ENTITY_NAME = "insuranceObjectType";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final InsuranceObjectTypeService insuranceObjectTypeService;

    public InsuranceObjectTypeResource(InsuranceObjectTypeService insuranceObjectTypeService) {
        this.insuranceObjectTypeService = insuranceObjectTypeService;
    }

    /**
     * {@code POST  /insurance-object-types} : Create a new insuranceObjectType.
     *
     * @param insuranceObjectTypeDTO the insuranceObjectTypeDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new insuranceObjectTypeDTO, or with status {@code 400 (Bad Request)} if the insuranceObjectType has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/insurance-object-types")
    public ResponseEntity<InsuranceObjectTypeDTO> createInsuranceObjectType(@RequestBody InsuranceObjectTypeDTO insuranceObjectTypeDTO) throws URISyntaxException {
        log.debug("REST request to save InsuranceObjectType : {}", insuranceObjectTypeDTO);
        if (insuranceObjectTypeDTO.getId() != null) {
            throw new BadRequestAlertException("A new insuranceObjectType cannot already have an ID", ENTITY_NAME, "idexists");
        }
        InsuranceObjectTypeDTO result = insuranceObjectTypeService.save(insuranceObjectTypeDTO);
        return ResponseEntity.created(new URI("/api/insurance-object-types/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /insurance-object-types} : Updates an existing insuranceObjectType.
     *
     * @param insuranceObjectTypeDTO the insuranceObjectTypeDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated insuranceObjectTypeDTO,
     * or with status {@code 400 (Bad Request)} if the insuranceObjectTypeDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the insuranceObjectTypeDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/insurance-object-types")
    public ResponseEntity<InsuranceObjectTypeDTO> updateInsuranceObjectType(@RequestBody InsuranceObjectTypeDTO insuranceObjectTypeDTO) throws URISyntaxException {
        log.debug("REST request to update InsuranceObjectType : {}", insuranceObjectTypeDTO);
        if (insuranceObjectTypeDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        InsuranceObjectTypeDTO result = insuranceObjectTypeService.save(insuranceObjectTypeDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, insuranceObjectTypeDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /insurance-object-types} : get all the insuranceObjectTypes.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of insuranceObjectTypes in body.
     */
    @GetMapping("/insurance-object-types")
    public ResponseEntity<List<InsuranceObjectTypeDTO>> getAllInsuranceObjectTypes(Pageable pageable) {
        log.debug("REST request to get a page of InsuranceObjectTypes");
        Page<InsuranceObjectTypeDTO> page = insuranceObjectTypeService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /insurance-object-types/:id} : get the "id" insuranceObjectType.
     *
     * @param id the id of the insuranceObjectTypeDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the insuranceObjectTypeDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/insurance-object-types/{id}")
    public ResponseEntity<InsuranceObjectTypeDTO> getInsuranceObjectType(@PathVariable Long id) {
        log.debug("REST request to get InsuranceObjectType : {}", id);
        Optional<InsuranceObjectTypeDTO> insuranceObjectTypeDTO = insuranceObjectTypeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(insuranceObjectTypeDTO);
    }

    /**
     * {@code DELETE  /insurance-object-types/:id} : delete the "id" insuranceObjectType.
     *
     * @param id the id of the insuranceObjectTypeDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/insurance-object-types/{id}")
    public ResponseEntity<Void> deleteInsuranceObjectType(@PathVariable Long id) {
        log.debug("REST request to delete InsuranceObjectType : {}", id);
        insuranceObjectTypeService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
