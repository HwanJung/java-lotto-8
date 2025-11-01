package lotto.domain;

import camp.nextstep.edu.missionutils.Randoms;

import java.util.ArrayList;
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

    public void play(int purchaseAmount) {
        List<Lotto> lottos = buyLotto(purchaseAmount);

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

    private List<Lotto> buyLotto(int purchaseAmount) {
        validatePurchaseAmount(purchaseAmount);
        int purchaseCnt = purchaseAmount / 1000;

        List<Lotto> lottos = new ArrayList<>();
        for (int i = 0; i < purchaseCnt; i++) {
            lottos.add(new Lotto(Randoms.pickUniqueNumbersInRange(1, 45, 6)));
        }

        return lottos;
    }

    private void validatePurchaseAmount(int purchaseAmount) {
        if (purchaseAmount < 1000) {
            throw new IllegalArgumentException("[ERROR] Purchase amount must be greater than 1000.");
        }

        if (purchaseAmount % 1000 != 0) {
            throw new IllegalArgumentException("[ERROR] Purchase amount must be a multiple of 1000.");
        }
    }
}
