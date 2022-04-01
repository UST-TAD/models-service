package ust.tad.modelsservice.technologyagnosticdeploymentmodel.repositories;

import java.util.UUID;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import ust.tad.modelsservice.technologyagnosticdeploymentmodel.annotatedentities.AnnotatedRelation;

@Repository
public interface AnnotatedRelationRepository extends MongoRepository<AnnotatedRelation, UUID>{
    
}
