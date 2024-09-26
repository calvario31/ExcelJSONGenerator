package models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.javafaker.Faker;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Personaje {
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
        final var faker = new Faker(new Locale("es-MX"));

        nombre = faker.zelda().character();
        apodo = faker.aquaTeenHungerForce().character();
        edad = faker.number().numberBetween(20, 70);
        nivel = faker.number().numberBetween(1, 100);
        peso = faker.number().randomDouble(2, 70, 100);
        ubicacion = faker.lordOfTheRings().location();
        juego = faker.zelda().game();
        espirituAnimal = getRandomEspirituAnimal();
    }

    private static String[] getHeaders() {
        return new String[]{
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
            final var personaje = new Personaje();
            array[i] = new Object[]{
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
            list.add(new Personaje());
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
