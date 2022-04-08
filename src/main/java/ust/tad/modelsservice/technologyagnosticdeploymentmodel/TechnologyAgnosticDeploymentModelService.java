package ust.tad.modelsservice.technologyagnosticdeploymentmodel;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.dataformat.yaml.YAMLGenerator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import ust.tad.modelsservice.technologyagnosticdeploymentmodel.annotatedentities.*;
import ust.tad.modelsservice.technologyagnosticdeploymentmodel.entities.*;
import ust.tad.modelsservice.technologyagnosticdeploymentmodel.exceptions.EntityNotFoundException;
import ust.tad.modelsservice.technologyagnosticdeploymentmodel.exceptions.InvalidPropertyValueException;
import ust.tad.modelsservice.technologyagnosticdeploymentmodel.yamlserializer.*;

@Service
public class TechnologyAgnosticDeploymentModelService {

    @Autowired
    AnnotatedDeploymentModelRepository repository;

    @Value("${tadm.output.directory}")
    private String outputPath;

    /**
     * Initialize a technology-agnostic deployment model which only holds the base component types.
     * Creates an entity of type AnnotatedDeploymentModel and saves it in the models database.
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
     * @return the absolute path and file name containing the exported technology-agnostic deployment model.
     * @throws IOException if there was an error with the creation of the output file.
     * @throws Exception if there is no technology-agnostic deployment model with the given transformationProcessId.
     */
    public String exportTechnologyAgnosticDeploymentModel(UUID transformationProcessId) throws EntityNotFoundException, IOException {
        ObjectMapper mapper = YamlObjectMapper.createYamlObjectMapper();
        File outputFile = Path.of(outputPath,transformationProcessId.toString()+".yaml").toFile();
        AnnotatedDeploymentModel tadm = getTechnologyAgnosticDeploymentModelByTransformationProcessId(transformationProcessId);
        mapper.writeValue(outputFile, tadm);
        return outputFile.getAbsolutePath();
    }

    /**
     * Retrieves a technology-agnostic deployment model, identified by the transformationProcessId.
     * 
     * @param transformationProcessId
     * @return the technology-agnostic deployment model.
     * @throws EntityNotFoundException if there is no technology-agnostic deployment model with the given transformationProcessId.
     */
    public AnnotatedDeploymentModel getTechnologyAgnosticDeploymentModelByTransformationProcessId(UUID transformationProcessId) throws EntityNotFoundException {
        List<AnnotatedDeploymentModel> annotatedDeploymentModels = repository.findByTransformationProcessId(transformationProcessId);
        if(annotatedDeploymentModels.isEmpty()) {
            throw new EntityNotFoundException(
                String.format("Could not find technology-agnostic deployment model with the transformation process id '%s'",
                transformationProcessId));
        } else {
            return annotatedDeploymentModels.get(0);
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
