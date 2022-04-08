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

    /**
     * Updates a technology-specific deployment model with new information.
     * 
     * @param technologySpecificDeploymentModel
     * @return the updated technology-specific deployment model.
     */
    public TechnologySpecificDeploymentModel updateTechnologySpecificDeploymentModel(TechnologySpecificDeploymentModel technologySpecificDeploymentModel) {
        return technologySpecificDeploymentModelRepository.save(technologySpecificDeploymentModel);
    }

    /**
     * Create a new technology-specific deployment model from the given parameters and save it in the models database.
     * 
     * @param transformationProcessId
     * @param technology
     * @param commands
     * @param locations
     * @return the created technology-specific deployment model.
     * @throws InvalidNumberOfLinesException
     * @throws InvalidNumberOfContentException
     */
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
    
    /**
     * Get a technology-specific deployment model by the given transformationProcessId.
     * 
     * @param transformationProcessId
     * @return the found technology-specific deployment model.
     */
    public TechnologySpecificDeploymentModel getTechnologySpecificDeploymentModelByTransformationProcessId(UUID transformationProcessId) {
       List<TechnologySpecificDeploymentModel> technologySpecificDeploymentModels = technologySpecificDeploymentModelRepository.findByTransformationProcessId(transformationProcessId);
       return technologySpecificDeploymentModels.get(0);
    }
}
