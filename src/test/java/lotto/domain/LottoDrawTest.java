package lotto.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

public class LottoDrawTest {

    @Test
    @DisplayName("로또 추첨 결과 성성 시 보너스 번호가 당첨 번호와 중복되면 예외")
    void createLottoDraw_whenBonusNumberIsDuplicated_thenThrowException() {
        // given
        List<Integer> winningNumbers = List.of(1, 2, 3, 4, 5, 6);
        int bonusNumber = 1;

        // when & then
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new LottoDraw(winningNumbers, bonusNumber);
        });
    }

}
