package hexlet.code.schemas;

public class StringSchema {
    private String value;
    private boolean isRequired = false;
    private int minLength = -1; // -1 означает, что проверка отключена
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

    public boolean isValid(String str) {
        this.value = str;

        if (isRequired && (str == null || str.isEmpty())) {
            return false;
        }

        if (minLength != -1 && (str == null || str.length() < minLength)) {
            return false;
        }

        if (mustContain != null && (str == null || !str.contains(mustContain))) {
            return false;
        }

        return true;
    }
}
