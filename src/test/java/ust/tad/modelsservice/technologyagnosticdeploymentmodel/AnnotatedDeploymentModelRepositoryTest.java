package ust.tad.modelsservice.technologyagnosticdeploymentmodel;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

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
        AnnotatedDeploymentModel annotatedDeploymentModel = CreateDeploymentModelsHelper.createExampleEDMM();

        AnnotatedDeploymentModel annotatedDeploymentModelSaved = deploymentModelRepository.save(annotatedDeploymentModel);    
        assertEquals(annotatedDeploymentModel, annotatedDeploymentModelSaved);
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
