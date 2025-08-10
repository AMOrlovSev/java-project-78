package hexlet.code;

import java.util.HashMap;

public class App {
    public static void main(String[] args) {

        var v = new Validator();

        var schema = v.map();

        System.out.println(schema.isValid(null)); // true

        schema.required();

        System.out.println(schema.isValid(null)); // false
        System.out.println(schema.isValid(new HashMap<>())); // true
        var data = new HashMap<String, String>();
        data.put("key1", "value1");
        System.out.println(schema.isValid(data)); // true

        schema.sizeof(2);

        System.out.println(schema.isValid(data));  // false
        data.put("key2", "value2");
        System.out.println(schema.isValid(data)); // true
        System.out.println(new HashMap<>().size());

    }
}
