package hexlet.code;

public class App {
    public static void main(String[] args) {

        var v = new Validator();

        var schema = v.string();

// Пока не вызван метод required(), null и пустая строка считаются валидным
        System.out.println(schema.isValid("")); // true
        System.out.println(schema.isValid(null)); // true

        System.out.println();
        schema.required();
        System.out.println(schema.isValid(null)); // false
        System.out.println(schema.isValid("")); // false
        System.out.println(schema.isValid("what does the fox say")); // true
        System.out.println(schema.isValid("hexlet")); // true

        System.out.println();
        System.out.println(schema.contains("wh").isValid("what does the fox say")); // true
        System.out.println(schema.contains("what").isValid("what does the fox say")); // true
        System.out.println(schema.contains("whatthe").isValid("what does the fox say")); // false

        System.out.println(schema.isValid("what does the fox say")); // false
// Здесь уже false, так как добавлена еще одна проверка contains("whatthe")

// Если один валидатор вызывался несколько раз
// то последний имеет приоритет (перетирает предыдущий)
        System.out.println();
        var schema1 = v.string();
        System.out.println(schema1.minLength(10).minLength(4).isValid("Hexlet")); // true

        System.out.println("----------------------------------------------");
        System.out.println();
        var schemaN = v.number();

        System.out.println(schemaN.isValid(5)); // true

// Пока не вызван метод required(), null считается валидным
        System.out.println(schemaN.isValid(null)); // true
        System.out.println(schemaN.positive().isValid(null)); // true

        schemaN.required();

        System.out.println();
        System.out.println(schemaN.isValid(null)); // false
        System.out.println(schemaN.isValid(10)); // true

// Потому что ранее мы вызвали метод positive()
        System.out.println();
        System.out.println(schemaN.isValid(-10)); // false
//  Ноль — не положительное число
        System.out.println(schemaN.isValid(0)); // false

        schemaN.range(5, 10);

        System.out.println();
        System.out.println(schemaN.isValid(5)); // true
        System.out.println(schemaN.isValid(10)); // true
        System.out.println(schemaN.isValid(4)); // false
        System.out.println(schemaN.isValid(11)); // false
    }
}
