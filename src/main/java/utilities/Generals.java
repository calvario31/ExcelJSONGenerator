package utilities;

import com.github.javafaker.Faker;

import java.util.List;

public class Generals {
    public static String getRandomEmail(String nombre, String apellido) {
        final var list = List.of(
                "GMAIL",
                "OUTLOOK",
                "HOTMAIL",
                "APPLE"
        );
        final var faker = new Faker();

        final var randomDomain = list.get(faker.number().numberBetween(0, list.size()));
        final var randomNumber = faker.number().numberBetween(1, 50);

        return String.format("%s.%s.%d@%s.COM", nombre, apellido, randomNumber, randomDomain);
    }
}