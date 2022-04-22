package ust.tad.modelsservice.technologyspecificdeploymentmodel;

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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("technology-specific")
public class TechnologySpecificDeploymentModelController {
    
    private static final Logger LOG =
      LoggerFactory.getLogger(TechnologySpecificDeploymentModelController.class);

    @Autowired
    private TechnologySpecificDeploymentModelService technologySpecificDeploymentModelService;

    /**
     * Update an internal technology-specific deployment model.
     * 
     * @param technologySpecificDeploymentModel
     * @return the updated technology-specific deployment model with HttpStatus.OK.
     */
    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<TechnologySpecificDeploymentModel> updateTechnologySpecificDeploymentModel(
        @RequestBody TechnologySpecificDeploymentModel technologySpecificDeploymentModel) {
            LOG.info("Updating technology-specific deployment model");            
            try {
                TechnologySpecificDeploymentModel tsdm = 
                    technologySpecificDeploymentModelService.updateTechnologySpecificDeploymentModel(technologySpecificDeploymentModel);
                return new ResponseEntity<>(tsdm, HttpStatus.OK);
            } catch (Exception e) {
                e.printStackTrace();
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Update of technology-specific deployment model failed", e);
            }
    }

    /**
     * Initialize an internal representation of the technology-specific deployment model.
     * 
     * @param initializeTechnologySpecificDeploymentModelRequest contains information about the deployment model.
     * @return the created technology-specific deployment model with HttpStatus.CREATED.
     */
    @PostMapping(value = "/init", consumes = "application/json", produces = "application/json")
    public ResponseEntity<TechnologySpecificDeploymentModel> initializeTechnologySpecificDeploymentModel(
        @RequestBody InitializeTechnologySpecificDeploymentModelRequest initializeTechnologySpecificDeploymentModelRequest) {
            LOG.info("Initializing technology-specific deployment model");
            try {
                TechnologySpecificDeploymentModel tsdm = 
                technologySpecificDeploymentModelService.createTechnologySpecificDeploymentModel(
                    initializeTechnologySpecificDeploymentModelRequest.getTransformationProcessId(),
                    initializeTechnologySpecificDeploymentModelRequest.getTechnology(),
                    initializeTechnologySpecificDeploymentModelRequest.getCommands(),
                    initializeTechnologySpecificDeploymentModelRequest.getLocations(),
                    true);
                return new ResponseEntity<>(tsdm, HttpStatus.CREATED);
            } catch (InvalidNumberOfLinesException | InvalidNumberOfContentException e) {
                e.printStackTrace();
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Initialization of technology-specific deployment model failed", e);
            }
    }

    /**
     * Retrieves a technology-specific deployment model, identified by the transformationProcessId.
     * 
     * @param transformationProcessId
     * @return the technology-specific deployment model with HttpStatus.OK.
     */
    @GetMapping("/{transformationProcessId}")
    public ResponseEntity<TechnologySpecificDeploymentModel> getByTransformationProcessId(@PathVariable UUID transformationProcessId) {
        LOG.info("Sending technology-specific deployment model");
        try {
            return new ResponseEntity<>(technologySpecificDeploymentModelService.getTechnologySpecificDeploymentModelByTransformationProcessId(transformationProcessId), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
    }

    
}
