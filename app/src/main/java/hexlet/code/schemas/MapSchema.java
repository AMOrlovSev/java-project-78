package hexlet.code.schemas;

import java.util.Map;

public final class MapSchema extends BaseSchema<Map<String, String>> {
    private int mapSize = -1;
    private Map<String, BaseSchema<String>> shapeSchemas;

    @Override
    public MapSchema required() {
        super.required();
        return this;
    }

    public MapSchema sizeof(int size) {
        mapSize = size;
        return this;
    }

    public MapSchema shape(Map<String, BaseSchema<String>> schemas) {
        shapeSchemas = schemas;
        return this;
    }

    @Override
    public boolean isValid(Map<String, String> map) {

        if (isRequired && map == null) {
            return false;
        }

        if (map == null) {
            return true;
        }

        if (mapSize != -1 && map.size() != mapSize) {
            return false;
        }

        if (shapeSchemas != null && !isValidShape(map)) {
            return false;
        }

        return true;
    }

    private boolean isValidShape(Map<String, String> inputMap) {

        for (var set : shapeSchemas.entrySet()) {
            String key = set.getKey();
            String value = inputMap.get(key);
            BaseSchema<String> schemaValue = set.getValue();
            if (!schemaValue.isValid(value)) {
                return false;
            }
        }

        return true;
    }
}
