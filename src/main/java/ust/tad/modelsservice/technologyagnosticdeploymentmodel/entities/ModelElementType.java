package ust.tad.modelsservice.technologyagnosticdeploymentmodel.entities;

import java.util.List;

public abstract class ModelElementType extends ModelEntity{

    public ModelElementType() {
        super();
    }

    public ModelElementType(String name, String description, List<Property> properties, List<Operation> operations) {
        super(name, description, properties, operations);
    }

}
