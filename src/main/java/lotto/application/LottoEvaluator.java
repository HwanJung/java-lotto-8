package lotto.application;

import lotto.domain.Lotto;
import lotto.domain.LottoDraw;
import lotto.domain.LottoRank;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LottoEvaluator {
    public Map<LottoRank, Integer> evaluate(List<Lotto> lottos, LottoDraw draw) {
        Map<LottoRank, Integer> rankCount = new HashMap<>();
        rankCount.put(LottoRank.FIRST, 0);
        rankCount.put(LottoRank.SECOND, 0);
        rankCount.put(LottoRank.THIRD, 0);
        rankCount.put(LottoRank.FOURTH, 0);
        rankCount.put(LottoRank.FIFTH, 0);
        rankCount.put(LottoRank.NONE, 0);

        countRanks(lottos, draw, rankCount);

        return rankCount;
    }

    public double calculateProfitRate(Map<LottoRank, Integer> rankCount, int purchaseAmount) {
        long profit = 0;
        for (Map.Entry<LottoRank, Integer> entry : rankCount.entrySet()) {
            profit += entry.getKey().getPrize() * entry.getValue();
        }

        return (profit / (double) purchaseAmount) * 100;
    }

    private void countRanks(List<Lotto> lottos, LottoDraw draw, Map<LottoRank, Integer> rankCount) {
        for (Lotto lotto : lottos) {
            int matchedCount = lotto.countMatchedNumbers(draw.winningNumbers());
            boolean hasBonusNumber = lotto.hasBonusNumber(draw.bonusNumber());
            LottoRank rank = LottoRank.valueOf(matchedCount, hasBonusNumber);

            rankCount.put(rank, rankCount.get(rank) + 1);
        }
    }
}
