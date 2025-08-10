package hexlet.code.schemas;

public class NumberSchema {
    private boolean isRequired = false;
    private boolean isPositive = false;
    Integer rangeMin;
    Integer rangeMax;

    public NumberSchema required() {
        isRequired = true;
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

    public boolean isValid(Integer value) {
        if (isRequired && value == null) {
            return false;
        }

        if (value != null && (isPositive && value <= 0)) {
            return false;
        }

        if (rangeMin != null && (value < rangeMin || value > rangeMax)) {
            return false;
        }

        return true;
    }
}
