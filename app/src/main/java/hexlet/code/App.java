package hexlet.code;

public class App {
    public static void main(String[] args) {

        var v = new Validator();

        var schema = v.string();

// Пока не вызван метод required(), null и пустая строка считаются валидным
        System.out.println(schema.isValid("")); // true
        System.out.println(schema.isValid(null)); // true

        schema.required();

        System.out.println(schema.isValid(null)); // false
        System.out.println(schema.isValid("")); // false
        System.out.println(schema.isValid("a")); // true
        schema.isValid("what does the fox say"); // true
        schema.isValid("hexlet"); // true

        System.out.println();
        var schema1 = v.string();
        schema1.minLength(10);
        System.out.println(schema1.isValid("Hexlet"));
        schema1.minLength(4);
        System.out.println(schema1.isValid("Hexlet"));

        System.out.println();
        schema.contains("wh");
        System.out.println(schema.isValid("what does the fox say")); // true
        schema.contains("whatthe");
        System.out.println(schema.isValid("what does the fox say"));
    }
}
