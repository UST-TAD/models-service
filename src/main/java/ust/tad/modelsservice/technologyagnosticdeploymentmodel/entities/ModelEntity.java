package ust.tad.modelsservice.technologyagnosticdeploymentmodel.entities;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

public abstract class ModelEntity {
    
    @Id
    private UUID id = UUID.randomUUID();

    private String description;

    private List<Property> properties;

    @DBRef
    private List<Operation> operations;


    public ModelEntity() {
    }

    public ModelEntity(String description, List<Property> properties, List<Operation> operations) {
        this.description = description;
        this.properties = properties;
        this.operations = operations;
    }

    public UUID getId() {
        return this.id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Property> getProperties() {
        return this.properties;
    }

    public void setProperties(List<Property> properties) {
        this.properties = properties;
    }

    public List<Operation> getOperations() {
        return this.operations;
    }

    public void setOperations(List<Operation> operations) {
        this.operations = operations;
    }

    public ModelEntity id(UUID id) {
        setId(id);
        return this;
    }

    public ModelEntity description(String description) {
        setDescription(description);
        return this;
    }

    public ModelEntity properties(List<Property> properties) {
        setProperties(properties);
        return this;
    }

    public ModelEntity operations(List<Operation> operations) {
        setOperations(operations);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof ModelEntity)) {
            return false;
        }
        ModelEntity modelEntity = (ModelEntity) o;
        return Objects.equals(id, modelEntity.id) && Objects.equals(description, modelEntity.description) && Objects.equals(properties, modelEntity.properties) && Objects.equals(operations, modelEntity.operations);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description, properties, operations);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", description='" + getDescription() + "'" +
            ", properties='" + getProperties() + "'" +
            ", operations='" + getOperations() + "'" +
            "}";
    }

    
}
