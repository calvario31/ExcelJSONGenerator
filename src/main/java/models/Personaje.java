package models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.javafaker.Faker;

import java.util.ArrayList;
import java.util.List;

public class Personaje {
    @JsonProperty("id")
    private String id;
    @JsonProperty("nombre")
    private final String nombre;
    @JsonProperty("apodo")
    private final String apodo;
    @JsonProperty("edad")
    private final int edad;
    @JsonProperty("nivel")
    private final int nivel;
    @JsonProperty("peso")
    private final double peso;
    @JsonProperty("ubicacion")
    private final String ubicacion;
    @JsonProperty("juego")
    private final String juego;
    @JsonProperty("espirituAnimal")
    private final String espirituAnimal;

    public Personaje() {
        final var faker = new Faker();

        nombre = faker.zelda().character().toUpperCase();
        apodo = faker.aquaTeenHungerForce().character().toUpperCase();
        edad = faker.number().numberBetween(20, 70);
        nivel = faker.number().numberBetween(1, 100);
        peso = faker.number().randomDouble(2, 70, 100);
        ubicacion = faker.lordOfTheRings().location().toUpperCase();
        juego = faker.zelda().game().toUpperCase();
        espirituAnimal = getRandomEspirituAnimal().toUpperCase();
    }

    public Personaje(int id) {
        this();
        this.id = String.format("CRT-%d", id);
    }

    private static String[] getHeaders() {
        return new String[]{
                "ID",
                "NOMBRE",
                "APODO",
                "EDAD",
                "NIVEL",
                "PESO",
                "UBICACION",
                "JUEGO",
                "ESPIRITU ANIMAL"
        };
    }

    public static Object[][] generateExcelData(int n) {
        final var headers = getHeaders();
        final var array = new Object[n][headers.length];

        array[0] = headers;
        for (var i = 1; i < n; i++) {
            final var personaje = new Personaje(i);
            array[i] = new Object[]{
                    personaje.id,
                    personaje.nombre,
                    personaje.apodo,
                    personaje.edad,
                    personaje.nivel,
                    personaje.peso,
                    personaje.ubicacion,
                    personaje.juego,
                    personaje.espirituAnimal
            };
        }

        return array;
    }

    public static List<Personaje> generateJsonData(int n) {
        final var list = new ArrayList<Personaje>();
        for (var i = 0; i < n; i++) {
            list.add(new Personaje(i));
        }

        return list;
    }

    private String getRandomEspirituAnimal() {
        final var list = List.of(
                "RATA",
                "BUEY",
                "TIGRE",
                "CONEJO",
                "DRAGON",
                "SERPIENTE",
                "CABALLO",
                "CABRA",
                "GALLO",
                "PERRO",
                "CERDO"
        );
        return list.get(new Faker().number().numberBetween(0, list.size()));
    }
}
