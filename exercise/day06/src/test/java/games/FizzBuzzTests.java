package games;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class FizzBuzzTests {
    public static Stream<Arguments> valid() {
        return Stream.of(Arguments.of(1, "1"),
                Arguments.of(67, "67"),
                Arguments.of(82, "82"),
                Arguments.of(3, "Fizz"),
                Arguments.of(66, "Fizz"),
                Arguments.of(99, "Fizz"),
                Arguments.of(5, "Buzz"),
                Arguments.of(50, "Buzz"),
                Arguments.of(85, "Buzz"),
                Arguments.of(15, "FizzBuzz"),
                Arguments.of(30, "FizzBuzz"),
                Arguments.of(45, "FizzBuzz"));
    }

    public static Stream<Arguments> invalid() {
        return Stream.of(
                Arguments.of(0),
                Arguments.of(-1),
                Arguments.of(101)
        );
    }

    @ParameterizedTest
    @MethodSource("valid")
    void shouldConvertInput(int input, String expected) throws OutOfRangeException {
        assertThat(FizzBuzz.convert(input)).isEqualTo(expected);
    }

    @ParameterizedTest
    @MethodSource("invalid")
    void shouldNotConvertInputAndThrowExceptionInstead(int input) {
        assertThrows(OutOfRangeException.class, () -> FizzBuzz.convert(input));
    }
}

