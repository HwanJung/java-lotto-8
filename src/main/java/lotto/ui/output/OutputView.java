package lotto.ui.output;

import lotto.domain.Lotto;
import lotto.domain.LottoRank;

import java.util.List;
import java.util.Map;

public class OutputView {
    public void printPurchases(List<Lotto> lottos) {
        System.out.println("\n" + lottos.size() + "개를 구매했습니다.");
        StringBuilder sb = new StringBuilder();

        for (Lotto lotto : lottos) {
            sb.append(lotto.toString()).append("\n");
        }

        System.out.println(sb);
    }

    public void printAllRanks(Map<LottoRank, Integer> rankCount) {
        String sb = "\n" + "당첨 통계" + "\n---\n" +
            makeResultFormat(LottoRank.FIFTH, rankCount) +
            makeResultFormat(LottoRank.FOURTH, rankCount) +
            makeResultFormat(LottoRank.THIRD, rankCount) +
            makeResultFormat(LottoRank.SECOND, rankCount) +
            makeResultFormat(LottoRank.FIRST, rankCount);

        System.out.println(sb);
    }

    public void printProfitRate(double profitRate) {
        String format = "총 수익률은 " +
            String.format("%.1f", profitRate) +
            "%입니다.";
        System.out.println(format);
    }

    public void printErrorMsg(Exception e) {
        System.out.println("[ERROR] " + e.getMessage());
    }

    private String makeResultFormat(LottoRank rank, Map<LottoRank, Integer> rankCount) {
        String format = rank.getMatchCount() + "개 일치";
        if (rank.isBonusMatched()) {
            format += ", 보너스 볼 일치";
        }
        format += " (" + String.format("%,d", rank.getPrize()) + "원)" +
            " - " + rankCount.get(rank) + "개\n";

        return format;
    }

}
