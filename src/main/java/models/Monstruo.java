package models;

import java.util.ArrayList;
import java.util.List;

public record Monstruo(
        String nombre,
        double edad,
        double peso,
        boolean macho
) {
    public static String[] getEncabezados() {
        return new String[]{
                "NOMBRE",
                "EDAD",
                "PESO",
                "MACHO"
        };
    }

    public static Object[][] getData(List<Monstruo> listaMonstruo) {
        final var n = listaMonstruo.size();

        final var array = new Object[n][];

        for (var i = 0; i < n; i++) {
            array[i] = getSingleData(listaMonstruo.get(i));
        }

        return array;
    }

    private static Object[] getSingleData(Monstruo monstruo) {
        return new Object[]{
                monstruo.nombre(),
                monstruo.edad(),
                monstruo.peso(),
                monstruo.macho()
        };
    }

    public static List<Monstruo> getMonsterList(Object[][] data) {
        final var list = new ArrayList<Monstruo>();

        for (var array : data) {
            final var nombre = (String) array[0];
            final var edad = (Double) array[1];
            final var peso = (Double) array[2];
            final var macho = true;

            list.add(new Monstruo(nombre, edad, peso, macho));
        }

        return list;
    }
}