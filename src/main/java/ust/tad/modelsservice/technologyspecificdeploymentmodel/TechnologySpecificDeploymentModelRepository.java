package ust.tad.modelsservice.technologyspecificdeploymentmodel;

import java.util.List;
import java.util.UUID;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TechnologySpecificDeploymentModelRepository extends MongoRepository<TechnologySpecificDeploymentModel, UUID>{

    List<TechnologySpecificDeploymentModel> findByTransformationProcessId(UUID transformationProcessId);
}
 