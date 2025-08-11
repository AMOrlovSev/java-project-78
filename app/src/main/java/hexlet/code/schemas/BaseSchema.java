package hexlet.code.schemas;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Predicate;

public abstract class BaseSchema<T> {
    protected Map<String, Predicate<T>> checks = new LinkedHashMap<>();
    protected boolean required = false;

    protected final void addCheck(String name, Predicate<T> validate) {
        checks.put(name, validate);
    }

    public BaseSchema<T> required() {
        this.required = true;
        addCheck("required", value -> value != null);
        return this;
    }

    public final boolean isValid(T value) {
        if (value == null) {
            return !required;
        }

        return checks.values().stream()
                .allMatch(predicate -> predicate.test(value));
    }
}
