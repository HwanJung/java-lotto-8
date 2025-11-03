package lotto.ui.input;

import java.util.ArrayList;
import java.util.List;

public class Parser {
    public int parsePurchaseAmount(String input) {
        int purchaseAmount = parseToInt(input);
        validatePurchaseAmount(purchaseAmount);
        return purchaseAmount;
    }

    public List<Integer> parseWinningNumbers(String input) {
        List<String> numbers = new ArrayList<>(List.of(input.split(",", -1)));
        List<Integer> integerNumbers = makeIntegerNumbers(numbers);
        validateWinningNumbers(integerNumbers);
        return integerNumbers;
    }

    public int parseBonusNumber(String input) {
        int bonusNumber = parseToInt(input);
        validateBonusNumber(bonusNumber);
        return bonusNumber;
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
            throw new IllegalArgumentException("Number input is not an integer. input: " + input);
        }
    }

    private void validateWinningNumbers(List<Integer> winningNumbers) {
        if (winningNumbers.size() != 6) {
            throw new IllegalArgumentException("Winning numbers must be six numbers.");
        }

        for (int number : winningNumbers) {
            if (number < 1 || number > 45) {
                throw new IllegalArgumentException("Winning numbers must be between 1 and 45.");
            }
        }
    }

    private void validateBonusNumber(int bonusNumber) {
        if (bonusNumber < 1 || bonusNumber > 45) {
            throw new IllegalArgumentException("Bonus number must be between 1 and 45.");
        }
    }

    private void validatePurchaseAmount(int purchaseAmount) {
        if (purchaseAmount < 1000) {
            throw new IllegalArgumentException("Purchase amount must be greater than 1000.");
        }

        if (purchaseAmount % 1000 != 0) {
            throw new IllegalArgumentException("Purchase amount must be a multiple of 1000.");
        }
    }

}
