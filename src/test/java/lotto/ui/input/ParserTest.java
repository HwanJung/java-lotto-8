package lotto.ui.input;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class ParserTest {
    private final Parser parser = new Parser();

    @Test
    @DisplayName("사용자의 구매 금액 입력이 숫자가 아니라면 예외")
    void parsePurchaseAmount_whenInputIsNotNumber_thenThrowsException() {
        // given
        String input = "faa";

        // when & then
        assertThrows(IllegalArgumentException.class,
            () -> parser.parsePurchaseAmount(input));
    }

    @Test
    @DisplayName("사용자의 구매 금액 입력이 1000이 넘지 않으면 예외")
    void parsePurchaseAmount_whenInputIsNotExceed1000_thenThrowsException() {
        // given
        String input = "800";

        // when & then
        assertThrows(IllegalArgumentException.class,
            () -> parser.parsePurchaseAmount(input));
    }

    @Test
    @DisplayName("사용자의 구매 금액 입력이 1000으로 나누어 떨어지지 않으면 예외")
    void parsePurchaseAmount_whenInputIsNotMultipleOf1000_thenThrowsException() {
        // given
        String input = "1500";

        // when & then
        assertThrows(IllegalArgumentException.class,
            () -> parser.parsePurchaseAmount(input));
    }

    @Test
    @DisplayName("사용자의 구매 금액 입력이 정상이라면 정수 반환")
    void parsePurchaseAmount_whenValidInput_thenReturnInt() {
        // given
        String input = "8000";

        // when
        int amount = parser.parsePurchaseAmount(input);

        // then
        assertEquals(8000, amount);
    }

    @Test
    @DisplayName("사용자의 당첨 번호 입력이 숫자가 아니면 예외")
    void parseWinningNumbers_whenInvalidInputFormat_thenThrowsException() {
        // given
        String input = "1,a,3,5,6,7";

        // when & then
        assertThrows(IllegalArgumentException.class,
            () -> parser.parseWinningNumbers(input));
    }

    @Test
    @DisplayName("사용자의 당첨 번호 입력이 여섯개가 아니라면 예외")
    void parseWinningNumbers_whenNotSixInput_thenThrowsException() {
        // given
        String input = "1,2,3";

        // when & then
        assertThrows(IllegalArgumentException.class,
            () -> parser.parseWinningNumbers(input));
    }

    @Test
    @DisplayName("사용자의 당첨 번호 입력이 1에서 45 사이가 아니라면 예외")
    void parseWinningNumbers_whenNumberExceed45_thenThrowsException() {
        // given
        String input = "1,2,3,4,5,46";

        // when & then
        assertThrows(IllegalArgumentException.class,
            () -> parser.parseWinningNumbers(input));
    }

    @Test
    @DisplayName("사용자의 당첨 번호 입력이 1에서 45 사이가 아니라면 예외")
    void parseWinningNumbers_whenNumberLowerThan1_thenThrowsException() {
        // given
        String input = "0,1,2,3,4,45";

        // when & then
        assertThrows(IllegalArgumentException.class,
            () -> parser.parseWinningNumbers(input));
    }

    @Test
    @DisplayName("사용자의 당첨 번호 입력이 정상이라면 정수 리스트 반환")
    void parseWinningNumbers_whenValidInput_thenReturnIntegerList() {
        // given
        String input = "1,2,3,4,5,45";
        List<Integer> expected = List.of(1, 2, 3, 4, 5, 45);

        // when
        List<Integer> integerList = parser.parseWinningNumbers(input);

        // then
        assertThat(expected).containsExactlyElementsOf(integerList);
    }

    @Test
    @DisplayName("사용자의 보너스 번호 입력이 숫자가 아니라면 예외")
    void parseBonusNumber_whenNotNumber_thenThrowsException() {
        // given
        String input = "A";

        // when & then
        assertThrows(IllegalArgumentException.class,
            () -> parser.parseBonusNumber(input));
    }

    @Test
    @DisplayName("사용자의 보너스 번호 입력이 1에서 45 사이가 아니라면 예외")
    void parseBonusNumber_whenNumberExceed45_thenThrowsException() {
        // given
        String input = "46";

        // when & then
        assertThrows(IllegalArgumentException.class,
            () -> parser.parseBonusNumber(input));
    }

    @Test
    @DisplayName("사용자의 보너스 번호 입력이 1에서 45 사이가 아니라면 예외")
    void parseBonusNumber_whenNumberLowerThan1_thenThrowsException() {
        // given
        String input = "0";

        // when & then
        assertThrows(IllegalArgumentException.class,
            () -> parser.parseBonusNumber(input));
    }
}
