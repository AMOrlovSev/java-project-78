package hexlet.code.schemas;

public class StringSchema {
    String str;
    int length;
    String subStr;

    Boolean isCorrect = true;

    Boolean isRequired = false;
    Boolean isMinLength = false;
    Boolean isContains = false;

    public void required() {
        isRequired = true;
    }

    public void minLength(int rLength) {
        this.length = rLength;
        isMinLength = true;
    }

    public void contains(String rSubStr) {
        this.subStr = rSubStr;
        isContains = true;
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
