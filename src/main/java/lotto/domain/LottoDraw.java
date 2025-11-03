package lotto.domain;

import java.util.Collections;
import java.util.List;

public record LottoDraw(
    List<Integer> winningNumbers,
    int bonusNumber
) {
    public LottoDraw {
        validateDuplication(winningNumbers, bonusNumber);
        Collections.sort(winningNumbers);
    }

    private void validateDuplication(List<Integer> winningNumbers, int bonusNumber) {
        if (winningNumbers.contains(bonusNumber)) {
            throw new IllegalArgumentException("The bonus number " + bonusNumber + " is already in winning numbers");
        }
    }
}
