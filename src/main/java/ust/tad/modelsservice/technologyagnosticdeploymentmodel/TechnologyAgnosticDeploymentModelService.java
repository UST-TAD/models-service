package ust.tad.modelsservice.technologyagnosticdeploymentmodel;

import java.io.File;
import java.nio.file.Path;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.dataformat.yaml.YAMLGenerator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import ust.tad.modelsservice.technologyagnosticdeploymentmodel.annotatedentities.AnnotatedDeploymentModel;
import ust.tad.modelsservice.technologyagnosticdeploymentmodel.entities.ComponentType;
import ust.tad.modelsservice.technologyagnosticdeploymentmodel.entities.Property;
import ust.tad.modelsservice.technologyagnosticdeploymentmodel.entities.PropertyType;
import ust.tad.modelsservice.technologyagnosticdeploymentmodel.entities.RelationType;
import ust.tad.modelsservice.technologyagnosticdeploymentmodel.exceptions.InvalidPropertyValueException;

@Service
public class TechnologyAgnosticDeploymentModelService {

    @Autowired
    AnnotatedDeploymentModelRepository repository;

    @Value("${tadm.output.directory}")
    private String outputPath;

    /**
     * Initialize a technology-agnostic deployment model which only holds the base component types.
     * An entity of type AnnotatedDeploymentModel is created and saved in the models database.
     * 
     * @param transformationProcessId
     * @return the initialized technology-agnostic deployment model.
     * @throws InvalidPropertyValueException
     */
    public AnnotatedDeploymentModel initializeTechnologyAgnosticDeploymentModel(UUID transformationProcessId) throws InvalidPropertyValueException {
        return repository.save(
            new AnnotatedDeploymentModel(
                createBaseProperties(), 
                List.of(), 
                List.of(), 
                createBaseComponentTypes(), 
                createBaseRelationTypes(), 
                transformationProcessId));
    }

    /**
     * Export a technology-agnostic deployment model, identified by the transformationProcessId.
     * The output directory is specified by the environment variable tadm.output.directory in the application.properties file.
     * 
     * @param transformationProcessId
     * @throws Exception if there is none or more than one technology-agnostic deployment model with the given transformationProcessId.
     */
    public void exportTechnologyAgnosticDeploymentModel(UUID transformationProcessId) throws Exception {
        List<AnnotatedDeploymentModel> annotatedDeploymentModels = repository.findByTransformationProcessId(transformationProcessId);
        if(annotatedDeploymentModels.size() != 1) {
            throw new Exception("Invalid tranformation process id");
        } else {
            ObjectMapper mapper = new ObjectMapper(new YAMLFactory().enable(YAMLGenerator.Feature.INDENT_ARRAYS_WITH_INDICATOR));
            File outputFile = Path.of(outputPath,transformationProcessId.toString()+".yaml").toFile();
            AnnotatedDeploymentModel tadm = annotatedDeploymentModels.get(0);
            mapper.writeValue(outputFile, tadm);
        }
    }


    private List<RelationType> createBaseRelationTypes() {
        RelationType dependsOn = new RelationType("DependsOn", "generic relation type", List.of(), List.of(), null);
        RelationType hostedOn = new RelationType("HostedOn", "hosted on relation", List.of(), List.of(), dependsOn);        
        RelationType connectsTo = new RelationType("ConnectsTo", "connects to relation", List.of(), List.of(), dependsOn);
        return List.of(dependsOn, hostedOn, connectsTo);
    }

    private List<ComponentType> createBaseComponentTypes() {        
        ComponentType baseType = new ComponentType("BaseType", "This is the base type", List.of(), List.of(), null);
        return List.of(baseType);
    }

    private List<Property> createBaseProperties() throws InvalidPropertyValueException {
        Property versionProperty = new Property("version", PropertyType.STRING, true, "edm_1_0");
        return List.of(versionProperty);
    }

    
}
