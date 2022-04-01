package ust.tad.modelsservice.technologyspecificdeploymentmodel;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ust.tad.modelsservice.shared.Location;

@Service
public class TechnologySpecificDeploymentModelService {

    @Autowired
    TechnologySpecificDeploymentModelRepository technologySpecificDeploymentModelRepository;

    public TechnologySpecificDeploymentModel createTechnologySpecificDeploymentModel(
        UUID transformationProcessId, 
        String technology, 
        List<String> commands, 
        List<Location> locations) throws InvalidNumberOfLinesException, InvalidNumberOfContentException {
            
        List<DeploymentModelContent> content = new ArrayList<>();
        for (Location location : locations) {
            List<Line> lines = new ArrayList<>();
            for(int lineNumber = location.getStartLineNumber(); lineNumber <= location.getEndLineNumber(); lineNumber++) {
                lines.add(new Line(lineNumber));
            } 
            content.add(new DeploymentModelContent(location.getUrl(), lines));
        }
        TechnologySpecificDeploymentModel technologySpecificDeploymentModel = new TechnologySpecificDeploymentModel(transformationProcessId, technology, commands, content);
        return technologySpecificDeploymentModelRepository.save(technologySpecificDeploymentModel);
    }
    
    public TechnologySpecificDeploymentModel getTechnologySpecificDeploymentModelByTransformationProcessId(UUID transformationProcessId) {
       List<TechnologySpecificDeploymentModel> technologySpecificDeploymentModels = technologySpecificDeploymentModelRepository.findByTransformationProcessId(transformationProcessId);
       return technologySpecificDeploymentModels.get(0);
    }
}
