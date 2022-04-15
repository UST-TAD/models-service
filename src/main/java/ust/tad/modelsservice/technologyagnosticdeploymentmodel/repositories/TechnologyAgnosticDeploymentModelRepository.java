package ust.tad.modelsservice.technologyagnosticdeploymentmodel.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import ust.tad.modelsservice.technologyagnosticdeploymentmodel.entities.TechnologyAgnosticDeploymentModel;


@Repository
public interface TechnologyAgnosticDeploymentModelRepository extends MongoRepository<TechnologyAgnosticDeploymentModel, String>{
    
    List<TechnologyAgnosticDeploymentModel> findByTransformationProcessId(UUID transformationProcessId);
    
}
