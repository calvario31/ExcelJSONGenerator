package models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.javafaker.Faker;
import utilities.Generals;

import java.util.ArrayList;
import java.util.List;

public class Participante {
    @JsonProperty("nombre")
    private final String nombre;
    @JsonProperty("apellido")
    private final String apellido;
    @JsonProperty("edad")
    private final int edad;
    @JsonProperty("email")
    private final String email;

    public Participante() {
        final var faker = new Faker();

        nombre = faker.name().firstName().toUpperCase();
        apellido = faker.name().lastName().toUpperCase();
        edad = faker.number().numberBetween(20, 60);
        email = Generals.getRandomEmail(nombre, apellido);
    }

    private static String[] getHeaders() {
        return new String[]{
                "NOMBRE",
                "APELLIDO",
                "EDAD",
                "EMAIL"
        };
    }

    public static Object[][] generateExcelData(int n) {
        final var headers = getHeaders();
        final var array = new Object[n][headers.length];

        array[0] = headers;
        for (var i = 1; i < n; i++) {
            final var alumno = new Participante();
            array[i] = new Object[]{
                    alumno.nombre,
                    alumno.apellido,
                    alumno.edad,
                    alumno.email
            };
        }

        return array;
    }

    public static List<Participante> generateJsonData(int n) {
        final var list = new ArrayList<Participante>();
        for (var i = 0; i < n; i++) {
            list.add(new Participante());
        }

        return list;
    }
}
