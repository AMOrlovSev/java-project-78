package hexlet.code.schemas;

import java.util.Map;
import java.util.function.Predicate;

public final class MapSchema extends BaseSchema<Map<String, String>> {
    public MapSchema sizeof(int size) {
        addCheck("sizeof", value -> value.size() == size);
        return this;
    }

    public MapSchema shape(Map<String, BaseSchema<String>> schemas) {
        Predicate<Map<String, String>> shapeCheck = map -> {
            for (var entry : schemas.entrySet()) {
                String key = entry.getKey();
                String value = map.get(key);
                BaseSchema<String> schema = entry.getValue();

                if (!schema.isValid(value)) {
                    return false;
                }
            }
            return true;
        };

        addCheck("shape", shapeCheck);
        return this;
    }

    @Override
    public MapSchema required() {
        super.required();
        return this;
    }
}
