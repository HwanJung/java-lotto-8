package lotto.application;

import lotto.domain.Lotto;
import lotto.domain.LottoDraw;
import lotto.domain.LottoRank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LottoEvaluatorTest {

    private final LottoEvaluator lottoEvaluator = new LottoEvaluator();

    @Test
    @DisplayName("여러 로또를 평가했을 때 각 등수별 개수 카운트")
    void evaluate_whenGivenLottos_thenCountEachRank() {
        // given
        LottoDraw draw = new LottoDraw(
            List.of(1, 2, 3, 4, 5, 6),
            7
        );

        Lotto firstRankLotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));     // 6개 일치 → FIRST
        Lotto secondRankLotto = new Lotto(List.of(1, 2, 3, 4, 5, 7));    // 5개 + 보너스 → SECOND
        Lotto thirdRankLotto = new Lotto(List.of(1, 2, 3, 4, 5, 8));     // 5개 → THIRD
        Lotto fourthRankLotto = new Lotto(List.of(1, 2, 3, 4, 8, 9));    // 4개 → FOURTH
        Lotto fifthRankLotto = new Lotto(List.of(1, 2, 3, 8, 9, 10));    // 3개 → FIFTH
        Lotto noneRankLotto = new Lotto(List.of(1, 2, 8, 9, 10, 11));    // 2개 → NONE

        List<Lotto> lottos = List.of(
            firstRankLotto,
            secondRankLotto,
            thirdRankLotto,
            fourthRankLotto,
            fifthRankLotto,
            noneRankLotto
        );

        // when
        Map<LottoRank, Integer> rankCount = lottoEvaluator.evaluate(lottos, draw);

        // then
        assertEquals(1, rankCount.get(LottoRank.FIRST));
        assertEquals(1, rankCount.get(LottoRank.SECOND));
        assertEquals(1, rankCount.get(LottoRank.THIRD));
        assertEquals(1, rankCount.get(LottoRank.FOURTH));
        assertEquals(1, rankCount.get(LottoRank.FIFTH));
        assertEquals(1, rankCount.get(LottoRank.NONE));
    }

    @Test
    @DisplayName("여러 로또를 평가했을 때 각 등수별 개수 카운트")
    void evaluate_whenGivenLottos_thenCountEachRank2() {
        // given
        LottoDraw draw = new LottoDraw(
            List.of(1, 2, 3, 4, 5, 6),
            7
        );

        Lotto firstRankLotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));    // 5개 → THIRD
        Lotto fourthRankLotto = new Lotto(List.of(1, 2, 3, 4, 8, 9));    // 4개 → FOURTH
        Lotto fifthRankLotto = new Lotto(List.of(1, 2, 3, 8, 9, 10));    // 3개 → FIFTH
        Lotto noneRankLotto = new Lotto(List.of(1, 2, 8, 9, 10, 11));    // 2개 → NONE

        List<Lotto> lottos = List.of(
            firstRankLotto,
            fourthRankLotto,
            fifthRankLotto,
            noneRankLotto
        );

        // when
        Map<LottoRank, Integer> rankCount = lottoEvaluator.evaluate(lottos, draw);

        // then
        assertEquals(1, rankCount.get(LottoRank.FIRST));
        assertEquals(0, rankCount.get(LottoRank.SECOND));
        assertEquals(0, rankCount.get(LottoRank.THIRD));
        assertEquals(1, rankCount.get(LottoRank.FOURTH));
        assertEquals(1, rankCount.get(LottoRank.FIFTH));
        assertEquals(1, rankCount.get(LottoRank.NONE));
    }

    @Test
    @DisplayName("로또의 당첨 결과와 구매 금액을 통해 수익률 계산")
    void calculateProfitRate_whenGivenResult_thenReturnProfitRate() {
        // given
        LottoDraw draw = new LottoDraw(
            List.of(1, 2, 3, 4, 5, 6),
            7
        );

        Lotto firstRankLotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));     // 6개 일치 → FIRST
        Lotto secondRankLotto = new Lotto(List.of(1, 2, 3, 4, 5, 7));   // 2개 → NONE

        List<Lotto> lottos = List.of(
            firstRankLotto,
            secondRankLotto
        );

        Map<LottoRank, Integer> rankCount = lottoEvaluator.evaluate(lottos, draw);
        int purchaseAmount = 2000;

        double expected = (LottoRank.FIRST.getPrize() + LottoRank.SECOND.getPrize()) / (double) purchaseAmount;

        // when
        double profitRate = lottoEvaluator.calculateProfitRate(rankCount, purchaseAmount);

        // then
        assertEquals(expected, profitRate);
    }

    @Test
    @DisplayName("로또가 모두 당첨이 안되면 수익률은 0")
    void calculateProfitRate_whenNotRanked_thenReturn0() {
        // given
        LottoDraw draw = new LottoDraw(
            List.of(1, 2, 3, 4, 5, 6),
            7
        );

        Lotto noRankedLotto = new Lotto(List.of(8, 9, 10, 11, 12, 13));

        List<Lotto> lottos = List.of(
            noRankedLotto
        );

        Map<LottoRank, Integer> rankCount = lottoEvaluator.evaluate(lottos, draw);
        int purchaseAmount = 1000;

        double expected = 0;
        // when
        double profitRate = lottoEvaluator.calculateProfitRate(rankCount, purchaseAmount);

        // then
        assertEquals(expected, profitRate);
    }
}
