package ust.tad.modelsservice.technologyagnosticdeploymentmodel;

import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
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
