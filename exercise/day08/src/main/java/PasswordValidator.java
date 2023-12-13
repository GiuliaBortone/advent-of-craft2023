import java.util.Arrays;
import java.util.List;

public class PasswordValidator {
    private final List<String> acceptableSpecialCharacters = List.of(".", "*", "#", "@", "$", "%", "&");
    private final int minimumLength = 8;

    public boolean isValid(String passwordToValidate) {
        return isAtLeastEightCharactersLong(passwordToValidate)
                && checkValidCharactersType(passwordToValidate)
                && doesNotHaveOtherSpecialCharacters(passwordToValidate);
    }

    private boolean checkValidCharactersType(String passwordToValidate) {
        return hasAtLeastOneUpperCaseLetter(passwordToValidate)
                && hasAtLeastOneLowerCaseLetter(passwordToValidate)
                && hasAtLeastOneNumber(passwordToValidate)
                && hasAtLeastOneSpecialCharacter(passwordToValidate);
    }

    private boolean hasAtLeastOneNumber(String passwordToValidate) {
        return passwordToValidate.matches(".*\\d.*");
    }

    private boolean hasAtLeastOneLowerCaseLetter(String passwordToValidate) {
        return !passwordToValidate.toUpperCase().equals(passwordToValidate);
    }

    private boolean hasAtLeastOneUpperCaseLetter(String passwordToValidate) {
        return !passwordToValidate.toLowerCase().equals(passwordToValidate);
    }

    private boolean isAtLeastEightCharactersLong(String passwordToValidate) {
        return passwordToValidate.length() >= minimumLength;
    }

    private boolean hasAtLeastOneSpecialCharacter(String passwordToValidate) {
        return Arrays.stream(passwordToValidate.split("")).anyMatch(acceptableSpecialCharacters::contains);
    }

    private boolean doesNotHaveOtherSpecialCharacters(String passwordToValidate) {
        return Arrays.stream(passwordToValidate.split("")).allMatch(character -> hasAtLeastOneUpperCaseLetter(character)
                || hasAtLeastOneLowerCaseLetter(character)
                || hasAtLeastOneNumber(character)
                || hasAtLeastOneSpecialCharacter(character));
    }
}
