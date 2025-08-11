package hexlet.code.schemas;

public final class StringSchema extends BaseSchema<String> {
    @Override
    public StringSchema required() {
        super.required();
        addCheck("nonEmpty", value -> !value.isEmpty());
        return this;
    }

    public StringSchema minLength(int length) {
        addCheck("minLength" + length, value -> value.length() >= length);
        return this;
    }

    public StringSchema contains(String substring) {
        addCheck("contains" + substring, value -> value.contains(substring));
        return this;
    }
}
