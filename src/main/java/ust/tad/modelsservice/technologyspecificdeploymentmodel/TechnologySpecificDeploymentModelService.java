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
        return persistEmbeddedModels(technologySpecificDeploymentModel);
    }

    private TechnologySpecificDeploymentModel persistEmbeddedModels(TechnologySpecificDeploymentModel tsdm) {
        if (tsdm.getEmbeddedDeploymentModels().isEmpty()) {
            return persistModel(tsdm);
        } else {
            tsdm.getEmbeddedDeploymentModels().forEach(this::persistEmbeddedModels);
            return persistModel(tsdm);
        }
    }

    private TechnologySpecificDeploymentModel persistModel(TechnologySpecificDeploymentModel tsdm) {
        if(tsdm.getId() == null) {
            tsdm.setId(UUID.randomUUID());
        }
        return technologySpecificDeploymentModelRepository.save(tsdm);
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
        List<Location> locations,
        Boolean isRoot) throws InvalidNumberOfLinesException, InvalidNumberOfContentException {
            
        List<DeploymentModelContent> content = new ArrayList<>();
        for (Location location : locations) {
            List<Line> lines = new ArrayList<>();
            for(int lineNumber = location.getStartLineNumber(); lineNumber <= location.getEndLineNumber(); lineNumber++) {
                lines.add(new Line(lineNumber));
            } 
            content.add(new DeploymentModelContent(location.getUrl(), lines));
        }
        TechnologySpecificDeploymentModel technologySpecificDeploymentModel = new TechnologySpecificDeploymentModel(transformationProcessId, technology, commands, content);
        technologySpecificDeploymentModel.setRoot(isRoot);
        return technologySpecificDeploymentModelRepository.save(technologySpecificDeploymentModel);
    }
    
    /**
     * Get a technology-specific deployment model by the given transformationProcessId.
     * 
     * @param transformationProcessId
     * @return the found technology-specific deployment model.
     */
    public TechnologySpecificDeploymentModel getTechnologySpecificDeploymentModelByTransformationProcessId(UUID transformationProcessId) {
        int index = 0;
        List<TechnologySpecificDeploymentModel> technologySpecificDeploymentModels = technologySpecificDeploymentModelRepository.findByTransformationProcessId(transformationProcessId);
        for (TechnologySpecificDeploymentModel tsdm : technologySpecificDeploymentModels) {
            Boolean isRoot = tsdm.isRoot();    
            if (Boolean.TRUE.equals(isRoot)) {
                    index = technologySpecificDeploymentModels.indexOf(tsdm);
                }   
        }             
        return technologySpecificDeploymentModels.get(index);
    }
}
