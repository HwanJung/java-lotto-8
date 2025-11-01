package lotto.domain;

import java.util.List;

public class LottoGame {
    private final List<Integer> winningNumbers;
    private final int bonusNumber;

    public LottoGame(List<Integer> winningNumbers, int bonusNumber) {
        validateWinningNumbers(winningNumbers);
        validateBonusNumber(bonusNumber);
        this.winningNumbers = winningNumbers;
        this.bonusNumber = bonusNumber;
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
