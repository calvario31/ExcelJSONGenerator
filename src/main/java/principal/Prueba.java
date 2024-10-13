package principal;

import models.Monstruo;
import utilities.ExcelReader;

import java.util.List;

public class Prueba {
    public static void main(String[] args) {
        final var dataInput = ExcelReader.readData("monstruos");
        final var listaMonstruo = Monstruo.getMonsterList(dataInput);

        final var listaFiltrada = filtrarData(listaMonstruo);
        System.out.println(listaFiltrada);
    }

    public static List<Monstruo> filtrarData(List<Monstruo> listaInicial) {
        return listaInicial
                .stream()
                .filter(Monstruo::macho)
                .toList();
    }
}