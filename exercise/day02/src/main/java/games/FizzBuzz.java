package games;

public class FizzBuzz {
    private FizzBuzz() {
    }

    public static String convert(Integer input) throws OutOfRangeException {
        if (input <= 0 || input > 100) {
            throw new OutOfRangeException();
        }

        return convertToString(input);
    }

    private static String convertToString(Integer input) {
        if (isDivisibleBy(input, 3) && isDivisibleBy(input, 5)) {
            return "FizzBuzz";
        }
        if (isDivisibleBy(input, 3)) {
            return "Fizz";
        }
        if (isDivisibleBy(input, 5)) {
            return "Buzz";
        }
        return input.toString();
    }

    private static boolean isDivisibleBy(Integer input, int divisor) {
        return input % divisor == 0;
    }
}

