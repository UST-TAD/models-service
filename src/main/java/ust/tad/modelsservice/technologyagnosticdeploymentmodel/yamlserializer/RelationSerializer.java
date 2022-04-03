package ust.tad.modelsservice.technologyagnosticdeploymentmodel.yamlserializer;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import ust.tad.modelsservice.technologyagnosticdeploymentmodel.entities.Relation;

public class RelationSerializer extends StdSerializer<Relation>{
    
    public RelationSerializer() {
        this(null);
    }

    protected RelationSerializer(Class<Relation> t) {
        super(t);
    }

    @Override
    public void serialize(Relation value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        gen.writeStartObject();
        gen.writeObjectFieldStart(value.getName());

        if(value.getType() != null) {
            gen.writeStringField("type", value.getType().getName());
        } else {
            gen.writeStringField("type", "-");
        }
        
        gen.writeStringField("description", value.getDescription());

        if(value.getSource() != null) {
            gen.writeStringField("source", value.getSource().getName()); 
        } else {
            gen.writeStringField("source", "-");
        }

        if(value.getTarget() != null) {
            gen.writeStringField("target", value.getTarget().getName());
        } else {
            gen.writeStringField("target", "-");
        }

        provider.defaultSerializeField("properties", value.getProperties(), gen);

        provider.defaultSerializeField("operations", value.getOperations(), gen);  

        gen.writeEndObject();
        gen.writeEndObject();        
    }
    
}
