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

    /**
     * Sets the schema to require non-null values for validation.
     * <p>
     * When activated, this validation will fail for any null values passed to {@link #isValid(Object)}.
     * The requirement is stored as a predicate check with the key "required".
     * </p>
     *
     * <p><b>Note for implementers:</b>
     * <ul>
     *   <li>Subclasses should call {@code super.required()} when overriding this method</li>
     *   <li>Subclasses may add additional requiredness checks (like empty string validation)</li>
     *   <li>Multiple calls will overwrite the previous required check</li>
     * </ul>
     * </p>
     *
     * @return the current schema instance (this) to enable method chaining
     *
     * @see #isValid(Object) For the validation execution
     * @see StringSchema#required() For an example of extended required validation
     * @see NumberSchema#required() For number-specific required validation
     *
     * @example
     * // Basic usage
     * BaseSchema<String> schema = new StringSchema().required();
     * schema.isValid(null);  // returns false
     * schema.isValid("");    // returns true (unless overridden by subclass)
     */
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
