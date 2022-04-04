package ust.tad.modelsservice.result;

import java.io.IOException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import ust.tad.modelsservice.technologyagnosticdeploymentmodel.TechnologyAgnosticDeploymentModelService;
import ust.tad.modelsservice.technologyagnosticdeploymentmodel.exceptions.EntityNotFoundException;

@RestController
@RequestMapping("result")
public class ResultController {
    
    @Autowired
    TechnologyAgnosticDeploymentModelService technologyAgnosticDeploymentModelService;

    /**
     * Get the result for the transformation process, identified by the transformationProcessId.
     * TODO include metrics
     * 
     * @param transformationProcessId
     * @return the absolute path where the technology-agnostic deployment model was exported to with HttpStatus.OK.
     */
    @GetMapping(value = "/{transformationProcessId}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<String> getResult(@PathVariable UUID transformationProcessId) {
        try {
            String path = technologyAgnosticDeploymentModelService.exportTechnologyAgnosticDeploymentModel(transformationProcessId);
            return new ResponseEntity<>(path, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        } catch (IOException e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);
        }
    }
    
}
