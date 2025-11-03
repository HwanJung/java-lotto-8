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
        int purchaseCnt = purchaseAmount / 1000;

        List<Lotto> lottos = new ArrayList<>();
        for (int i = 0; i < purchaseCnt; i++) {
            lottos.add(lottoGenerator.generate());
        }

        return lottos;
    }




}
