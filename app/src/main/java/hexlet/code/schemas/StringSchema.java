package hexlet.code.schemas;

public final class StringSchema extends BaseSchema<String> {
    private int minLength = -1;
    private String mustContain = null;

    @Override
    public StringSchema required() {
        super.required();
        return this;
    }

    public StringSchema minLength(int length) {
        minLength = length;
        return this;
    }

    public StringSchema contains(String substring) {
        mustContain = substring;
        return this;
    }

    @Override
    public boolean isValid(String value) {
        if (isRequired && (value == null || value.isEmpty())) {
            return false;
        }

        if (value == null) {
            return true;
        }

        if (minLength != -1 && value.length() < minLength) {
            return false;
        }

        if (mustContain != null && !value.contains(mustContain)) {
            return false;
        }

        return true;
    }
}
