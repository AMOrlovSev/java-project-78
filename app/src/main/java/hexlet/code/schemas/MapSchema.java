package hexlet.code.schemas;

import java.util.Map;

public class MapSchema extends BaseSchema<Map> {
    private int mapSize = -1;

    @Override
    public MapSchema required() {
        super.required();
        return this;
    }

    public MapSchema sizeof(int size) {
        mapSize = size;
        return this;
    }

    @Override
    public boolean isValid(Map value) {
        if (isRequired && (value == null)) {
            return false;
        }

        if (value == null) {
            return true;
        }

        if (mapSize != -1 && value.size() != mapSize) {
            return false;
        }

        return true;
    }
}
