package ust.tad.modelsservice.technologyagnosticdeploymentmodel;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.dataformat.yaml.YAMLGenerator;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ust.tad.modelsservice.technologyagnosticdeploymentmodel.annotatedentities.AnnotatedDeploymentModel;

@SpringBootTest
public class TechnologyAgnosticDeploymentModelServiceTest {
        
    @Autowired
    TechnologyAgnosticDeploymentModelService service;

    @Test
    public void initializeTechnologyAgnosticDeploymentModel_created() throws IOException, URISyntaxException {        
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory().enable(YAMLGenerator.Feature.INDENT_ARRAYS_WITH_INDICATOR));
        Path filePath = Path.of("src/test/resources/initialEDMM.yaml");
        String expected = Files.readString(filePath);

        UUID transformationProcessId = UUID.randomUUID();

        AnnotatedDeploymentModel dm = assertDoesNotThrow(() -> 
            service.initializeTechnologyAgnosticDeploymentModel(transformationProcessId));

        String yaml = mapper.writeValueAsString(dm);

        assertEquals(expected, yaml);
    }

}
