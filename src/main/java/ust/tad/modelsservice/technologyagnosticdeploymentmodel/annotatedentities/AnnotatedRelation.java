package ust.tad.modelsservice.technologyagnosticdeploymentmodel.annotatedentities;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

import org.springframework.data.mongodb.core.mapping.Document;

import ust.tad.modelsservice.technologyagnosticdeploymentmodel.entities.Operation;
import ust.tad.modelsservice.technologyagnosticdeploymentmodel.entities.Property;
import ust.tad.modelsservice.technologyagnosticdeploymentmodel.entities.Relation;
import ust.tad.modelsservice.technologyagnosticdeploymentmodel.exceptions.InvalidRelationException;

@Document(collection = "relations")
public class AnnotatedRelation extends Relation{

    private Confidence confidence;


    public AnnotatedRelation() {
        super();
    }

    public AnnotatedRelation(String description, List<Property> properties, List<Operation> operations, UUID type, AnnotatedComponent source, AnnotatedComponent target, Confidence confidence) throws InvalidRelationException {
        super(description, properties, operations, type, source, target);
        this.confidence = confidence;
    }

    public Confidence getConfidence() {
        return this.confidence;
    }

    public void setConfidence(Confidence confidence) {
        this.confidence = confidence;
    }

    public AnnotatedRelation confidence(Confidence confidence) {
        setConfidence(confidence);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof AnnotatedRelation)) {
            return false;
        }
        AnnotatedRelation annotatedRelation = (AnnotatedRelation) o;
        return Objects.equals(getId(), annotatedRelation.getId()) 
            && Objects.equals(getDescription(), annotatedRelation.getDescription()) 
            && Objects.equals(getProperties(), annotatedRelation.getProperties()) 
            && Objects.equals(getOperations(), annotatedRelation.getOperations())
            && Objects.equals(getType(), annotatedRelation.getType())
            && Objects.equals(getSource(), annotatedRelation.getSource()) 
            && Objects.equals(getTarget(), annotatedRelation.getTarget()) 
            && Objects.equals(confidence, annotatedRelation.confidence);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getDescription(), getProperties(), getOperations(), getType(), getSource(), getTarget(), confidence);
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
            ", confidence='" + getConfidence() + "'" +
            "}";
    }

    
}
