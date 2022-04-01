package ust.tad.modelsservice.technologyagnosticdeploymentmodel.entities;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

public abstract class ModelElement extends ModelEntity{

    private UUID type;


    public ModelElement() {
        super();
    }

    public ModelElement(String description, List<Property> properties, List<Operation> operations, UUID type) {
        super(description, properties, operations);
        this.type = type;
    }

    public UUID getType() {
        return this.type;
    }

    public void setType(UUID type) {
        this.type = type;
    }

    public ModelElement type(UUID type) {
        setType(type);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof ModelElement)) {
            return false;
        }
        ModelElement modelElement = (ModelElement) o;
        return Objects.equals(getId(), modelElement.getId()) 
            && Objects.equals(getDescription(), modelElement.getDescription()) 
            && Objects.equals(getProperties(), modelElement.getProperties()) 
            && Objects.equals(getOperations(), modelElement.getOperations())
            && Objects.equals(type, modelElement.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getDescription(), getProperties(), getOperations(), type);
    }

    @Override
    public String toString() {
        return "{" +
        " id='" + getId() + "'" +
        ", type='" + getType() + "'" +            
        ", description='" + getDescription() + "'" +
        ", properties='" + getProperties() + "'" +
        ", operations='" + getOperations() + "'" +
        "}";
    }

    
}
