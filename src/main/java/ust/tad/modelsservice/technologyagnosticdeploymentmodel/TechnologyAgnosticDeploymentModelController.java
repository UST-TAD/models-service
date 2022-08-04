package ust.tad.modelsservice.technologyagnosticdeploymentmodel;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import ust.tad.modelsservice.technologyagnosticdeploymentmodel.entities.TechnologyAgnosticDeploymentModel;


@RestController
@RequestMapping("technology-agnostic")
public class TechnologyAgnosticDeploymentModelController {
    
    private static final Logger LOG =
      LoggerFactory.getLogger(TechnologyAgnosticDeploymentModelController.class);

    @Autowired
    private TechnologyAgnosticDeploymentModelService technologyAgnosticDeploymentModelService;

    /**
     * Update a technology-agnostic deployment model.
     * 
     * @param annotatedDeploymentModel
     * @return the updated technology-agnostic deployment model with HttpStatus.OK.
     */
    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<TechnologyAgnosticDeploymentModel> updateTechnologyAgnosticDeploymentModel(
        @RequestBody TechnologyAgnosticDeploymentModel technologyAgnosticDeploymentModel) {
            LOG.info("Updating technology-agnostic deployment model");
        try {
            TechnologyAgnosticDeploymentModel tadm = technologyAgnosticDeploymentModelService.updateTechnologyAgnosticDeploymentModel(technologyAgnosticDeploymentModel);
            return new ResponseEntity<>(tadm, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Update of technology-agnostic deployment model failed", e);
        }
    }

    /**
     * Initialize a technology-agnostic deployment model which only holds the base component types.
     * 
     * @param transformationProcessId
     * @return the created technology-agnostic deployment model with HttpStatus.CREATED.
     */
    @PostMapping(value = "/init", consumes = "application/json", produces = "application/json")
    public ResponseEntity<TechnologyAgnosticDeploymentModel> initializeTechnologyAgnosticDeploymentModel(
        @RequestParam(required = true) UUID transformationProcessId) {
            LOG.info("Initializing technology-agnostic deployment model");
        try {
            TechnologyAgnosticDeploymentModel tadm = technologyAgnosticDeploymentModelService.initializeTechnologyAgnosticDeploymentModel(transformationProcessId);
            return new ResponseEntity<>(tadm, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Initialization of technology-agnostic deployment model failed", e);
        }
    }

    /**
     * Retrieves a technology-agnostic deployment model, identified by the transformationProcessId.
     * 
     * @param transformationProcessId
     * @return the technology-agnostic deployment model with HttpStatus.OK.
     */
    @GetMapping("/{transformationProcessId}")
    public ResponseEntity<TechnologyAgnosticDeploymentModel> getByTransformationProcessId(@PathVariable UUID transformationProcessId) {
        LOG.info("Sending technology-agnostic deployment model");
        try {
            return new ResponseEntity<>(technologyAgnosticDeploymentModelService.getTechnologyAgnosticDeploymentModelByTransformationProcessId(transformationProcessId), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
    }
    
}
