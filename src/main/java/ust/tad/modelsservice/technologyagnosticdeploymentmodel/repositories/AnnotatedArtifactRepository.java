package ust.tad.modelsservice.technologyagnosticdeploymentmodel.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import ust.tad.modelsservice.technologyagnosticdeploymentmodel.annotatedentities.AnnotatedArtifact;

@Repository
public interface AnnotatedArtifactRepository extends MongoRepository<AnnotatedArtifact, String>{
    
}
