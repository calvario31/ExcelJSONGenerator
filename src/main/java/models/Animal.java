package models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.javafaker.Faker;

import java.util.ArrayList;
import java.util.List;

public class Animal {
    @JsonProperty("id")
    private int id;
    @JsonProperty("nombre")
    private final String nombre;
    @JsonProperty("tipo")
    private final String tipo;
    @JsonProperty("amo")
    private final String amo;
    @JsonProperty("edad")
    private final int edad;
    @JsonProperty("peso")
    private final double peso;
    @JsonProperty("genero")
    private final String genero;

    public Animal() {
        final var faker = new Faker();

        nombre = faker.animal().name().toUpperCase();
        tipo = getRandomTipo();
        amo = faker.name().name().toUpperCase();
        edad = faker.number().numberBetween(1, 25);
        peso = faker.number().randomDouble(3, 1, 200);
        genero = getRandomGenero();
    }

    public Animal(int id) {
        this();
        this.id = id;
    }

    private static String[] getHeaders() {
        return new String[]{
                "ID",
                "NOMBRE",
                "TIPO",
                "AMO",
                "EDAD",
                "PESO",
                "GENERO"
        };
    }

    public static Object[][] generateExcelData(int n) {
        final var headers = getHeaders();
        final var array = new Object[n][headers.length];

        array[0] = headers;
        for (var i = 1; i < n; i++) {
            final var animal = new Animal(i);
            array[i] = new Object[]{
                    animal.id,
                    animal.nombre,
                    animal.tipo,
                    animal.amo,
                    animal.edad,
                    animal.peso,
                    animal.genero
            };
        }

        return array;
    }

    public static List<Animal> generateJsonData(int n) {
        final var list = new ArrayList<Animal>();
        for (var i = 0; i < n; i++) {
            list.add(new Animal(i + 1));
        }

        return list;
    }

    private String getRandomGenero() {
        final var list = List.of(
                "MACHO",
                "HEMBRA"
        );
        return list.get(new Faker().number().numberBetween(0, list.size()));
    }

    private String getRandomTipo() {
        final var list = List.of(
                "DOMESTICO",
                "SALVAJE"
        );
        return list.get(new Faker().number().numberBetween(0, list.size()));
    }
}
