package ust.tad.modelsservice.technologyagnosticdeploymentmodel;

import java.util.List;
import java.util.UUID;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import ust.tad.modelsservice.technologyagnosticdeploymentmodel.annotatedentities.AnnotatedDeploymentModel;

@Repository
public interface AnnotatedDeploymentModelRepository extends MongoRepository<AnnotatedDeploymentModel, String>{
    
    List<AnnotatedDeploymentModel> findByTransformationProcessId(UUID transformationProcessId);
    
}
