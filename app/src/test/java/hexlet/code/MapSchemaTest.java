package hexlet.code;

import hexlet.code.schemas.MapSchema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public final class MapSchemaTest {
    private MapSchema schema;
    private Map<String, String> testMap;

    @BeforeEach
    void setUp() {
        schema = new MapSchema();
        testMap = new HashMap<>();
        testMap.put("key1", "value1");
        testMap.put("key2", "value2");
    }

    @Test
    void testDefaultSchema() {
        assertTrue(schema.isValid(null));
        assertTrue(schema.isValid(new HashMap<>()));
        assertTrue(schema.isValid(testMap));
    }

    @Test
    void testRequired() {
        schema.required();

        assertFalse(schema.isValid(null));
        assertTrue(schema.isValid(new HashMap<>()));
        assertTrue(schema.isValid(testMap));
    }

    @Test
    void testSizeof() {
        schema.sizeof(2);

        assertTrue(schema.isValid(null));
        assertFalse(schema.isValid(new HashMap<>()));
        assertTrue(schema.isValid(testMap));

        testMap.put("key3", "value3");
        assertFalse(schema.isValid(testMap));
    }

    @Test
    void testRequiredWithSizeof() {
        schema.required().sizeof(2);

        assertFalse(schema.isValid(null));
        assertFalse(schema.isValid(new HashMap<>()));
        assertTrue(schema.isValid(testMap));

        testMap.remove("key1");
        assertFalse(schema.isValid(testMap));
    }

    @Test
    void testEmptyMap() {
        schema.required();
        assertTrue(schema.isValid(new HashMap<>()));

        schema.sizeof(0);
        assertTrue(schema.isValid(new HashMap<>()));
    }

    @Test
    void testLargeMap() {
        Map<String, String> largeMap = new HashMap<>();
        for (int i = 0; i < 1000; i++) {
            largeMap.put("key" + i, "value" + i);
        }

        schema.sizeof(1000);
        assertTrue(schema.isValid(largeMap));

        schema.sizeof(999);
        assertFalse(schema.isValid(largeMap));
    }

    @Test
    void testNullMap() {
        assertTrue(schema.isValid(null));

        schema.required();
        assertFalse(schema.isValid(null));

        schema.sizeof(2);
        assertFalse(schema.isValid(null)); // required имеет приоритет
    }

    @Test
    void testSizeofAfterRequired() {
        schema.required();
        schema.sizeof(1);

        Map<String, String> singleItemMap = new HashMap<>();
        singleItemMap.put("key", "value");

        assertFalse(schema.isValid(null));
        assertFalse(schema.isValid(new HashMap<>()));
        assertTrue(schema.isValid(singleItemMap));
    }
}
