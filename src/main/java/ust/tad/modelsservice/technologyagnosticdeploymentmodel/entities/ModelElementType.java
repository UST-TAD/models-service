package ust.tad.modelsservice.technologyagnosticdeploymentmodel.entities;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

public abstract class ModelElementType extends ModelEntity{

    private UUID parentType;


    public ModelElementType() {
        super();
    }

    public ModelElementType(String description, List<Property> properties, List<Operation> operations, UUID parentType) {
        super(description, properties, operations);
        this.parentType = parentType;
    }

    public UUID getParentType() {
        return this.parentType;
    }

    public void setParentType(UUID parentType) {
        this.parentType = parentType;
    }

    public ModelElementType parentType(UUID parentType) {
        setParentType(parentType);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof ModelElementType)) {
            return false;
        }
        ModelElementType modelElementType = (ModelElementType) o;
        return Objects.equals(getId(), modelElementType.getId()) 
            && Objects.equals(getDescription(), modelElementType.getDescription()) 
            && Objects.equals(getProperties(), modelElementType.getProperties()) 
            && Objects.equals(getOperations(), modelElementType.getOperations())
            && Objects.equals(parentType, modelElementType.parentType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getDescription(), getProperties(), getOperations(), parentType);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", parentType='" + getParentType() + "'" +            
            ", description='" + getDescription() + "'" +
            ", properties='" + getProperties() + "'" +
            ", operations='" + getOperations() + "'" +
            "}";
    }

    
}
