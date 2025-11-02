package lotto.domain;

import camp.nextstep.edu.missionutils.Randoms;
import lotto.strategy.LottoGenerator;

public class RandomLottoGenerator implements LottoGenerator {
    @Override
    public Lotto generate() {
        return new Lotto(Randoms.pickUniqueNumbersInRange(1, 45, 6));
    }
}
