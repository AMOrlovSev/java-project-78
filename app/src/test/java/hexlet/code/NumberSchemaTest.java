package hexlet.code;

import hexlet.code.schemas.NumberSchema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class NumberSchemaTest {
    private NumberSchema schema;

    @BeforeEach
    void setUp() {
        schema = new NumberSchema();
    }

    @Test
    void testDefaultSchema() {
        assertTrue(schema.isValid(null));
        assertTrue(schema.isValid(0));
        assertTrue(schema.isValid(-10));
        assertTrue(schema.isValid(10));
        assertTrue(schema.isValid(Integer.MAX_VALUE));
        assertTrue(schema.isValid(Integer.MIN_VALUE));
    }

    @Test
    void testRequired() {
        schema.required();

        assertFalse(schema.isValid(null));
        assertTrue(schema.isValid(0));
        assertTrue(schema.isValid(5));
        assertTrue(schema.isValid(-5));
    }

    @Test
    void testPositive() {
        schema.positive();

        assertTrue(schema.isValid(null));
        assertFalse(schema.isValid(0));
        assertFalse(schema.isValid(-5));
        assertTrue(schema.isValid(5));
    }

    @Test
    void testRange() {
        schema.range(5, 10);

        assertTrue(schema.isValid(null));
        assertFalse(schema.isValid(4));
        assertTrue(schema.isValid(5));
        assertTrue(schema.isValid(7));
        assertTrue(schema.isValid(10));
        assertFalse(schema.isValid(11));
    }

    @Test
    void testRequiredWithPositive() {
        schema.required().positive();

        assertFalse(schema.isValid(null));
        assertFalse(schema.isValid(0));
        assertFalse(schema.isValid(-5));
        assertTrue(schema.isValid(5));
    }

    @Test
    void testRequiredWithRange() {
        schema.required().range(5, 10);

        assertFalse(schema.isValid(null));
        assertFalse(schema.isValid(4));
        assertTrue(schema.isValid(5));
        assertTrue(schema.isValid(10));
        assertFalse(schema.isValid(11));
    }

    @Test
    void testPositiveWithRange() {
        schema.positive().range(5, 10);

        assertTrue(schema.isValid(null));
        assertFalse(schema.isValid(0));
        assertFalse(schema.isValid(-5));
        assertFalse(schema.isValid(4));
        assertTrue(schema.isValid(5));
        assertTrue(schema.isValid(10));
        assertFalse(schema.isValid(11));
    }

    @Test
    void testFullCombination() {
        schema.required().positive().range(5, 10);

        assertFalse(schema.isValid(null));
        assertFalse(schema.isValid(0));
        assertFalse(schema.isValid(-5));
        assertFalse(schema.isValid(4));
        assertTrue(schema.isValid(5));
        assertTrue(schema.isValid(7));
        assertTrue(schema.isValid(10));
        assertFalse(schema.isValid(11));
    }

    @Test
    void testExtremeValues() {
        schema.range(Integer.MIN_VALUE, Integer.MAX_VALUE);
        assertTrue(schema.isValid(Integer.MIN_VALUE));
        assertTrue(schema.isValid(Integer.MAX_VALUE));
    }

    @Test
    void testZeroCases() {
        schema.required();
        assertTrue(schema.isValid(0));

        schema.positive();
        assertFalse(schema.isValid(0));
    }
}
