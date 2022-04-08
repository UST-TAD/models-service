package ust.tad.modelsservice.technologyagnosticdeploymentmodel;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ust.tad.modelsservice.technologyagnosticdeploymentmodel.annotatedentities.AnnotatedDeploymentModel;
import ust.tad.modelsservice.technologyagnosticdeploymentmodel.yamlserializer.YamlObjectMapper;

@SpringBootTest
public class TechnologyAgnosticDeploymentModelServiceTest {
        
    @Autowired
    TechnologyAgnosticDeploymentModelService service;

    @Autowired
    AnnotatedDeploymentModelRepository repository;

    @Test
    public void initializeTechnologyAgnosticDeploymentModel_created() throws IOException, URISyntaxException {        
        ObjectMapper mapper = YamlObjectMapper.createYamlObjectMapper();
        Path filePath = Path.of("src/test/resources/initialEDMM.yaml");
        String expected = Files.readString(filePath);

        UUID transformationProcessId = UUID.randomUUID();

        AnnotatedDeploymentModel dm = assertDoesNotThrow(() -> 
            service.initializeTechnologyAgnosticDeploymentModel(transformationProcessId));

        String yaml = mapper.writeValueAsString(dm);

        assertEquals(expected, yaml);
    }

    @Test
    public void exportTechnologyAgnosticDeploymentModel_exported() {
        AnnotatedDeploymentModel annotatedDeploymentModel = CreateDeploymentModelsHelper.createExampleEDMM();
        AnnotatedDeploymentModel annotatedDeploymentModelSaved = repository.save(annotatedDeploymentModel);

        assertDoesNotThrow(() -> service.exportTechnologyAgnosticDeploymentModel(annotatedDeploymentModelSaved.getTransformationProcessId()));
    }

}
