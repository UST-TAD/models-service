package ust.tad.modelsservice.technologyagnosticdeploymentmodel;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.net.URI;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ust.tad.modelsservice.technologyagnosticdeploymentmodel.annotatedentities.AnnotatedArtifact;
import ust.tad.modelsservice.technologyagnosticdeploymentmodel.annotatedentities.AnnotatedComponent;
import ust.tad.modelsservice.technologyagnosticdeploymentmodel.annotatedentities.AnnotatedDeploymentModel;
import ust.tad.modelsservice.technologyagnosticdeploymentmodel.annotatedentities.AnnotatedOperation;
import ust.tad.modelsservice.technologyagnosticdeploymentmodel.annotatedentities.AnnotatedProperty;
import ust.tad.modelsservice.technologyagnosticdeploymentmodel.annotatedentities.AnnotatedRelation;
import ust.tad.modelsservice.technologyagnosticdeploymentmodel.entities.ComponentType;
import ust.tad.modelsservice.technologyagnosticdeploymentmodel.entities.PropertyType;
import ust.tad.modelsservice.technologyagnosticdeploymentmodel.entities.RelationType;
import ust.tad.modelsservice.technologyagnosticdeploymentmodel.repositories.AnnotatedArtifactRepository;
import ust.tad.modelsservice.technologyagnosticdeploymentmodel.repositories.AnnotatedComponentRepository;
import ust.tad.modelsservice.technologyagnosticdeploymentmodel.repositories.AnnotatedDeploymentModelRepository;
import ust.tad.modelsservice.technologyagnosticdeploymentmodel.repositories.AnnotatedOperationRepository;
import ust.tad.modelsservice.technologyagnosticdeploymentmodel.repositories.AnnotatedRelationRepository;
import ust.tad.modelsservice.technologyagnosticdeploymentmodel.repositories.ComponentTypeRepository;
import ust.tad.modelsservice.technologyagnosticdeploymentmodel.repositories.RelationTypeRepository;

@SpringBootTest
public class AnnotatedDeploymentModelRepositoryTest {

    @Autowired
    AnnotatedDeploymentModelRepository deploymentModelRepository;
    
    @Autowired
    AnnotatedComponentRepository componentRepository;

    @Autowired
    AnnotatedRelationRepository relationRepository;
    
    @Autowired
    AnnotatedArtifactRepository artifactRepository;

    @Autowired
    AnnotatedOperationRepository operationRepository;

    @Autowired
    ComponentTypeRepository componentTypeRepository;

    @Autowired
    RelationTypeRepository relationTypeRepository;
    
