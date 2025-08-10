package hexlet.code;

import hexlet.code.schemas.StringSchema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StringSchemaTest {

    Validator v;

    StringSchema schema;

    @BeforeEach
    public void beforeEach() {
        v = new Validator();
        schema = v.string();
    }

    @Test
    public void notImplementsTest() {
        assertTrue(schema.isValid(""));
        assertTrue(schema.isValid(null));
    }

    @Test
    public void requiredTest() {
        schema.required();
        assertFalse(schema.isValid(""));
        assertFalse(schema.isValid(null));
        assertTrue(schema.isValid("a"));
    }

    @Test
    public void minLengthTest() {
        schema.minLength(4);
        assertTrue(schema.isValid("Hexlet"));
        schema.minLength(10);
        assertFalse(schema.isValid("Hexlet"));
    }

    @Test
    public void containsTest() {
        schema.contains("wh");
        assertTrue(schema.isValid("what does the fox say"));
        schema.contains("whatthe");
        assertFalse(schema.isValid("what does the fox say"));
    }
}
