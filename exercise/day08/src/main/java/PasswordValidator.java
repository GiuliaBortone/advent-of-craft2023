import java.util.List;

public class PasswordValidator {
    private final List<String> acceptableSpecialCharacters = List.of(".", "*", "#", "@", "$", "%", "&");

    public boolean isValid(String passwordToValidate) {
        return isAtLeastEightCharacters(passwordToValidate)
                && hasAtLeastOneUpperCaseLetter(passwordToValidate)
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

    private boolean isAtLeastEightCharacters(String passwordToValidate) {
        return passwordToValidate.length() >= 8;
    }

    private boolean hasAtLeastOneSpecialCharacter(String passwordToValidate) {
        for (String acceptableSpecialCharacter : acceptableSpecialCharacters) {
            if (passwordToValidate.contains(acceptableSpecialCharacter)) {
                return true;
            }
        }
        return false;
    }
}
