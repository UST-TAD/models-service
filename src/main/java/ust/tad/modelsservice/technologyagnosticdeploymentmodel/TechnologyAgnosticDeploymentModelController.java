package ust.tad.modelsservice.technologyagnosticdeploymentmodel;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import ust.tad.modelsservice.technologyagnosticdeploymentmodel.annotatedentities.AnnotatedDeploymentModel;

@RestController
@RequestMapping("technology-agnostic")
public class TechnologyAgnosticDeploymentModelController {

    @Autowired
    TechnologyAgnosticDeploymentModelService technologyAgnosticDeploymentModelService;

    /**
     * Initialize a technology-agnostic deployment model which only holds the base component types.
     * 
     * @param transformationProcessId
     * @return the created technology-agnostic deployment model with HttpStatus.CREATED.
     */
    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<AnnotatedDeploymentModel> initializeTechnologyAgnosticDeploymentModel(
        @RequestParam(required = true) UUID transformationProcessId) {
        try {
            AnnotatedDeploymentModel tadm =  technologyAgnosticDeploymentModelService.initializeTechnologyAgnosticDeploymentModel(transformationProcessId);
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
    public ResponseEntity<AnnotatedDeploymentModel> getByTransformationProcessId(@PathVariable UUID transformationProcessId) {
        try {
            return new ResponseEntity<>(technologyAgnosticDeploymentModelService.getTechnologyAgnosticDeploymentModelByTransformationProcessId(transformationProcessId), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
    }
    
}
