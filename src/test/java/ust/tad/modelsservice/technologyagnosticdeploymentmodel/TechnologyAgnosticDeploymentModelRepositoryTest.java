package ust.tad.modelsservice.technologyagnosticdeploymentmodel;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ust.tad.modelsservice.technologyagnosticdeploymentmodel.entities.TechnologyAgnosticDeploymentModel;
import ust.tad.modelsservice.technologyagnosticdeploymentmodel.repositories.TechnologyAgnosticDeploymentModelRepository;

@SpringBootTest
public class TechnologyAgnosticDeploymentModelRepositoryTest {

    @Autowired
    TechnologyAgnosticDeploymentModelRepository deploymentModelRepository;
    
    @Test
    public void createTechnologyAgnosticDeploymentModel_created() throws IOException {     
        TechnologyAgnosticDeploymentModel tadm = CreateDeploymentModelsHelper.createExampleEDMM();

        TechnologyAgnosticDeploymentModel annotatedDeploymentModelSaved = deploymentModelRepository.save(tadm);    
        assertEquals(tadm, annotatedDeploymentModelSaved);
    }

    @Test
    public void createAnnotatedDeploymentModelWithSameTransformationProcessId_forbidden() { 
        UUID transformationProcessId = UUID.randomUUID();   
        TechnologyAgnosticDeploymentModel first = new TechnologyAgnosticDeploymentModel(transformationProcessId, List.of(), List.of(), List.of(), List.of(), List.of());
        TechnologyAgnosticDeploymentModel second = new TechnologyAgnosticDeploymentModel(transformationProcessId, List.of(), List.of(), List.of(), List.of(), List.of());
        deploymentModelRepository.save(first);    
        assertThrows(Exception.class, () -> deploymentModelRepository.save(second));
    }
}
