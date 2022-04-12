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
    private TechnologyAgnosticDeploymentModelService technologyAgnosticDeploymentModelService;

    @Autowired
    private AnalysisProgressService analysisProgressService;

    @Autowired
    private ComprehensibilityService comprehensibilityService;

    @Autowired
    private ConfidenceService confidenceService;

    @Autowired
    private TypeCompletenessService typeCompletenessService;

    /**
     * Get the result for the transformation process, identified by the transformationProcessId.
     * 
     * @param transformationProcessId
     * @return the absolute path where the technology-agnostic deployment model was exported to with HttpStatus.OK.
     */
    @GetMapping(value = "/{transformationProcessId}", produces = "application/json")
    public ResponseEntity<Result> getResult(@PathVariable UUID transformationProcessId) {
        try {            
            return new ResponseEntity<>(createResult(transformationProcessId), HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        } catch (IOException e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);
        }
    }

    /**
     * Create a result from the path to the exported technology-agnostic deployment model and the calculated metrics.
     * 
     * @param transformationProcessId
     * @return
     * @throws EntityNotFoundException
     * @throws IOException
     */
    private Result createResult(UUID transformationProcessId) throws EntityNotFoundException, IOException {
        Result result = new Result();
        result.setPath(technologyAgnosticDeploymentModelService.exportTechnologyAgnosticDeploymentModel(transformationProcessId));
        result.setAnalysisProgress(analysisProgressService.calculateAnalysisProgress(transformationProcessId));
        result.setComprehensibility(comprehensibilityService.calculateComprehensibility(transformationProcessId));
        result.setConfidence(confidenceService.calculateConfidence(transformationProcessId));
        result.setTypeCompletenessVal1(typeCompletenessService.calculateTypeCompletenessVal1(transformationProcessId));
        result.setTypeCompletenessVal2(typeCompletenessService.calculateTypeCompletenessVal2(transformationProcessId));
        return result;
    }
    
}
