package hexlet.code;

import hexlet.code.schemas.StringSchema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public final class StringSchemaTest {

    private Validator v;

    private StringSchema schema;

    @BeforeEach
    public void beforeEach() {
        v = new Validator();
        schema = v.string();
    }

    @Test
    void testNonRequiredString() {
        assertTrue(schema.isValid(null));
        assertTrue(schema.isValid(""));
        assertTrue(schema.isValid("test"));
    }

    @Test
    void testRequiredString() {
        schema.required();
        assertFalse(schema.isValid(null));
        assertFalse(schema.isValid(""));
        assertTrue(schema.isValid("test"));
    }

    @Test
    void testMinLength() {
        schema.minLength(3);
        assertFalse(schema.isValid("ab"));
        assertTrue(schema.isValid("abc"));
        assertTrue(schema.isValid("abcd"));
    }

    @Test
    void testContains() {
        schema.contains("hello");
        assertFalse(schema.isValid("hi"));
        assertTrue(schema.isValid("hello world"));
    }

    @Test
    void testCombinedRules() {
        schema.required().minLength(5).contains("test");
        assertFalse(schema.isValid(null));
        assertFalse(schema.isValid("tes"));
        assertFalse(schema.isValid("hello"));
        assertTrue(schema.isValid("test 123"));
    }
}
