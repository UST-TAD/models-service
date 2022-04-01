package ust.tad.modelsservice.technologyagnosticdeploymentmodel.repositories;

import java.util.UUID;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import ust.tad.modelsservice.technologyagnosticdeploymentmodel.annotatedentities.AnnotatedComponent;

@Repository
public interface AnnotatedComponentRepository extends MongoRepository<AnnotatedComponent, UUID>{
    
}
