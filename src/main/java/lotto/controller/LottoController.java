package lotto.controller;

import lotto.application.LottoEvaluator;
import lotto.application.LottoIssuer;
import lotto.domain.Lotto;
import lotto.domain.LottoDraw;
import lotto.domain.LottoRank;
import lotto.ui.input.InputView;
import lotto.ui.input.Parser;
import lotto.ui.output.OutputView;

import java.util.List;
import java.util.Map;

public class LottoController {
    private final LottoEvaluator lottoEvaluator;
    private final LottoIssuer lottoIssuer;
    private final InputView inputView;
    private final OutputView outputView;
    private final Parser parser;

    public LottoController(LottoEvaluator lottoEvaluator, LottoIssuer lottoIssuer, InputView inputView, OutputView outputView, Parser parser) {
        this.lottoEvaluator = lottoEvaluator;
        this.lottoIssuer = lottoIssuer;
        this.inputView = inputView;
        this.outputView = outputView;
        this.parser = parser;
    }

    public void run() {
        int purchaseAmount = readPurchaseAmount();
        List<Lotto> lottos = lottoIssuer.buyLotto(purchaseAmount);
        outputView.printPurchases(lottos);

        LottoDraw lottoDraw = readLottoDraw();

        Map<LottoRank, Integer> rankCount = lottoEvaluator.evaluate(lottos, lottoDraw);
        double profitRate = lottoEvaluator.calculateProfitRate(rankCount, purchaseAmount);
        outputView.printAllRanks(rankCount);
        outputView.printProfitRate(profitRate);
    }

    private int readPurchaseAmount() {
        while (true) {
            try {
                String input = inputView.readPurchaseAmount();
                return parser.parsePurchaseAmount(input);
            } catch (IllegalArgumentException e) {
                outputView.printErrorMsg(e);
            }
        }
    }

    private LottoDraw readLottoDraw() {
        List<Integer> winningNumbers = readWinningNumbers();

        while (true) {
            try {
                int bonusNumber = parser.parseBonusNumber(inputView.readBonusNumber());
                return new LottoDraw(winningNumbers, bonusNumber);
            } catch (IllegalArgumentException e) {
                outputView.printErrorMsg(e);
            }
        }
    }

    private List<Integer> readWinningNumbers() {
        while (true) {
            try {
                return parser.parseWinningNumbers(inputView.readWinningNumbers());
            } catch (IllegalArgumentException e) {
                outputView.printErrorMsg(e);
            }
        }
    }
}
