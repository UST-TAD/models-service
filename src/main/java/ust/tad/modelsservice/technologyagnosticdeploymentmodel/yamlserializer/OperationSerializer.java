package ust.tad.modelsservice.technologyagnosticdeploymentmodel.yamlserializer;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import ust.tad.modelsservice.technologyagnosticdeploymentmodel.entities.Operation;


public class OperationSerializer extends StdSerializer<Operation> {

    public OperationSerializer(){
        this(null);
    }

    public OperationSerializer(Class<Operation> t) {
        super(t);
    }

    @Override
    public void serialize(Operation value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        gen.writeStartObject();
        gen.writeObjectFieldStart(value.getName());

        provider.defaultSerializeField("artifacts", value.getArtifacts(), gen);

        gen.writeEndObject();
        gen.writeEndObject();     
    }
    
}
