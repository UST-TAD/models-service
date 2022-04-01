package ust.tad.modelsservice.technologyagnosticdeploymentmodel.annotatedentities;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

import org.springframework.data.mongodb.core.mapping.Document;

import ust.tad.modelsservice.technologyagnosticdeploymentmodel.entities.DeploymentModel;
import ust.tad.modelsservice.technologyagnosticdeploymentmodel.entities.Property;

@Document(collection = "technologyagnosticdeploymentmodels")
public class AnnotatedDeploymentModel extends DeploymentModel{

    private UUID transformationProcessId = UUID.randomUUID();
    

    public AnnotatedDeploymentModel() {
        super();
    }

    public AnnotatedDeploymentModel(List<Property> properties, List<UUID> modelEntities, UUID transformationProcessId) {
        super(properties, modelEntities);
        this.transformationProcessId = transformationProcessId;
    }

    public UUID getTransformationProcessId() {
        return this.transformationProcessId;
    }

    public void setTransformationProcessId(UUID transformationProcessId) {
        this.transformationProcessId = transformationProcessId;
    }

    public AnnotatedDeploymentModel transformationProcessId(UUID transformationProcessId) {
        setTransformationProcessId(transformationProcessId);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof AnnotatedDeploymentModel)) {
            return false;
        }
        AnnotatedDeploymentModel annotatedDeploymentModel = (AnnotatedDeploymentModel) o;
        return Objects.equals(getProperties(), annotatedDeploymentModel.getProperties()) 
            && Objects.equals(getModelEntities(), annotatedDeploymentModel.getModelEntities()) 
            && Objects.equals(transformationProcessId, annotatedDeploymentModel.transformationProcessId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getProperties(), getModelEntities(), transformationProcessId);
    }

    @Override
    public String toString() {
        return "{" +
            " properties='" + getProperties() + "'" +
            ", modelEntities='" + getModelEntities() + "'" +
            ", transformationProcessId='" + getTransformationProcessId() + "'" +
            "}";
    }

}
