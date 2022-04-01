package ust.tad.modelsservice.technologyagnosticdeploymentmodel;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.net.URI;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ust.tad.modelsservice.technologyagnosticdeploymentmodel.annotatedentities.AnnotatedArtifact;
import ust.tad.modelsservice.technologyagnosticdeploymentmodel.annotatedentities.AnnotatedComponent;
import ust.tad.modelsservice.technologyagnosticdeploymentmodel.annotatedentities.AnnotatedOperation;
import ust.tad.modelsservice.technologyagnosticdeploymentmodel.annotatedentities.AnnotatedProperty;
import ust.tad.modelsservice.technologyagnosticdeploymentmodel.entities.ComponentType;
import ust.tad.modelsservice.technologyagnosticdeploymentmodel.entities.PropertyType;
import ust.tad.modelsservice.technologyagnosticdeploymentmodel.repositories.AnnotatedArtifactRepository;
import ust.tad.modelsservice.technologyagnosticdeploymentmodel.repositories.AnnotatedComponentRepository;
import ust.tad.modelsservice.technologyagnosticdeploymentmodel.repositories.AnnotatedOperationRepository;
import ust.tad.modelsservice.technologyagnosticdeploymentmodel.repositories.ComponentTypeRepository;

@SpringBootTest
public class AnnotatedComponentRepositoryTest {

    @Autowired
    AnnotatedComponentRepository componentRepository;
    
    @Autowired
    AnnotatedArtifactRepository artifactRepository;

    @Autowired
    AnnotatedOperationRepository operationRepository;

    @Autowired
    ComponentTypeRepository componentTypeRepository;

    @Test
    public void createComponent_created() {
        AnnotatedProperty property = assertDoesNotThrow(() -> new AnnotatedProperty("testProperty", PropertyType.STRING, true, "testValue", null));

        AnnotatedArtifact artifactForOperation = assertDoesNotThrow(() -> 
            new AnnotatedArtifact("Create Script", "Shellscript", new URI("file://path/create.sh"), null));
        artifactRepository.save(artifactForOperation);
        AnnotatedOperation operation = new AnnotatedOperation("Create", List.of(artifactForOperation), null);
        AnnotatedOperation operationDef = new AnnotatedOperation("Create", null, null);
        operationRepository.saveAll(List.of(operation, operationDef));

        ComponentType componentType = new ComponentType("type", List.of(property), List.of(operationDef), null);
        ComponentType savedComponentType = componentTypeRepository.save(componentType);

        AnnotatedArtifact artifact = assertDoesNotThrow(() -> 
            new AnnotatedArtifact("MyImage", "Docker Image", new URI("http://dockerhub.com/myimage"), null));
        artifactRepository.save(artifact);
            
        AnnotatedComponent component = new AnnotatedComponent("test", List.of(property), List.of(operation), savedComponentType.getId(), List.of(artifact), null);
        AnnotatedComponent savedComponent = componentRepository.save(component);
        assertEquals(component, savedComponent);
    }
    
}
