package lotto.ui.input;

import java.util.ArrayList;
import java.util.List;

public class Parser {
    public int parsePurchaseAmount(String input) {
        return parseToInt(input);
    }

    public List<Integer> parseWinningNumbers(String input) {
        List<String> numbers = new ArrayList<>(List.of(input.split(",", -1)));
        return makeIntegerNumbers(numbers);
    }

    public int parseBonusNumber(String input) {
        return parseToInt(input);
    }

    private List<Integer> makeIntegerNumbers(List<String> numbers) {
        return numbers.stream()
            .map(this::parseToInt)
            .toList();
    }

    private int parseToInt(String input) {
        input = input.trim();
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] Number input is not an integer. input: " + input);
        }
    }

}
