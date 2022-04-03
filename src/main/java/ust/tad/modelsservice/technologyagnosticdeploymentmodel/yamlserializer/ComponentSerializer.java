package ust.tad.modelsservice.technologyagnosticdeploymentmodel.yamlserializer;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import ust.tad.modelsservice.technologyagnosticdeploymentmodel.entities.Component;

public class ComponentSerializer extends StdSerializer<Component> {

    public ComponentSerializer() {
        this(null);
    }

    public ComponentSerializer(Class<Component> t) {
        super(t);
    }

    @Override
    public void serialize(Component value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        gen.writeStartObject();
        gen.writeObjectFieldStart(value.getName());

        if(value.getType() != null) {
            gen.writeStringField("type", value.getType().getName());
        } else {
            gen.writeStringField("type", "-");
        }
        
        gen.writeStringField("description", value.getDescription());

        provider.defaultSerializeField("properties", value.getProperties(), gen);

        provider.defaultSerializeField("operations", value.getOperations(), gen);          

        provider.defaultSerializeField("artifatcs", value.getArtifacts(), gen);

        gen.writeEndObject();
        gen.writeEndObject();       
    }
    
}
