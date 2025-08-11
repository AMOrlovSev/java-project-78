package hexlet.code.schemas;

public final class NumberSchema extends BaseSchema<Integer> {
    public NumberSchema positive() {
        addCheck("positive", value -> value > 0);
        return this;
    }

    public NumberSchema range(int min, int max) {
        addCheck("range", value -> value >= min && value <= max);
        return this;
    }

    @Override
    public NumberSchema required() {
        super.required();
        return this;
    }
}
