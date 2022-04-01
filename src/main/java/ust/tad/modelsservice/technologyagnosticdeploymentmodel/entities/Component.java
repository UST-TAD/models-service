package ust.tad.modelsservice.technologyagnosticdeploymentmodel.entities;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

import org.springframework.data.mongodb.core.mapping.DBRef;

public class Component extends ModelElement{

    @DBRef
    private List<Artifact> artifacts;


    public Component() {
        super();
    }

    public Component(String description, List<Property> properties, List<Operation> operations, UUID type, List<Artifact> artifacts) {
        super(description, properties, operations, type);
        this.artifacts = artifacts;
    }

    public List<Artifact> getArtifacts() {
        return this.artifacts;
    }

    public void setArtifacts(List<Artifact> artifacts) {
        this.artifacts = artifacts;
    }

    public Component artifacts(List<Artifact> artifacts) {
        setArtifacts(artifacts);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Component)) {
            return false;
        }
        Component component = (Component) o;
        return Objects.equals(getId(), component.getId()) 
            && Objects.equals(getDescription(), component.getDescription()) 
            && Objects.equals(getProperties(), component.getProperties()) 
            && Objects.equals(getOperations(), component.getOperations())
            && Objects.equals(getType(), component.getType())
            && Objects.equals(artifacts, component.artifacts);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getDescription(), getProperties(), getOperations(), getType(), artifacts);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", type='" + getType() + "'" +            
            ", description='" + getDescription() + "'" +
            ", properties='" + getProperties() + "'" +
            ", operations='" + getOperations() + "'" +
            ", artifacts='" + getArtifacts() + "'" +
            "}";
    }

    
}
