package ust.tad.modelsservice.technologyagnosticdeploymentmodel.entities;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

import org.springframework.data.mongodb.core.mapping.DBRef;

import ust.tad.modelsservice.technologyagnosticdeploymentmodel.exceptions.InvalidRelationException;

public class Relation extends ModelElement{

    @DBRef
    private Component source;

    @DBRef
    private Component target;


    private static final String INVALIDRELATIONEXCEPTIONMESSAGE = "The source and the target of a relation must not be the same component.";


    public Relation() {
        super();
    }

    public Relation(String description, List<Property> properties, List<Operation> operations, UUID type, Component source, Component target) throws InvalidRelationException {
        super(description, properties, operations, type);
        if(areSourceAndTargetEqual(source, target)) {
            throw new InvalidRelationException(INVALIDRELATIONEXCEPTIONMESSAGE);
        } else {
            this.source = source;
            this.target = target;
        }
    }

    public Component getSource() {
        return this.source;
    }

    public void setSource(Component source) throws InvalidRelationException {
        if(areSourceAndTargetEqual(source, this.target)) {
            throw new InvalidRelationException(INVALIDRELATIONEXCEPTIONMESSAGE);
        } else {
            this.source = source;
        }
    }

    public Component getTarget() {
        return this.target;
    }

    public void setTarget(Component target) throws InvalidRelationException {
        if(areSourceAndTargetEqual(this.source, target)) {
            throw new InvalidRelationException(INVALIDRELATIONEXCEPTIONMESSAGE);
        } else {
            this.target = target;
        }
    }

    public Relation source(Component source) throws InvalidRelationException {
        setSource(source);
        return this;
    }

    public Relation target(Component target) throws InvalidRelationException {
        setTarget(target);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Relation)) {
            return false;
        }
        Relation relation = (Relation) o;
        return Objects.equals(getId(), relation.getId()) 
            && Objects.equals(getDescription(), relation.getDescription()) 
            && Objects.equals(getProperties(), relation.getProperties()) 
            && Objects.equals(getOperations(), relation.getOperations())
            && Objects.equals(getType(), relation.getType())
            && Objects.equals(source, relation.source) 
            && Objects.equals(target, relation.target);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getDescription(), getProperties(), getOperations(), getType(), source, target);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", type='" + getType() + "'" +            
            ", description='" + getDescription() + "'" +
            ", properties='" + getProperties() + "'" +
            ", operations='" + getOperations() + "'" +
            ", source='" + getSource() + "'" +
            ", target='" + getTarget() + "'" +
            "}";
    }

    private Boolean areSourceAndTargetEqual(Component source, Component target) {
        return source.equals(target);
    }


    
}
