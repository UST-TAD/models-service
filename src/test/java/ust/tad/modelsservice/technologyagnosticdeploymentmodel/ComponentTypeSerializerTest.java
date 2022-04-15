package ust.tad.modelsservice.technologyagnosticdeploymentmodel;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import ust.tad.modelsservice.technologyagnosticdeploymentmodel.entities.ComponentType;
import ust.tad.modelsservice.technologyagnosticdeploymentmodel.entities.Operation;
import ust.tad.modelsservice.technologyagnosticdeploymentmodel.entities.Property;
import ust.tad.modelsservice.technologyagnosticdeploymentmodel.entities.PropertyType;
import ust.tad.modelsservice.technologyagnosticdeploymentmodel.yamlserializer.YamlObjectMapper;

@SpringBootTest
public class ComponentTypeSerializerTest {
    
    @Test
    public void serializeBaseComponentType_correct() throws JsonProcessingException {
        String expected = 
            "---\n"
            + "BaseType:\n"
            + "  extends: \"-\"\n"
            + "  description: \"This is the base type\"\n"
            + "  properties: []\n"
            + "  operations: []\n";

        ComponentType baseType = new ComponentType("BaseType", "This is the base type", List.of(), List.of(), null);
    
        ObjectMapper mapper = YamlObjectMapper.createYamlObjectMapper();
        String yaml = mapper.writeValueAsString(baseType);
    
        assertEquals(expected, yaml);
    }

    @Test
    public void serializeComponentTypes_correct() throws JsonProcessingException {
        String expected = 
            "---\n"
            + "TypeOne:\n"
            + "  extends: \"BaseType\"\n"
            + "  description: \"type one\"\n"
            + "  properties:\n"
            + "    - testProperty:\n"
            + "        type: \"STRING\"\n"
            + "        required: true\n"
            + "        default_value: \"testValue\"\n"
            + "  operations:\n"
            + "    - Create: \"~\"\n";

        Property property = assertDoesNotThrow(() -> new Property("testProperty", PropertyType.STRING, true, "testValue", null));
        Operation operationDef = new Operation("Create", null, null);

        ComponentType baseType = new ComponentType("BaseType", "This is the base type", List.of(), List.of(), null);
        ComponentType componentTypeOne = new ComponentType("TypeOne", "type one", List.of(property), List.of(operationDef), baseType);

        ObjectMapper mapper = YamlObjectMapper.createYamlObjectMapper();
        String yaml = mapper.writeValueAsString(componentTypeOne);

        assertEquals(expected, yaml);
    }
    
}
