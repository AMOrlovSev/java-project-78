package hexlet.code.schemas;

public abstract class BaseSchema<T> {
    protected boolean isRequired = false;

    /**
     * Marks the schema as required, meaning the value cannot be null.
     * Subclasses should call this method first when overriding it:
     * {@code super.required();}
     *
     * @return the current schema instance for method chaining
     */
    public BaseSchema<T> required() {
        this.isRequired = true;
        return this;
    }

    public abstract boolean isValid(T value);
}
