package hexlet.code.schemas;

public final class NumberSchema extends BaseSchema<Integer> {
    private boolean isPositive = false;
    private Integer rangeMin;
    private Integer rangeMax;

    @Override
    public NumberSchema required() {
        super.required();
        return this;
    }

    public NumberSchema positive() {
        isPositive = true;
        return this;
    }

    public NumberSchema range(Integer min, Integer max) {
        rangeMin = min;
        rangeMax = max;
        return this;
    }

    @Override
    public boolean isValid(Integer value) {
        if (isRequired && value == null) {
            return false;
        }

        if (value == null) {
            return true;
        }

        if (isPositive && value <= 0) {
            return false;
        }

        if (rangeMin != null && (value < rangeMin || value > rangeMax)) {
            return false;
        }

        return true;
    }
}
