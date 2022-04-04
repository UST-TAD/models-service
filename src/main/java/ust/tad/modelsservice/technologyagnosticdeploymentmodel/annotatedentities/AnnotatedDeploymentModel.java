package ust.tad.modelsservice.technologyagnosticdeploymentmodel.annotatedentities;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import ust.tad.modelsservice.technologyagnosticdeploymentmodel.entities.Component;
import ust.tad.modelsservice.technologyagnosticdeploymentmodel.entities.ComponentType;
import ust.tad.modelsservice.technologyagnosticdeploymentmodel.entities.DeploymentModel;
import ust.tad.modelsservice.technologyagnosticdeploymentmodel.entities.Property;
import ust.tad.modelsservice.technologyagnosticdeploymentmodel.entities.Relation;
import ust.tad.modelsservice.technologyagnosticdeploymentmodel.entities.RelationType;
import ust.tad.modelsservice.technologyagnosticdeploymentmodel.yamlserializer.DeploymentModelSerializer;

@Document(collection = "technologyagnosticdeploymentmodels")
@JsonSerialize(using = DeploymentModelSerializer.class)
public class AnnotatedDeploymentModel extends DeploymentModel{

    @Indexed(unique=true)
    private UUID transformationProcessId;
    

    public AnnotatedDeploymentModel() {
        super();
    }

    public AnnotatedDeploymentModel(List<Property> properties, List<Component> components, List<Relation> relations, List<ComponentType> componentTypes, List<RelationType> relationTypes, UUID transformationProcessId) {
        super(properties, components, relations, componentTypes, relationTypes);
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
            && Objects.equals(getComponents(), annotatedDeploymentModel.getComponents()) 
            && Objects.equals(getRelations(), annotatedDeploymentModel.getRelations()) 
            && Objects.equals(getComponentTypes(), annotatedDeploymentModel.getComponentTypes()) 
            && Objects.equals(getRelationTypes(), annotatedDeploymentModel.getRelationTypes())
            && Objects.equals(transformationProcessId, annotatedDeploymentModel.transformationProcessId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getProperties(), getComponents(), getRelations(), getComponentTypes(), getRelationTypes(), transformationProcessId);
    }

    @Override
    public String toString() {
        return "{" +
            " properties='" + getProperties() + "'" +
            ", components='" + getComponents() + "'" +
            ", relations='" + getRelations() + "'" +
            ", componentTypes='" + getComponentTypes() + "'" +
            ", relationTypes='" + getRelationTypes() + "'" +
            ", transformationProcessId='" + getTransformationProcessId() + "'" +
            "}";
    }

}
