package lotto.application;

import lotto.domain.Lotto;
import lotto.domain.generator.LottoGenerator;

import java.util.ArrayList;
import java.util.List;

public class LottoIssuer {
    private final LottoGenerator lottoGenerator;

    public LottoIssuer(LottoGenerator lottoGenerator) {
        this.lottoGenerator = lottoGenerator;
    }

    public List<Lotto> buyLotto(int purchaseAmount) {
        validatePurchaseAmount(purchaseAmount);
        int purchaseCnt = purchaseAmount / 1000;

        List<Lotto> lottos = new ArrayList<>();
        for (int i = 0; i < purchaseCnt; i++) {
            lottos.add(lottoGenerator.generate());
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
