package ust.tad.modelsservice.technologyagnosticdeploymentmodel.yamlserializer;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import ust.tad.modelsservice.technologyagnosticdeploymentmodel.annotatedentities.AnnotatedOperation;

public class OperationSerializer extends StdSerializer<AnnotatedOperation> {

    public OperationSerializer(){
        this(null);
    }

    public OperationSerializer(Class<AnnotatedOperation> t) {
        super(t);
    }

    @Override
    public void serialize(AnnotatedOperation value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        gen.writeStartObject();
        gen.writeObjectFieldStart(value.getName());

        provider.defaultSerializeField("artifatcs", value.getArtifacts(), gen);

        gen.writeEndObject();
        gen.writeEndObject();     
    }
    
}
