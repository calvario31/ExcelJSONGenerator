package principal;

import utilities.DataGenerator;

public class Main {
    public static void main(String[] args) {
        DataGenerator.generateAll(30, 50);
        System.out.println("SE HA FINALIZADO CON EXITO");
        System.out.printf("RUTA EXCEL: %s%n", "src/main/resources/excel");
        System.out.printf("RUTA JSON: %s%n", "src/main/resources/json");
    }
}