package ust.tad.modelsservice.result;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ust.tad.modelsservice.technologyspecificdeploymentmodel.DeploymentModelContent;
import ust.tad.modelsservice.technologyspecificdeploymentmodel.Line;
import ust.tad.modelsservice.technologyspecificdeploymentmodel.TechnologySpecificDeploymentModel;
import ust.tad.modelsservice.technologyspecificdeploymentmodel.TechnologySpecificDeploymentModelService;

@Service
public class AnalysisProgressService {

    @Autowired
    private TechnologySpecificDeploymentModelService service;

    public double calculateAnalysisProgress(UUID transformationProcessId) {
        TechnologySpecificDeploymentModel tsdm = 
            service.getTechnologySpecificDeploymentModelByTransformationProcessId(transformationProcessId);
        double lineCount = calculateLineCount(tsdm);
        if (lineCount == 0) {
            return 0D;
        }
        return calculateAnalyzedLinesSum(tsdm) / lineCount;
    }

    private long analyzedLinesSum(DeploymentModelContent dMC) {
        return dMC.getLines().stream().filter(Line::isAnalyzed).count();
    }

    private double calculateAnalyzedLinesSum(TechnologySpecificDeploymentModel tsdm) {
        int result = 0;
        for (DeploymentModelContent content : tsdm.getContent()) {
            result += analyzedLinesSum(content);
        }
        for (TechnologySpecificDeploymentModel eDM : tsdm.getEmbeddedDeploymentModels()) {
            result += calculateAnalyzedLinesSum(eDM);
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
