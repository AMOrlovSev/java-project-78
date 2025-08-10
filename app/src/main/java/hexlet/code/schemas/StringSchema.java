package hexlet.code.schemas;

public class StringSchema {
    String str;
    int length;
    String subStr;

    Boolean isCorrect = true;

    Boolean isRequired = false;
    Boolean isMinLength = false;
    Boolean isContains = false;

    public StringSchema required() {
        isRequired = true;
        return this;
    }

    public StringSchema minLength(int rLength) {
        this.length = rLength;
        isMinLength = true;
        return this;
    }

    public StringSchema contains(String rSubStr) {
        this.subStr = rSubStr;
        isContains = true;
        return this;
    }

    public boolean isValid(String rStr) {
        this.str = rStr;

        if (isRequired) {
            isCorrect = str != null && !str.isEmpty();
        }

        if (isMinLength) {
            isCorrect = (str != null ? str.length() : 0) >= length;
        }

        if (isContains) {
            isCorrect = str != null && str.contains(subStr);
        }

        return isCorrect;
    }
}
