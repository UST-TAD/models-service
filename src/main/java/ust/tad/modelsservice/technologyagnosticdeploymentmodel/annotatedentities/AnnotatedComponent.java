package ust.tad.modelsservice.technologyagnosticdeploymentmodel.annotatedentities;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

import org.springframework.data.mongodb.core.mapping.Document;

import ust.tad.modelsservice.technologyagnosticdeploymentmodel.entities.Artifact;
import ust.tad.modelsservice.technologyagnosticdeploymentmodel.entities.Component;
import ust.tad.modelsservice.technologyagnosticdeploymentmodel.entities.Operation;
import ust.tad.modelsservice.technologyagnosticdeploymentmodel.entities.Property;

@Document(collection = "components")
public class AnnotatedComponent extends Component{

    private Confidence confidence;


    public AnnotatedComponent() {
        super();
    }

    public AnnotatedComponent(String description, List<Property> properties, List<Operation> operations, UUID type, List<Artifact> artifacts, Confidence confidence) {
        super(description, properties, operations, type, artifacts);
        this.confidence = confidence;
    }

    public Confidence getConfidence() {
        return this.confidence;
    }

    public void setConfidence(Confidence confidence) {
        this.confidence = confidence;
    }

    public AnnotatedComponent confidence(Confidence confidence) {
        setConfidence(confidence);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof AnnotatedComponent)) {
            return false;
        }
        AnnotatedComponent annotatedComponent = (AnnotatedComponent) o;
        return Objects.equals(getId(), annotatedComponent.getId()) 
            && Objects.equals(getDescription(), annotatedComponent.getDescription()) 
            && Objects.equals(getProperties(), annotatedComponent.getProperties()) 
            && Objects.equals(getOperations(), annotatedComponent.getOperations())
            && Objects.equals(getType(), annotatedComponent.getType())
            && Objects.equals(getArtifacts(), annotatedComponent.getArtifacts())
            && Objects.equals(confidence, annotatedComponent.confidence);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getDescription(), getProperties(), getOperations(), getType(), getArtifacts(), confidence);
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
            ", confidence='" + getConfidence() + "'" +
            "}";
    }

    
}
