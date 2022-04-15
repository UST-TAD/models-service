package ust.tad.modelsservice.technologyagnosticdeploymentmodel;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import ust.tad.modelsservice.technologyagnosticdeploymentmodel.entities.Property;
import ust.tad.modelsservice.technologyagnosticdeploymentmodel.entities.PropertyType;

@SpringBootTest
public class PropertyTest {

    @Test
    public void createProperty_SetTypeOnNullValue() {
        Property property = new Property();
        property.setKey("testProperty");
        assertDoesNotThrow(() -> property.setType(PropertyType.STRING));    
    }

    @Test
    public void createProperty_SetValueOnNullType() {
        Property property = new Property();
        property.setKey("testProperty");
        assertDoesNotThrow(() -> property.setValue("testValue"));    
    }

    @Test
    public void createProperty_TypeString_SetValueAfterType() {
        Property property = new Property();
        property.setKey("testProperty");
        assertDoesNotThrow(() -> property.setType(PropertyType.STRING));
        assertDoesNotThrow(() -> property.setValue("testValue"));
        property.setRequired(true);
    }

    @Test
    public void createProperty_TypeBoolean_SetValueAfterType() {
        Property property = new Property();
        property.setKey("testProperty");
        assertDoesNotThrow(() -> property.setType(PropertyType.BOOLEAN));
        assertDoesNotThrow(() -> property.setValue(true));
        property.setRequired(true);
    }

    @Test
    public void createProperty_TypeInteger_SetValueAfterType() {
        Property property = new Property();
        property.setKey("testProperty");
        assertDoesNotThrow(() -> property.setType(PropertyType.INTEGER));
        assertDoesNotThrow(() -> property.setValue(3));
        property.setRequired(true);
    }

    @Test
    public void createProperty_TypeLong_SetValueAfterType() {
        Property property = new Property();
        property.setKey("testProperty");
        assertDoesNotThrow(() -> property.setType(PropertyType.INTEGER));
        assertDoesNotThrow(() -> property.setValue(12345678910L));
        property.setRequired(true);
    }

    @Test
    public void createProperty_TypeDouble_SetValueAfterType() {
        Property property = new Property();
        property.setKey("testProperty");
        assertDoesNotThrow(() -> property.setType(PropertyType.DOUBLE));
        assertDoesNotThrow(() -> property.setValue(2.5D));
        property.setRequired(true);
    }

    @Test
    public void createProperty_TypeFloat_SetValueAfterType() {
        Property property = new Property();
        property.setKey("testProperty");
        assertDoesNotThrow(() -> property.setType(PropertyType.DOUBLE));
        assertDoesNotThrow(() -> property.setValue(2.5F));
        property.setRequired(true);
    }

    @Test
    public void createProperty_TypeString_Constructor() {
        assertDoesNotThrow(() -> new Property("testProperty", PropertyType.STRING, true, "testValue", null));
    }
    
}
