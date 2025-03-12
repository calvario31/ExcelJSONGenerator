package models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.javafaker.Faker;
import utilities.Generals;

import java.util.ArrayList;
import java.util.List;

public class Alumno {
    @JsonProperty("id")
    private String id;
    @JsonProperty("nombre")
    private final String nombre;
    @JsonProperty("apellido")
    private final String apellido;
    @JsonProperty("edad")
    private final int edad;
    @JsonProperty("email")
    private final String email;
    @JsonProperty("pais")
    private final String pais;
    @JsonProperty("curso")
    private final String curso;
    @JsonProperty("lenguajeProgramacion")
    private final String lenguajeProgramacion;
    @JsonProperty("nota")
    private final int nota;

    public Alumno() {
        final var faker = new Faker();

        nombre = faker.name().firstName().toUpperCase();
        apellido = faker.name().lastName().toUpperCase();
        edad = faker.number().numberBetween(20, 60);
        email = Generals.getRandomEmail(nombre, apellido);
        pais = getRandomPais();
        curso = getRandomCurso();
        lenguajeProgramacion = getRandomLenguajeProgramacion();
        nota = faker.number().numberBetween(1, 20);
    }

    public Alumno(int id) {
        this();
        this.id = String.format("EST-%d", id);
    }

    private static String[] getHeaders() {
        return new String[]{
                "ID",
                "NOMBRE",
                "APELLIDO",
                "EDAD",
                "EMAIL",
                "PAIS",
                "CURSO",
                "LENGUAJE DE PROGRAMACION",
                "NOTA"
        };
    }

    public static Object[][] generateExcelData(int n) {
        final var headers = getHeaders();
        final var array = new Object[n][headers.length];

        array[0] = headers;
        for (var i = 1; i < n; i++) {
            final var alumno = new Alumno(i);
            array[i] = new Object[]{
                    alumno.id,
                    alumno.nombre,
                    alumno.apellido,
                    alumno.edad,
                    alumno.email,
                    alumno.pais,
                    alumno.curso,
                    alumno.lenguajeProgramacion,
                    alumno.nota
            };
        }

        return array;
    }

    public static List<Alumno> generateJsonData(int n) {
        final var list = new ArrayList<Alumno>();
        for (var i = 0; i < n; i++) {
            list.add(new Alumno(i + 1));
        }

        return list;
    }

    private String getRandomCurso() {
        final var list = List.of(
                "SELENIUM",
                "APPIUM",
                "REST ASSURED",
                "KARATE DSL"
        );
        return list.get(new Faker().number().numberBetween(0, list.size()));
    }

    private String getRandomLenguajeProgramacion() {
        final var list = List.of(
                "JAVA",
                "PYTHON",
                "JAVASCRIPT",
                "TYPESCRIPT",
                "SWIFT",
                "KOTLIN",
                "C#"
        );
        return list.get(new Faker().number().numberBetween(0, list.size()));
    }

    private String getRandomPais() {
        final var list = List.of(
                "PERU",
                "COLOMBIA",
                "ECUADOR",
                "VENEZUELA",
                "ARGENTINA",
                "URUGUAY"
        );
        return list.get(new Faker().number().numberBetween(0, list.size()));
    }
}
