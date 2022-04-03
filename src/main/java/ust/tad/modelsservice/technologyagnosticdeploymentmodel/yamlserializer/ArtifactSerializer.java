package ust.tad.modelsservice.technologyagnosticdeploymentmodel.yamlserializer;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import ust.tad.modelsservice.technologyagnosticdeploymentmodel.annotatedentities.AnnotatedArtifact;

public class ArtifactSerializer extends StdSerializer<AnnotatedArtifact>{

    public ArtifactSerializer() {
        this(null);
    }

    public ArtifactSerializer(Class<AnnotatedArtifact> t) {
        super(t);
    }

    @Override
    public void serialize(AnnotatedArtifact value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        gen.writeStartObject();
        gen.writeObjectFieldStart(value.getName());

        gen.writeStringField("type", value.getType());
        if (value.getFileUri() != null) {
            gen.writeStringField("fileURI", value.getFileUri().toString());
        } else {            
            gen.writeStringField("fileURI", "-");
        }

        gen.writeEndObject();
        gen.writeEndObject();        
    }
    
    
}
