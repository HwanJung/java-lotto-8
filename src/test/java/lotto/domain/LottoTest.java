package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class LottoTest {
    @Test
    void 로또_번호의_개수가_6개가_넘어가면_예외가_발생한다() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 6, 7)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("로또 번호에 중복된 숫자가 있으면 예외가 발생한다.")
    @Test
    void 로또_번호에_중복된_숫자가_있으면_예외가_발생한다() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 5)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("로또 번호에서 당첨 번호와 몇개가 맞는지 반환")
    void countMatchedNumbers_whenWinningNumbersIsGiven_thenReturnCount() {
        // given
        Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        List<Integer> winningNumbers = List.of(7, 8, 9, 4, 5, 6);

        // when
        int cnt = lotto.countMatchedNumbers(winningNumbers);

        // then
        assertEquals(3, cnt);
    }

    @Test
    @DisplayName("로또 번호가 보너스 번호를 가지고 있다면 참 반환")
    void hasBonusNumber_whenNumbersHaveBonusNumber_thenReturnTrue() {
        // given
        Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        int bonusNumber = 1;

        // when
        boolean result = lotto.hasBonusNumber(bonusNumber);

        // then
        assertTrue(result);
    }

    @Test
    @DisplayName("로또 번호가 보너스 번호를 가지고 있지 않으면 참=거짓 반환")
    void hasBonusNumber_whenNumbersNotHaveBonusNumber_thenReturnFalse() {
        // given
        Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        int bonusNumber = 7;

        // when
        boolean result = lotto.hasBonusNumber(bonusNumber);

        // then
        assertFalse(result);
    }
}
