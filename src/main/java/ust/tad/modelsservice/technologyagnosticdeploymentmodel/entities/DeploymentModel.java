package ust.tad.modelsservice.technologyagnosticdeploymentmodel.entities;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class DeploymentModel {

    private List<Property> properties;

    private List<UUID> modelEntities;

    public DeploymentModel() {
    }

    public DeploymentModel(List<Property> properties, List<UUID> modelEntities) {
        this.properties = properties;
        this.modelEntities = modelEntities;
    }

    public List<Property> getProperties() {
        return this.properties;
    }

    public void setProperties(List<Property> properties) {
        this.properties = properties;
    }

    public List<UUID> getModelEntities() {
        return this.modelEntities;
    }

    public void setModelEntities(List<UUID> modelEntities) {
        this.modelEntities = modelEntities;
    }

    public DeploymentModel properties(List<Property> properties) {
        setProperties(properties);
        return this;
    }

    public DeploymentModel modelEntities(List<UUID> modelEntities) {
        setModelEntities(modelEntities);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof DeploymentModel)) {
            return false;
        }
        DeploymentModel deploymentModel = (DeploymentModel) o;
        return Objects.equals(properties, deploymentModel.properties) 
            && Objects.equals(modelEntities, deploymentModel.modelEntities);
    }

    @Override
    public int hashCode() {
        return Objects.hash(properties, modelEntities);
    }

    @Override
    public String toString() {
        return "{" +
            " properties='" + getProperties() + "'" +
            ", modelEntities='" + getModelEntities() + "'" +
            "}";
    }
    
}
