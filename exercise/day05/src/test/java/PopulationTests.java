import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import people.Person;
import people.Pet;
import people.PetType;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static java.lang.Integer.MAX_VALUE;
import static java.lang.String.format;
import static java.lang.System.lineSeparator;
import static org.assertj.core.api.Assertions.assertThat;

class PopulationTests {
    private static List<Person> population;

    @BeforeAll
    static void setup() {
        population = Arrays.asList(
                new Person("Peter", "Griffin")
                        .addPet(PetType.CAT, "Tabby", 2),
                new Person("Stewie", "Griffin")
                        .addPet(PetType.CAT, "Dolly", 3)
                        .addPet(PetType.DOG, "Brian", 9),
                new Person("Joe", "Swanson")
                        .addPet(PetType.DOG, "Spike", 4),
                new Person("Lois", "Griffin")
                        .addPet(PetType.SNAKE, "Serpy", 1),
                new Person("Meg", "Griffin")
                        .addPet(PetType.BIRD, "Tweety", 1),
                new Person("Chris", "Griffin")
                        .addPet(PetType.TURTLE, "Speedy", 4),
                new Person("Cleveland", "Brown")
                        .addPet(PetType.HAMSTER, "Fuzzy", 1)
                        .addPet(PetType.HAMSTER, "Wuzzy", 2),
                new Person("Glenn", "Quagmire")
        );
    }

    @Test
    void peopleWithTheirPets() {
        final var response = populationFormatter();

        assertThat(response)
                .hasToString("Peter Griffin who owns : Tabby " + lineSeparator() +
                        "Stewie Griffin who owns : Dolly Brian " + lineSeparator() +
                        "Joe Swanson who owns : Spike " + lineSeparator() +
                        "Lois Griffin who owns : Serpy " + lineSeparator() +
                        "Meg Griffin who owns : Tweety " + lineSeparator() +
                        "Chris Griffin who owns : Speedy " + lineSeparator() +
                        "Cleveland Brown who owns : Fuzzy Wuzzy " + lineSeparator() +
                        "Glenn Quagmire");
    }

    private static String populationFormatter() {
        return population.stream()
                .map(PopulationTests::personFormatter)
                .collect(Collectors.joining(lineSeparator()));
    }

    private static String personFormatter(Person person) {
        String formattedPerson = format("%s %s", person.firstName(), person.lastName());

        if (person.pets().isEmpty())
            return formattedPerson;

        return formattedPerson + " who owns : " + petsFormatter(person);
    }

    private static String petsFormatter(Person person) {
        return person.pets().stream()
                .map(Pet::name)
                .map(text -> text + " ")
                .collect(Collectors.joining());
    }

    @Test
    void whoOwnsTheYoungestPet() {
        var personWithYoungestPet = findPersonWithYoungestPet();

        assert personWithYoungestPet != null;
        assertThat(personWithYoungestPet.firstName()).isEqualTo("Lois");
    }

    private Person findPersonWithYoungestPet() {
        return population
                .stream()
                .min(Comparator.comparingInt(this::findYoungestPetFor))
                .orElse(null);
    }

    private int findYoungestPetFor(Person person) {
        return person.pets()
                .stream()
                .mapToInt(Pet::age)
                .min()
                .orElse(MAX_VALUE);
    }
}
