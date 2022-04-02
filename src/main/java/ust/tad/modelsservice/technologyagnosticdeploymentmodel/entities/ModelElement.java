package ust.tad.modelsservice.technologyagnosticdeploymentmodel.entities;

import java.util.List;

public abstract class ModelElement extends ModelEntity{

    public ModelElement() {
        super();
    }

    public ModelElement(String name, String description, List<Property> properties, List<Operation> operations) {
        super(name, description, properties, operations);
    }

}
