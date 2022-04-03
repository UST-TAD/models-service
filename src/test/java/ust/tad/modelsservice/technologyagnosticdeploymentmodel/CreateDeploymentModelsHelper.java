package ust.tad.modelsservice.technologyagnosticdeploymentmodel;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.net.URI;
import java.util.List;
import java.util.UUID;

import ust.tad.modelsservice.technologyagnosticdeploymentmodel.annotatedentities.AnnotatedArtifact;
import ust.tad.modelsservice.technologyagnosticdeploymentmodel.annotatedentities.AnnotatedComponent;
import ust.tad.modelsservice.technologyagnosticdeploymentmodel.annotatedentities.AnnotatedDeploymentModel;
import ust.tad.modelsservice.technologyagnosticdeploymentmodel.annotatedentities.AnnotatedOperation;
import ust.tad.modelsservice.technologyagnosticdeploymentmodel.annotatedentities.AnnotatedProperty;
import ust.tad.modelsservice.technologyagnosticdeploymentmodel.annotatedentities.AnnotatedRelation;
import ust.tad.modelsservice.technologyagnosticdeploymentmodel.entities.ComponentType;
import ust.tad.modelsservice.technologyagnosticdeploymentmodel.entities.PropertyType;
import ust.tad.modelsservice.technologyagnosticdeploymentmodel.entities.RelationType;

class CreateDeploymentModelsHelper {

    // Creates an Annotated Deployment Model based on the /src/test/resources/exampleEDMM.yaml file
    public static AnnotatedDeploymentModel createExampleEDMM() {
        AnnotatedProperty property = assertDoesNotThrow(() -> new AnnotatedProperty("testProperty", PropertyType.STRING, true, "testValue", null));
        
        AnnotatedArtifact artifactForOperation = assertDoesNotThrow(() -> new AnnotatedArtifact("Create Script", "Shellscript", new URI("file://path/create.sh"), null));

        AnnotatedOperation operation = new AnnotatedOperation("Create", List.of(artifactForOperation), null);
        AnnotatedOperation operationDef = new AnnotatedOperation("Create", List.of(), null);

        ComponentType baseType = new ComponentType("BaseType", "This is the base type", List.of(), List.of(), null);

        ComponentType componentTypeOne = new ComponentType("TypeOne", "type one", List.of(property), List.of(operationDef), baseType);

        AnnotatedProperty propertyOne = assertDoesNotThrow(() -> new AnnotatedProperty("testProperty", PropertyType.STRING, true, "value one", null));
        AnnotatedArtifact artifactForComponentOne = assertDoesNotThrow(() -> new AnnotatedArtifact("MyImage1", "Docker Image", new URI("http://dockerhub.com/myimage1"), null));
        AnnotatedComponent componentOne = new AnnotatedComponent("ComponentOne", "first component", List.of(propertyOne), List.of(operation), componentTypeOne, List.of(artifactForComponentOne), null);

        ComponentType componentTypeTwo = new ComponentType("TypeTwo", "type two", List.of(property), List.of(operationDef), baseType);

        AnnotatedProperty propertyTwo = assertDoesNotThrow(() -> new AnnotatedProperty("testProperty", PropertyType.STRING, true, "value two", null));
        AnnotatedArtifact artifactForComponentTwo = assertDoesNotThrow(() -> new AnnotatedArtifact("MyImage2", "Docker Image", new URI("http://dockerhub.com/myimage2"), null));
        AnnotatedComponent componentTwo = new AnnotatedComponent("ComponentTwo", "second component", List.of(propertyTwo), List.of(operation), componentTypeTwo, List.of(artifactForComponentTwo), null);

        RelationType dependsOn = new RelationType("DependsOn", "generic relation type", List.of(), List.of(), null);
        RelationType hostedOn = new RelationType("HostedOn", "hosted on relation", List.of(), List.of(), dependsOn);        
        RelationType connectsTo = new RelationType("ConnectsTo", "connects to relation", List.of(), List.of(), dependsOn);

        AnnotatedProperty propertyRelation = assertDoesNotThrow(() -> new AnnotatedProperty("testProperty", PropertyType.STRING, true, "value relation", null));
        AnnotatedRelation relation = assertDoesNotThrow(() -> new AnnotatedRelation("ComponentOneConnectsToComponentTwo", "relation between component one and two", List.of(propertyRelation), List.of(), connectsTo, componentOne, componentTwo, null));

        AnnotatedDeploymentModel annotatedDeploymentModel = new AnnotatedDeploymentModel();
        annotatedDeploymentModel.setTransformationProcessId(UUID.randomUUID());
        annotatedDeploymentModel.setProperties(List.of(property));
        annotatedDeploymentModel.setComponents(List.of(componentOne, componentTwo));
        annotatedDeploymentModel.setRelations(List.of(relation));
        annotatedDeploymentModel.setComponentTypes(List.of(baseType, componentTypeOne, componentTypeTwo));
        annotatedDeploymentModel.setRelationTypes(List.of(dependsOn, hostedOn, connectsTo));

        return annotatedDeploymentModel;
    }
    
}
