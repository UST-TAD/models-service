package ust.tad.modelsservice.result;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ust.tad.modelsservice.technologyspecificdeploymentmodel.DeploymentModelContent;
import ust.tad.modelsservice.technologyspecificdeploymentmodel.Line;
import ust.tad.modelsservice.technologyspecificdeploymentmodel.TechnologySpecificDeploymentModel;
import ust.tad.modelsservice.technologyspecificdeploymentmodel.TechnologySpecificDeploymentModelService;

@Service
public class ComprehensibilityService {

    @Autowired
    private TechnologySpecificDeploymentModelService service;

    public double calculateComprehensibility(UUID transformationProcessId) {
        TechnologySpecificDeploymentModel tsdm = 
            service.getTechnologySpecificDeploymentModelByTransformationProcessId(transformationProcessId);
        double lineCount = calculateLineCount(tsdm);
        if (lineCount == 0) {
            return 0D;
        }
        return calculateComprehensibilitySum(tsdm) / lineCount;
    }

    private double comprehensibilitySum(DeploymentModelContent dMC) {
        double result = 0D;
        for (Line line : dMC.getLines()) {
            result += line.getComprehensibility();
        }
        return result;
    }

    private double calculateComprehensibilitySum(TechnologySpecificDeploymentModel tsdm) {
        double result = 0D;
        for (DeploymentModelContent content : tsdm.getContent()) {
            result += comprehensibilitySum(content);
        }
        for (TechnologySpecificDeploymentModel eDM : tsdm.getEmbeddedDeploymentModels()) {
            result += calculateComprehensibilitySum(eDM);
        }
        return result;
    }

    private double calculateLineCount(TechnologySpecificDeploymentModel tsdm) {
        int result = 0;
        for (DeploymentModelContent content : tsdm.getContent()) {
            result += content.getLines().size();
        }
        for (TechnologySpecificDeploymentModel eDM : tsdm.getEmbeddedDeploymentModels()) {
            result += calculateLineCount(eDM);
        }
        return result;
    }
    
}
