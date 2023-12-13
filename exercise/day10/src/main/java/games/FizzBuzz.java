package games;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class FizzBuzz {
    private static final int MAX = 100;
    private static final int MIN = 0;
    private static final Map<Integer, String> fizzBuzzRules = (Map.of(3, "Fizz", 5, "Buzz"));

    public static String convert(Integer input) throws OutOfRangeException {
        if (isOutOfRange(input)) {
            throw new OutOfRangeException();
        }
        return convertSafely(input);
    }

    private static String convertSafely(Integer input) {
        List<Integer> filteredAndOrderedKeys = filterAndOrderKeys(input);

        if (filteredAndOrderedKeys.isEmpty()) {
            return input.toString();
        }

        return buildStringFromAcceptableKeys(filteredAndOrderedKeys);
    }

    private static List<Integer> filterAndOrderKeys(Integer input) {
        var filteredKeys = new ArrayList<>(fizzBuzzRules.keySet().stream().filter(value -> input % value == 0).toList());
        Collections.sort(filteredKeys);

        return filteredKeys;
    }

    private static String buildStringFromAcceptableKeys(List<Integer> filteredAndOrderedKeys) {
        StringBuilder result = new StringBuilder();
        filteredAndOrderedKeys.forEach(key -> result.append(fizzBuzzRules.get(key)));

        return result.toString();
    }

    private static boolean isOutOfRange(Integer input) {
        return input <= MIN || input > MAX;
    }
}
