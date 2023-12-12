import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class PasswordValidatorTests {
    private final PasswordValidator passwordValidator = new PasswordValidator();

    @Test
    void acceptanceTest() {
        assertThat(passwordValidator.isValid("validP@ssw0rd")).isTrue();
    }

    @Test
    void emptyStringShouldReturnFalse() {
        assertThat(passwordValidator.isValid("")).isFalse();
    }

    @Test
    void lengthNotOKShouldReturnFalse() {
        assertThat(passwordValidator.isValid("short")).isFalse();
    }

    @Test
    void lengthAndLowerCaseLetterOKAndCapitalLettersNotOKShouldReturnFalse() {
        assertThat(passwordValidator.isValid("nocaplet")).isFalse();
    }

    @Test
    void lengthAndCapitalLetterOKLowerCaseLetterNotOKShouldReturnFalse() {
        assertThat(passwordValidator.isValid("LONGNOLOWERCASE")).isFalse();
    }

    @Test
    void lengthAndLettersOKButNoNumberShouldReturnFalse() {
        assertThat(passwordValidator.isValid("noNumberPassword")).isFalse();
    }

    @Test
    void lengthAndLettersAndNumberOKSpecialCharacterNotOKShouldReturnTrue() {
        assertThat(passwordValidator.isValid("numb3rPassword")).isFalse();
    }

    @Test
    void lengthAndLettersAndNumberAndSpecialCharactersOKShouldReturnTrue() {
        assertThat(passwordValidator.isValid("numb3rP***word")).isTrue();
    }

    @Test
    @Disabled
    void everythingOKExceptOtherSpecialCharacterPresentShouldReturnFalse() {
        assertThat(passwordValidator.isValid("numb3rPassw*rd?")).isFalse();
    }
}
