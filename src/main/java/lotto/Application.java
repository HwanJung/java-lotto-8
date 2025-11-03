package lotto;

import lotto.application.LottoEvaluator;
import lotto.application.LottoIssuer;
import lotto.controller.LottoController;
import lotto.domain.generator.LottoGenerator;
import lotto.infrastructure.generator.RandomLottoGenerator;
import lotto.ui.input.InputView;
import lotto.ui.input.Parser;
import lotto.ui.output.OutputView;

public class Application {
    public static void main(String[] args) {
        LottoEvaluator lottoEvaluator = new LottoEvaluator();
        LottoGenerator lottoGenerator = new RandomLottoGenerator();
        LottoIssuer lottoIssuer = new LottoIssuer(lottoGenerator);
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();
        Parser parser = new Parser();

        LottoController lottoController = new LottoController(lottoEvaluator, lottoIssuer, inputView, outputView, parser);
        lottoController.run();
    }
}
