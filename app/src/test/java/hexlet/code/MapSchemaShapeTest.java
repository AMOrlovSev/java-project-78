package hexlet.code;

import hexlet.code.schemas.BaseSchema;
import hexlet.code.schemas.MapSchema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MapSchemaShapeTest {
    private MapSchema schema;
    private Validator validator;

    @BeforeEach
    void setUp() {
        validator = new Validator();
        schema = validator.map();
    }

    @Test
    void testShapeWithStringValues() {
        Map<String, BaseSchema<String>> schemas = new HashMap<>();
        schemas.put("firstName", validator.string().required());
        schemas.put("lastName", validator.string().required().minLength(2));

        schema.shape(schemas);

        Map<String, String> validMap = new HashMap<>();
        validMap.put("firstName", "John");
        validMap.put("lastName", "Doe");
        assertTrue(schema.isValid(validMap));

        Map<String, String> invalidMap1 = new HashMap<>();
        invalidMap1.put("firstName", "");
        invalidMap1.put("lastName", "Doe");
        assertFalse(schema.isValid(invalidMap1));

        Map<String, String> invalidMap2 = new HashMap<>();
        invalidMap2.put("firstName", "Jane");
        invalidMap2.put("lastName", "D");
        assertFalse(schema.isValid(invalidMap2));
    }

    @Test
    void testShapeWithOptionalFields() {
        Map<String, BaseSchema<String>> schemas = new HashMap<>();
        schemas.put("username", validator.string().required());
        schemas.put("status", validator.string());

        schema.shape(schemas);

        Map<String, String> minimalMap = new HashMap<>();
        minimalMap.put("username", "admin");
        assertTrue(schema.isValid(minimalMap));

        minimalMap.put("status", "active");
        assertTrue(schema.isValid(minimalMap));
    }

    @Test
    void testShapeWithEmptyMap() {
        Map<String, BaseSchema<String>> schemas = new HashMap<>();
        schemas.put("id", validator.string().required());

        schema.shape(schemas);

        Map<String, String> emptyMap = new HashMap<>();
        assertFalse(schema.isValid(emptyMap));
    }

    @Test
    void testShapeWithNullValues() {
        Map<String, BaseSchema<String>> schemas = new HashMap<>();
        schemas.put("name", validator.string().required());

        schema.shape(schemas);

        Map<String, String> nullValueMap = new HashMap<>();
        nullValueMap.put("name", null);
        assertFalse(schema.isValid(nullValueMap));
    }
}
