package ust.tad.modelsservice.technologyspecificdeploymentmodel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("technology-specific")
public class TechnologySpecificDeploymentModelController {

    @Autowired
    private TechnologySpecificDeploymentModelService technologySpecificDeploymentModelService;

    //TODO Lookup how to correctly return
    @PostMapping(consumes = "application/json", produces = "application/json")
    public String initializeTechnologySpecificDeploymentModel(
        @RequestBody InitializeTechnologySpecificDeploymentModelRequest initializeTechnologySpecificDeploymentModelRequest) {
        try {
            technologySpecificDeploymentModelService.createTechnologySpecificDeploymentModel(
                initializeTechnologySpecificDeploymentModelRequest.getTransformationProcessId(),
                initializeTechnologySpecificDeploymentModelRequest.getTechnology(),
                initializeTechnologySpecificDeploymentModelRequest.getCommands(),
                initializeTechnologySpecificDeploymentModelRequest.getLocations());
            return "Successfully initialized technology-specific deployment model";
        } catch (InvalidNumberOfLinesException | InvalidNumberOfContentException e) {
            e.printStackTrace();
            return String.format("Error: %s", e.getMessage());
        }
    }

    
}
