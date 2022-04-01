package ust.tad.modelsservice.technologyagnosticdeploymentmodel.entities;

import java.util.List;
import java.util.UUID;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "componenttypes")
public class ComponentType extends ModelElementType{

    public ComponentType() {
        super();
    }

    public ComponentType(String description, List<Property> properties, List<Operation> operations, UUID parentType) {
        super(description, properties, operations, parentType);
    }


}
