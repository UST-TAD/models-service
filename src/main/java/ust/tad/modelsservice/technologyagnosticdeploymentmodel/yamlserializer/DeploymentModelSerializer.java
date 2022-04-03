package ust.tad.modelsservice.technologyagnosticdeploymentmodel.yamlserializer;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import ust.tad.modelsservice.technologyagnosticdeploymentmodel.annotatedentities.AnnotatedDeploymentModel;

public class DeploymentModelSerializer extends StdSerializer<AnnotatedDeploymentModel>{
    
    public DeploymentModelSerializer() {
        this(null);
    }
  
    public DeploymentModelSerializer(Class<AnnotatedDeploymentModel> t) {
        super(t);
    }

    @Override
    public void serialize(AnnotatedDeploymentModel value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        gen.writeStartObject();       
        provider.defaultSerializeField("properties", value.getProperties(), gen);
        provider.defaultSerializeField("components", value.getComponents(), gen);
        provider.defaultSerializeField("relations", value.getRelations(), gen);
        provider.defaultSerializeField("component_types", value.getComponentTypes(), gen);
        provider.defaultSerializeField("relation_types", value.getRelationTypes(), gen);
        gen.writeEndObject();
    }
}
