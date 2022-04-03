package ust.tad.modelsservice.technologyagnosticdeploymentmodel.annotatedentities;

import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import ust.tad.modelsservice.technologyagnosticdeploymentmodel.entities.Artifact;
import ust.tad.modelsservice.technologyagnosticdeploymentmodel.entities.Operation;
import ust.tad.modelsservice.technologyagnosticdeploymentmodel.yamlserializer.OperationSerializer;

@JsonSerialize(using = OperationSerializer.class)
public class AnnotatedOperation extends Operation{

    private Confidence confidence;

    public AnnotatedOperation() {
        super();
    }

    public AnnotatedOperation(String name, List<Artifact> artifacts, Confidence confidence) {
        super(name, artifacts);
        this.confidence = confidence;
    }

    public Confidence getConfidence() {
        return this.confidence;
    }

    public void setConfidence(Confidence confidence) {
        this.confidence = confidence;
    }

    public AnnotatedOperation confidence(Confidence confidence) {
        setConfidence(confidence);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof AnnotatedOperation)) {
            return false;
        }
        AnnotatedOperation annotatedOperation = (AnnotatedOperation) o;
        return Objects.equals(getName(), annotatedOperation.getName()) 
            && Objects.equals(getArtifacts(), annotatedOperation.getArtifacts()) 
            && Objects.equals(confidence, annotatedOperation.confidence);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getArtifacts(), confidence);
    }

    @Override
    public String toString() {
        return "{" +
            " name='" + getName() + "'" +
            ", artifacts='" + getArtifacts() + "'" +
            ", confidence='" + getConfidence() + "'" +
            "}";
    }

    
}
