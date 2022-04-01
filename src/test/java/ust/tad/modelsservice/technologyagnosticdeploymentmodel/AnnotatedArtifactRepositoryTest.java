package ust.tad.modelsservice.technologyagnosticdeploymentmodel;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.net.URI;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ust.tad.modelsservice.technologyagnosticdeploymentmodel.annotatedentities.AnnotatedArtifact;
import ust.tad.modelsservice.technologyagnosticdeploymentmodel.repositories.AnnotatedArtifactRepository;

@SpringBootTest
public class AnnotatedArtifactRepositoryTest {

    @Autowired
    AnnotatedArtifactRepository repository;

    @Test
    public void createArtifact_created() {
        AnnotatedArtifact artifact = assertDoesNotThrow(() -> 
            new AnnotatedArtifact("MyImage", "Docker Image", new URI("http://dockerhub.com/myimage"), null));
        
        AnnotatedArtifact artifactSaved = repository.save(artifact);
        assertEquals(artifact, artifactSaved);
    }
    
}
