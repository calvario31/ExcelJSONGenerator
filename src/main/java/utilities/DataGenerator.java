package utilities;

import models.Alumno;
import models.Animal;
import models.Personaje;
import models.Pokemon;
import models.Usuario;
import org.apache.commons.io.FileUtils;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.IOException;

public class DataGenerator {
    private static final String excelPath = "src/main/resources/excel";
    private static final String jsonPath = "src/main/resources/json";

    public static void generateAll(int n, int m) {
        generateExcel(n);
        generateJsons(m);
    }

    public static void generateExcel(int n) {
        Logs.info("Generando el Excel");
        System.out.println("GENERANDO EL EXCEL");
        try {
            final var file = new File(excelPath);
            FileUtils.deleteDirectory(file);
            if (file.mkdirs()) {
                Logs.info("Se creo el directorio de excel");
            } else {
                Logs.error("Fallo al crear directorio de excel");
            }
        } catch (IOException ioException) {
            ioException.fillInStackTrace();
        }

        final var workbook = new XSSFWorkbook();

        ExcelWriter.initHeaderStyle(workbook);

        Logs.info("Escribiendo pestaña de usuarios");
        ExcelWriter.writeExcelSheet(workbook, Usuario.generateExcelData(n), "usuarios");

        Logs.info("Escribiendo pestaña de pokemons");
        ExcelWriter.writeExcelSheet(workbook, Pokemon.generateExcelData(n), "pokemons");

        Logs.info("Escribiendo pestaña de personajes");
        ExcelWriter.writeExcelSheet(workbook, Personaje.generateExcelData(n), "personajes");

        Logs.info("Escribiendo pestaña de animales");
        ExcelWriter.writeExcelSheet(workbook, Animal.generateExcelData(n), "animales");

        Logs.info("Escribiendo pestaña de alumnos");
        ExcelWriter.writeExcelSheet(workbook, Alumno.generateExcelData(n), "alumnos");

        ExcelWriter.writeData(workbook, generateExcelPath());
    }

    public static void generateJsons(int n) {
        Logs.info("Generando los JSON");
        System.out.println("GENERANDO LOS JSONS");
        try {
            final var file = new File("src/main/resources/json");
            FileUtils.deleteDirectory(file);
            if (file.mkdirs()) {
                Logs.info("Se creo el directorio de json");
            } else {
                Logs.error("Fallo al crear directorio de json");
            }
        } catch (IOException ioException) {
            ioException.fillInStackTrace();
        }

        final var listaUsuario = Usuario.generateJsonData(n);
        final var listPokemon = Pokemon.generateJsonData(n);
        final var listaAnimal = Animal.generateJsonData(n);
        final var listaPersonaje = Personaje.generateJsonData(n);
        final var listaAlumno = Alumno.generateJsonData(n);

        Logs.info("Escribiendo JSON de usuarios");
        JsonWriter.writeJson(listaUsuario, generateJsonPath("usuarios"));

        Logs.info("Escribiendo JSON de pokemones");
        JsonWriter.writeJson(listPokemon, generateJsonPath("pokemones"));

        Logs.info("Escribiendo JSON de animales");
        JsonWriter.writeJson(listaAnimal, generateJsonPath("animales"));

        Logs.info("Escribiendo JSON de personajes");
        JsonWriter.writeJson(listaPersonaje, generateJsonPath("personajes"));

        Logs.info("Escribiendo JSON de alumnos");
        JsonWriter.writeJson(listaAlumno, generateJsonPath("alumnos"));
    }

    private static String generateJsonPath(String name) {
        return String.format("%s/%s.json", jsonPath, name);
    }

    private static String generateExcelPath() {
        return String.format("%s/%s.xlsx", excelPath, "data");
    }
}