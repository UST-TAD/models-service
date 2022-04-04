package ust.tad.modelsservice.technologyagnosticdeploymentmodel;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.dataformat.yaml.YAMLGenerator;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ust.tad.modelsservice.technologyagnosticdeploymentmodel.annotatedentities.AnnotatedDeploymentModel;

@SpringBootTest
public class AnnotatedDeploymentModelRepositoryTest {

    @Autowired
    AnnotatedDeploymentModelRepository deploymentModelRepository;
    
    @Test
    public void createAnnotatedDeploymentModel_created() throws IOException {     
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory().enable(YAMLGenerator.Feature.INDENT_ARRAYS_WITH_INDICATOR));
        Path filePath = Path.of("src/test/resources/exampleEDMM.yaml");
        String expected = Files.readString(filePath);

        AnnotatedDeploymentModel annotatedDeploymentModel = CreateDeploymentModelsHelper.createExampleEDMM();

        AnnotatedDeploymentModel annotatedDeploymentModelSaved = deploymentModelRepository.save(annotatedDeploymentModel);    
        assertEquals(annotatedDeploymentModel, annotatedDeploymentModelSaved);

        String yaml = assertDoesNotThrow(() -> mapper.writeValueAsString(annotatedDeploymentModelSaved));
        assertEquals(expected, yaml);
    }

    @Test
    public void createAnnotatedDeploymentModelWithSameTransformationProcessId_forbidden() { 
        UUID transformationProcessId = UUID.randomUUID();   
        AnnotatedDeploymentModel first = new AnnotatedDeploymentModel(List.of(), List.of(), List.of(), List.of(), List.of(), transformationProcessId);
        AnnotatedDeploymentModel second = new AnnotatedDeploymentModel(List.of(), List.of(), List.of(), List.of(), List.of(), transformationProcessId);
        deploymentModelRepository.save(first);    
        assertThrows(Exception.class, () -> deploymentModelRepository.save(second));
    }
}
