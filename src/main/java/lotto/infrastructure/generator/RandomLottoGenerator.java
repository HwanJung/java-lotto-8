package lotto.infrastructure.generator;

import camp.nextstep.edu.missionutils.Randoms;
import lotto.domain.Lotto;
import lotto.domain.generator.LottoGenerator;

public class RandomLottoGenerator implements LottoGenerator {
    @Override
    public Lotto generate() {
        return new Lotto(Randoms.pickUniqueNumbersInRange(1, 45, 6));
    }
}
