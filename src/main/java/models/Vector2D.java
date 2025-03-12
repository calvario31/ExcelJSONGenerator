package models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.javafaker.Faker;

import java.util.ArrayList;
import java.util.List;

public class Vector2D {
    @JsonProperty("username")
    private final String username;
    @JsonProperty("x")
    private final double x;
    @JsonProperty("y")
    private final double y;

    public Vector2D() {
        final var faker = new Faker();

        username = faker.name().username();
        x = faker.number().randomDouble(2, 5, 100);
        y = faker.number().randomDouble(2, 5, 100);
    }

    private static String[] getHeaders() {
        return new String[]{
                "USERNAME",
                "X",
                "Y"
        };
    }

    public static Object[][] generateExcelData(int n) {
        final var headers = getHeaders();
        final var array = new Object[n][headers.length];

        array[0] = headers;
        for (var i = 1; i < n; i++) {
            final var vector2D = new Vector2D();
            array[i] = new Object[]{
                    vector2D.username,
                    vector2D.x,
                    vector2D.y
            };
        }

        return array;
    }

    public static List<Vector2D> generateJsonData(int n) {
        final var list = new ArrayList<Vector2D>();
        for (var i = 0; i < n; i++) {
            list.add(new Vector2D());
        }

        return list;
    }
}
