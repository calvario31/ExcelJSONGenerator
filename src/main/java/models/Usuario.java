package models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.javafaker.Faker;
import utilities.Generals;

import java.util.ArrayList;
import java.util.List;

public class Usuario {
    @JsonProperty("id")
    private String id;
    @JsonProperty("nombre")
    private final String nombre;
    @JsonProperty("apellido")
    private final String apellido;
    @JsonProperty("edad")
    private final int edad;
    @JsonProperty("peso")
    private final double peso;
    @JsonProperty("email")
    private final String email;
    @JsonProperty("username")
    private final String username;
    @JsonProperty("password")
    private final String password;
    @JsonProperty("pais")
    private final String pais;
    @JsonProperty("universidad")
    private final String universidad;

    public Usuario() {
        final var faker = new Faker();

        nombre = faker.name().firstName().toUpperCase();
        apellido = faker.name().lastName().toUpperCase();
        edad = faker.number().numberBetween(20, 60);
        peso = faker.number().randomDouble(3, 60, 120);
        email = Generals.getRandomEmail(nombre, apellido);
        username = faker.name().username().toUpperCase();
        password = faker.internet().password().toUpperCase();
        pais = faker.country().name().toUpperCase();
        universidad = faker.university().name().toUpperCase();
    }

    public Usuario(int id) {
        this();
        this.id = String.format("USR-%d", id);
    }

    private static String[] getHeaders() {
        return new String[]{
                "ID",
                "NOMBRE",
                "APELLIDO",
                "EDAD",
                "PESO",
                "EMAIL",
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
            final var usuario = new Usuario(i);
            array[i] = new Object[]{
                    usuario.id,
                    usuario.nombre,
                    usuario.apellido,
                    usuario.edad,
                    usuario.peso,
                    usuario.email,
                    usuario.username,
                    usuario.password,
                    usuario.pais,
                    usuario.universidad
            };
        }

        return array;
    }

    public static List<Usuario> generateJsonData(int n) {
        final var list = new ArrayList<Usuario>();
        for (var i = 0; i < n; i++) {
            list.add(new Usuario(i + 1));
        }

        return list;
    }
}