    @Test
    public void createAnnotatedDeploymentModel_created() {
        AnnotatedProperty property = assertDoesNotThrow(() -> new AnnotatedProperty("testProperty", PropertyType.STRING, true, "testValue", null));
        
        AnnotatedArtifact artifactForOperation = assertDoesNotThrow(() -> new AnnotatedArtifact("Create Script", "Shellscript", new URI("file://path/create.sh"), null));
        AnnotatedArtifact artifactForOperationSaved = artifactRepository.save(artifactForOperation);

        AnnotatedOperation operation = new AnnotatedOperation("Create", List.of(artifactForOperation), null);
        AnnotatedOperation operationDef = new AnnotatedOperation("Create", null, null);
        AnnotatedOperation operationSaved = operationRepository.save(operation);
        AnnotatedOperation operationDefSaved = operationRepository.save(operationDef);

        ComponentType baseType = new ComponentType("This is the base type", null, null, null);
        ComponentType baseTypeSaved = componentTypeRepository.save(baseType);

        ComponentType componentTypeOne = new ComponentType("type one", List.of(property), List.of(operationDef), baseTypeSaved.getId());
        ComponentType componentTypeOneSaved = componentTypeRepository.save(componentTypeOne);

        AnnotatedProperty propertyOne = property;
        assertDoesNotThrow(() -> propertyOne.setValue("value one"));
        AnnotatedArtifact artifactForComponentOne = assertDoesNotThrow(() -> new AnnotatedArtifact("MyImage1", "Docker Image", new URI("http://dockerhub.com/myimage1"), null));
        AnnotatedArtifact artifactForComponentOneSaved = artifactRepository.save(artifactForComponentOne);
        AnnotatedComponent componentOne = new AnnotatedComponent("first component", List.of(propertyOne), List.of(operation), componentTypeOneSaved.getId(), List.of(artifactForComponentOne), null);
        AnnotatedComponent componentOneSaved = componentRepository.save(componentOne);

        ComponentType componentTypeTwo = new ComponentType("type two", List.of(property), List.of(operationDef), baseTypeSaved.getId());
        ComponentType componentTypeTwoSaved = componentTypeRepository.save(componentTypeTwo);

        AnnotatedProperty propertyTwo = property;
        assertDoesNotThrow(() -> propertyTwo.setValue("value two"));
        AnnotatedArtifact artifactForComponentTwo = assertDoesNotThrow(() -> new AnnotatedArtifact("MyImage2", "Docker Image", new URI("http://dockerhub.com/myimage2"), null));
        AnnotatedArtifact artifactForComponentTwoSaved = artifactRepository.save(artifactForComponentTwo);
        AnnotatedComponent componentTwo = new AnnotatedComponent("second component", List.of(propertyTwo), List.of(operation), componentTypeTwoSaved.getId(), List.of(artifactForComponentTwo), null);
        AnnotatedComponent componentTwoSaved = componentRepository.save(componentTwo);

        RelationType dependsOn = new RelationType("generic relation type", null, null, null);
        RelationType dependsOnSaved = relationTypeRepository.save(dependsOn);

        RelationType hostedOn = new RelationType("hosted on relation", null, null, dependsOnSaved.getId());
        RelationType hostedOnSaved = relationTypeRepository.save(hostedOn);
        
        RelationType connectsTo = new RelationType("connects to relation", null, null, dependsOnSaved.getId());
        RelationType connectsToSaved = relationTypeRepository.save(connectsTo);

        AnnotatedProperty propertyRelation = property;
        assertDoesNotThrow(() -> propertyRelation.setValue("value relation"));
        AnnotatedRelation relation = assertDoesNotThrow(() -> new AnnotatedRelation("relation between component one and two", List.of(propertyRelation), null, connectsToSaved.getId(), componentOne, componentTwo, null));
        AnnotatedRelation relationSaved = relationRepository.save(relation);

        AnnotatedDeploymentModel annotatedDeploymentModel = new AnnotatedDeploymentModel();
        annotatedDeploymentModel.setTransformationProcessId(UUID.randomUUID());
        annotatedDeploymentModel.setProperties(List.of(property));
        annotatedDeploymentModel.setModelEntities(List.of(baseTypeSaved.getId(), componentTypeOneSaved.getId(), 
            componentTypeTwoSaved.getId(), dependsOnSaved.getId(), hostedOnSaved.getId(), connectsToSaved.getId(), 
            componentOneSaved.getId(), componentTwoSaved.getId(), relationSaved.getId()));

        AnnotatedDeploymentModel annotatedDeploymentModelSaved = deploymentModelRepository.save(annotatedDeploymentModel);
    
        assertEquals(artifactForOperation, artifactForOperationSaved);
        assertEquals(operation, operationSaved);
        assertEquals(operationDef, operationDefSaved);
        assertEquals(baseType, baseTypeSaved);
        assertEquals(componentTypeOne, componentTypeOneSaved);
        assertEquals(artifactForComponentOne, artifactForComponentOneSaved);
        assertEquals(componentOne, componentOneSaved);
        assertEquals(componentTypeTwo, componentTypeTwoSaved);
        assertEquals(artifactForComponentTwo, artifactForComponentTwoSaved);
        assertEquals(componentTwo, componentTwoSaved);
        assertEquals(dependsOn, dependsOnSaved);
        assertEquals(hostedOn, hostedOnSaved);
        assertEquals(connectsTo, connectsToSaved);
        assertEquals(relation, relationSaved);
        assertEquals(annotatedDeploymentModel, annotatedDeploymentModelSaved);

    }
}
