package models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.javafaker.Faker;

import java.util.ArrayList;
import java.util.List;

public class Pokemon {
    @JsonProperty("id")
    private String id;
    @JsonProperty("nombre")
    private final String nombre;
    @JsonProperty("nivel")
    private final int nivel;
    @JsonProperty("hp")
    private final int puntosVida;
    @JsonProperty("ataque")
    private final double ataque;
    @JsonProperty("defensa")
    private final double defensa;
    @JsonProperty("ataqueEspecial")
    private final double ataqueEspecial;
    @JsonProperty("defensaEspecial")
    private final double defensaEspecial;
    @JsonProperty("velocidad")
    private final double velocidad;
    @JsonProperty("esMacho")
    private final boolean macho;
    @JsonProperty("entrenador")
    private final String entrenador;
    @JsonProperty("ubicacion")
    private final String ubicacion;
    @JsonProperty("tipo")
    private final String tipo;

    public Pokemon() {
        final var faker = new Faker();
        nombre = faker.pokemon().name().toUpperCase();
        nivel = faker.number().numberBetween(1, 100);
        puntosVida = faker.number().numberBetween(10, 350);
        ataque = faker.number().randomDouble(2, 20, 200);
        defensa = faker.number().randomDouble(2, 20, 200);
        ataqueEspecial = faker.number().randomDouble(2, 20, 200);
        defensaEspecial = faker.number().randomDouble(2, 20, 200);
        velocidad = faker.number().randomDouble(2, 20, 200);
        macho = faker.bool().bool();
        entrenador = faker.name().fullName().toUpperCase();
        ubicacion = faker.pokemon().location().toUpperCase();
        tipo = getRandomTipo();
    }

    public Pokemon(int id) {
        this();
        this.id = String.format("PKM-%d", id);
    }

    private static String[] getHeaders() {
        return new String[]{
                "ID",
                "NOMBRE",
                "NIVEL",
                "HP",
                "ATAQUE",
                "DEFENSA",
                "ATAQUE ESPECIAL",
                "DEFENSA ESPECIAL",
                "VELOCIDAD",
                "ES MACHO",
                "ENTRENADOR",
                "UBICACION",
                "TIPO"
        };
    }

    public static Object[][] generateExcelData(int n) {
        final var headers = getHeaders();
        final var array = new Object[n][headers.length];

        array[0] = headers;
        for (var i = 1; i < n; i++) {
            final var pokemon = new Pokemon(i);
            array[i] = new Object[]{
                    pokemon.id,
                    pokemon.nombre,
                    pokemon.nivel,
                    pokemon.puntosVida,
                    pokemon.ataque,
                    pokemon.defensa,
                    pokemon.ataqueEspecial,
                    pokemon.defensaEspecial,
                    pokemon.velocidad,
                    pokemon.macho,
                    pokemon.entrenador,
                    pokemon.ubicacion,
                    pokemon.tipo
            };
        }

        return array;
    }

    public static List<Pokemon> generateJsonData(int n) {
        final var list = new ArrayList<Pokemon>();
        for (var i = 0; i < n; i++) {
            list.add(new Pokemon(i + 1));
        }

        return list;
    }

    private String getRandomTipo() {
        final var list = List.of(
                "AGUA",
                "FUEGO",
                "ELECTRICIDAD",
                "AIRE",
                "PELEA",
                "NORMAL",
                "PSIQUICO",
                "FANTASMA",
                "PLANTA",
                "VOLADOR",
                "HADA",
                "INSECTO"
        );
        return list.get(new Faker().number().numberBetween(0, list.size()));
    }
}
