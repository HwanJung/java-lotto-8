package lotto.domain;

import java.util.List;

public record LottoDraw(
    List<Integer> winningNumbers,
    int bonusNumber
) {
    public LottoDraw {
        validateWinningNumbers(winningNumbers);
        validateBonusNumber(bonusNumber);
    }

    private void validateWinningNumbers(List<Integer> winningNumbers) {
        if (winningNumbers.size() != 6) {
            throw new IllegalArgumentException("[ERROR] Winning numbers must be six numbers.");
        }

        for (int number : winningNumbers) {
            if (number < 1 || number > 45) {
                throw new IllegalArgumentException("[ERROR] Winning numbers must be between 1 and 45.");
            }
        }
    }

    private void validateBonusNumber(int bonusNumber) {
        if (bonusNumber < 1 || bonusNumber > 45) {
            throw new IllegalArgumentException("[ERROR] Bonus number must be between 1 and 45.");
        }
    }
}
