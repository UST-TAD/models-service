package ust.tad.modelsservice.technologyagnosticdeploymentmodel.entities;

import java.util.List;
import java.util.UUID;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "relationtypes")
public class RelationType extends ModelElementType{
    
    public RelationType() {
        super();
    }

    public RelationType(String description, List<Property> properties, List<Operation> operations, UUID parentType) {
        super(description, properties, operations, parentType);
    }


}
