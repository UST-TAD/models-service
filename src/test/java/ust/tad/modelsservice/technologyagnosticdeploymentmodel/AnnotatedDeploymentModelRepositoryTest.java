package ust.tad.modelsservice.technologyagnosticdeploymentmodel;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.net.URI;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ust.tad.modelsservice.technologyagnosticdeploymentmodel.entities.*;
import ust.tad.modelsservice.technologyagnosticdeploymentmodel.annotatedentities.*;

@SpringBootTest
public class AnnotatedDeploymentModelRepositoryTest {

    @Autowired
    AnnotatedDeploymentModelRepository deploymentModelRepository;
    
    @Test
    public void createAnnotatedDeploymentModel_created() {
        
        final Logger LOG =
            LoggerFactory.getLogger(AnnotatedDeploymentModelRepositoryTest.class);

        AnnotatedProperty property = assertDoesNotThrow(() -> new AnnotatedProperty("testProperty", PropertyType.STRING, true, "testValue", null));
        
        AnnotatedArtifact artifactForOperation = assertDoesNotThrow(() -> new AnnotatedArtifact("Create Script", "Shellscript", new URI("file://path/create.sh"), null));

        AnnotatedOperation operation = new AnnotatedOperation("Create", List.of(artifactForOperation), null);
        AnnotatedOperation operationDef = new AnnotatedOperation("Create", null, null);

        ComponentType baseType = new ComponentType("BaseType", "This is the base type", null, null, null);

        ComponentType componentTypeOne = new ComponentType("TypeOne", "type one", List.of(property), List.of(operationDef), baseType);

        AnnotatedProperty propertyOne = property;
        assertDoesNotThrow(() -> propertyOne.setValue("value one"));
        AnnotatedArtifact artifactForComponentOne = assertDoesNotThrow(() -> new AnnotatedArtifact("MyImage1", "Docker Image", new URI("http://dockerhub.com/myimage1"), null));
        AnnotatedComponent componentOne = new AnnotatedComponent("ComponentOne", "first component", List.of(propertyOne), List.of(operation), componentTypeOne, List.of(artifactForComponentOne), null);

        ComponentType componentTypeTwo = new ComponentType("TypeTwo", "type two", List.of(property), List.of(operationDef), baseType);

        AnnotatedProperty propertyTwo = property;
        assertDoesNotThrow(() -> propertyTwo.setValue("value two"));
        AnnotatedArtifact artifactForComponentTwo = assertDoesNotThrow(() -> new AnnotatedArtifact("MyImage2", "Docker Image", new URI("http://dockerhub.com/myimage2"), null));
        AnnotatedComponent componentTwo = new AnnotatedComponent("ComponentTwo", "second component", List.of(propertyTwo), List.of(operation), componentTypeTwo, List.of(artifactForComponentTwo), null);

        RelationType dependsOn = new RelationType("DependsOn", "generic relation type", null, null, null);
        RelationType hostedOn = new RelationType("HostedOn", "hosted on relation", null, null, dependsOn);        
        RelationType connectsTo = new RelationType("ConnectsTo", "connects to relation", null, null, dependsOn);

        AnnotatedProperty propertyRelation = property;
        assertDoesNotThrow(() -> propertyRelation.setValue("value relation"));
        AnnotatedRelation relation = assertDoesNotThrow(() -> new AnnotatedRelation("ComponentOneConnectsToComponentTwo", "relation between component one and two", List.of(propertyRelation), null, connectsTo, componentOne, componentTwo, null));

        AnnotatedDeploymentModel annotatedDeploymentModel = new AnnotatedDeploymentModel();
        annotatedDeploymentModel.setTransformationProcessId(UUID.randomUUID());
        annotatedDeploymentModel.setProperties(List.of(property));
        annotatedDeploymentModel.setModelEntities(List.of(baseType, componentTypeOne, 
            componentTypeTwo, dependsOn, hostedOn, connectsTo, 
            componentOne, componentTwo, relation));

        AnnotatedDeploymentModel annotatedDeploymentModelSaved = deploymentModelRepository.save(annotatedDeploymentModel);
    
        assertEquals(annotatedDeploymentModel, annotatedDeploymentModelSaved);

        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = assertDoesNotThrow(() -> ow.writeValueAsString(annotatedDeploymentModelSaved));

        LOG.info(json);
    }
}
