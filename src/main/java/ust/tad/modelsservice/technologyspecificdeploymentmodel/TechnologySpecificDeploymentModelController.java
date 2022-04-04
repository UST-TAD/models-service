package ust.tad.modelsservice.technologyspecificdeploymentmodel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("technology-specific")
public class TechnologySpecificDeploymentModelController {

    @Autowired
    private TechnologySpecificDeploymentModelService technologySpecificDeploymentModelService;

    /**
     * Initialize an internal representation of the technology-specific deployment model.
     * 
     * @param initializeTechnologySpecificDeploymentModelRequest contains information about the deployment model.
     * @return the created technology-specific deployment model with HttpStatus.CREATED.
     */
    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<TechnologySpecificDeploymentModel> initializeTechnologySpecificDeploymentModel(
        @RequestBody InitializeTechnologySpecificDeploymentModelRequest initializeTechnologySpecificDeploymentModelRequest) {
        try {
            TechnologySpecificDeploymentModel tsdm = 
            technologySpecificDeploymentModelService.createTechnologySpecificDeploymentModel(
                initializeTechnologySpecificDeploymentModelRequest.getTransformationProcessId(),
                initializeTechnologySpecificDeploymentModelRequest.getTechnology(),
                initializeTechnologySpecificDeploymentModelRequest.getCommands(),
                initializeTechnologySpecificDeploymentModelRequest.getLocations());
            return new ResponseEntity<>(tsdm, HttpStatus.CREATED);
        } catch (InvalidNumberOfLinesException | InvalidNumberOfContentException e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Initialization of technology-specific deployment model failed", e);
        }
    }

    
}
