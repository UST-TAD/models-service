package ust.tad.modelsservice.technologyagnosticdeploymentmodel.yamlserializer;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import ust.tad.modelsservice.technologyagnosticdeploymentmodel.entities.Property;

public class PropertySerializer extends StdSerializer<Property>{

    public PropertySerializer() {
        this(null);
    }
  
    public PropertySerializer(Class<Property> t) {
        super(t);
    }

    @Override
    public void serialize(Property value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        gen.writeStartObject();
        switch(value.getType()) {
            case BOOLEAN:
                gen.writeBooleanField(value.getKey(), (Boolean) value.getValue());                
            case DOUBLE:
                gen.writeNumberField(value.getKey(), (Double) value.getValue());                
            case INTEGER:
                gen.writeNumberField(value.getKey(), (Integer) value.getValue());
            case STRING:
                gen.writeStringField(value.getKey(), value.getValue().toString());
                break;
            default:
                break;
        }
        gen.writeEndObject();
    }
    
}
