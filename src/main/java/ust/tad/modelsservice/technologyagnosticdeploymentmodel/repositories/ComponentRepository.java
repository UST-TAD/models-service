package ust.tad.modelsservice.technologyagnosticdeploymentmodel.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import ust.tad.modelsservice.technologyagnosticdeploymentmodel.entities.Component;

@Repository
public interface ComponentRepository extends MongoRepository<Component, String> {
    
}
