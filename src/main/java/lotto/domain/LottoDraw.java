package lotto.domain;

import java.util.List;

public record LottoDraw(
    List<Integer> winningNumbers,
    int bonusNumber
) {
    public LottoDraw(List<Integer> winningNumbers, int bonusNumber) {
        validateDuplication(winningNumbers, bonusNumber);
        this.winningNumbers = winningNumbers.stream()
            .sorted()
            .toList();
        this.bonusNumber = bonusNumber;
    }

    private void validateDuplication(List<Integer> winningNumbers, int bonusNumber) {
        if (winningNumbers.contains(bonusNumber)) {
            throw new IllegalArgumentException("The bonus number " + bonusNumber + " is already in winning numbers");
        }
    }
}
