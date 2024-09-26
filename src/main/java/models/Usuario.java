package models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.javafaker.Faker;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Usuario {
    @JsonProperty("nombre")
    private final String nombre;
    @JsonProperty("apellido")
    private final String apellido;
    @JsonProperty("edad")
    private final int edad;
    @JsonProperty("peso")
    private final double peso;
    @JsonProperty("email")
    private final String correo;
    @JsonProperty("username")
    private final String username;
    @JsonProperty("password")
    private final String password;
    @JsonProperty("pais")
    private final String pais;
    @JsonProperty("universidad")
    private final String universidad;

    public Usuario() {
        final var faker = new Faker(new Locale("es-MX"));

        nombre = faker.name().firstName();
        apellido = faker.name().lastName();
        edad = faker.number().numberBetween(20, 60);
        peso = faker.number().randomDouble(3, 60, 120);
        correo = faker.internet().emailAddress();
        username = faker.name().username();
        password = faker.internet().password();
        pais = faker.country().name();
        universidad = faker.university().name();
    }

    private static String[] getHeaders() {
        return new String[]{
                "NOMBRE",
                "APELLIDO",
                "EDAD",
                "PESO",
                "CORREO",
                "USERNAME",
                "PASSWORD",
                "PAIS",
                "UNIVERSIDAD"
        };
    }

    public static Object[][] generateExcelData(int n) {
        final var headers = getHeaders();
        final var array = new Object[n][headers.length];

        array[0] = headers;
        for (var i = 1; i < n; i++) {
            final var persona = new Usuario();
            array[i] = new Object[]{
                    persona.nombre,
                    persona.apellido,
                    persona.edad,
                    persona.peso,
                    persona.correo,
                    persona.username,
                    persona.password,
                    persona.pais,
                    persona.universidad
            };
        }

        return array;
    }

    public static List<Usuario> generateJsonData(int n) {
        final var list = new ArrayList<Usuario>();
        for (var i = 0; i < n; i++) {
            list.add(new Usuario());
        }

        return list;
    }
}
