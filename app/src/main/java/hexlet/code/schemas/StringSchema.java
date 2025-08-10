package hexlet.code.schemas;

public class StringSchema {
    private boolean isRequired = false;
    private int minLength = -1;
    private String mustContain = null;

    public StringSchema required() {
        isRequired = true;
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

    public boolean isValid(String value) {

        if (isRequired && (value == null || value.isEmpty())) {
            return false;
        }

        if (minLength != -1 && (value == null || value.length() < minLength)) {
            return false;
        }

        if (mustContain != null && (value == null || !value.contains(mustContain))) {
            return false;
        }

        return true;
    }
}
