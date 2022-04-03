package ust.tad.modelsservice.technologyagnosticdeploymentmodel;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("technology-agnostic")
public class TechnologyAgnosticDeploymentModelController {

    @Autowired
    TechnologyAgnosticDeploymentModelService technologyAgnosticDeploymentModelService;

    //TODO Lookup how to correctly return
    @PostMapping(consumes = "application/json", produces = "application/json")
    public String initializeTechnologyAgnosticDeploymentModel(
        @RequestParam(required = true) UUID transformationProcessId) {
        try {
            technologyAgnosticDeploymentModelService.initializeTechnologyAgnosticDeploymentModel(transformationProcessId);
            return "Successfully initialized technology-agnostic deployment model";
        } catch (Exception e) {
            e.printStackTrace();
            return String.format("Error: %s", e.getMessage());
        }
    }
    
}
