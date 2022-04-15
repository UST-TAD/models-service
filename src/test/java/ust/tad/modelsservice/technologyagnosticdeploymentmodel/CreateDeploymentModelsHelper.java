package ust.tad.modelsservice.technologyagnosticdeploymentmodel;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.net.URI;
import java.util.List;
import java.util.UUID;

import ust.tad.modelsservice.technologyagnosticdeploymentmodel.entities.*;

class CreateDeploymentModelsHelper {

    // Creates a technology-agnostic Deployment Model based on the /src/test/resources/exampleEDMM.yaml file
    public static TechnologyAgnosticDeploymentModel createExampleEDMM() {
        Property property = assertDoesNotThrow(() -> new Property("testProperty", PropertyType.STRING, true, "testValue", null));
        
        Artifact artifactForOperation = assertDoesNotThrow(() -> new Artifact("Create Script", "Shellscript", new URI("file://path/create.sh"), null));

        Operation operation = new Operation("Create", List.of(artifactForOperation), null);
        Operation operationDef = new Operation("Create", List.of(), null);

        ComponentType baseType = new ComponentType("BaseType", "This is the base type", List.of(), List.of(), null);

        ComponentType componentTypeOne = new ComponentType("TypeOne", "type one", List.of(property), List.of(operationDef), baseType);

        Property propertyOne = assertDoesNotThrow(() -> new Property("testProperty", PropertyType.STRING, true, "value one", null));
        Artifact artifactForComponentOne = assertDoesNotThrow(() -> new Artifact("MyImage1", "Docker Image", new URI("http://dockerhub.com/myimage1"), null));
        Component componentOne = new Component("ComponentOne", "first component", List.of(propertyOne), List.of(operation), componentTypeOne, List.of(artifactForComponentOne), null);

        ComponentType componentTypeTwo = new ComponentType("TypeTwo", "type two", List.of(property), List.of(operationDef), baseType);

        Property propertyTwo = assertDoesNotThrow(() -> new Property("testProperty", PropertyType.STRING, true, "value two", null));
        Artifact artifactForComponentTwo = assertDoesNotThrow(() -> new Artifact("MyImage2", "Docker Image", new URI("http://dockerhub.com/myimage2"), null));
        Component componentTwo = new Component("ComponentTwo", "second component", List.of(propertyTwo), List.of(operation), componentTypeTwo, List.of(artifactForComponentTwo), null);

        RelationType dependsOn = new RelationType("DependsOn", "generic relation type", List.of(), List.of(), null);
        RelationType hostedOn = new RelationType("HostedOn", "hosted on relation", List.of(), List.of(), dependsOn);        
        RelationType connectsTo = new RelationType("ConnectsTo", "connects to relation", List.of(), List.of(), dependsOn);

        Property propertyRelation = assertDoesNotThrow(() -> new Property("testProperty", PropertyType.STRING, true, "value relation", null));
        Relation relation = assertDoesNotThrow(() -> new Relation("ComponentOneConnectsToComponentTwo", "relation between component one and two", List.of(propertyRelation), List.of(), connectsTo, componentOne, componentTwo, null));

        TechnologyAgnosticDeploymentModel tadm = new TechnologyAgnosticDeploymentModel();
        tadm.setTransformationProcessId(UUID.randomUUID());
        tadm.setProperties(List.of(property));
        tadm.setComponents(List.of(componentOne, componentTwo));
        tadm.setRelations(List.of(relation));
        tadm.setComponentTypes(List.of(baseType, componentTypeOne, componentTypeTwo));
        tadm.setRelationTypes(List.of(dependsOn, hostedOn, connectsTo));

        return tadm;
    }
    
}
