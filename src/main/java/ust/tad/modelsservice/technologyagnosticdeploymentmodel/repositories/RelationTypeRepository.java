package ust.tad.modelsservice.technologyagnosticdeploymentmodel.repositories;

import java.util.UUID;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import ust.tad.modelsservice.technologyagnosticdeploymentmodel.entities.RelationType;

@Repository
public interface RelationTypeRepository extends MongoRepository<RelationType, UUID> {
    
}
