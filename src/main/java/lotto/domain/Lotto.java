package lotto.domain;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Lotto {
    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        Collections.sort(numbers);
        this.numbers = numbers;
    }

    private void validate(List<Integer> numbers) {
        if (numbers.size() != 6) {
            throw new IllegalArgumentException("로또 번호는 6개여야 합니다.");
        }
    }

    public int countMatchedNumbers(List<Integer> winningNumbers) {
        long count = numbers.stream()
            .filter(winningNumbers::contains)
            .count();

        return (int) count;
    }

    public boolean hasBonusNumber(int bonusNumber) {
        return numbers.contains(bonusNumber);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        String numbersString = numbers.stream()
            .map(String::valueOf)
            .collect(Collectors.joining(", "));
        sb.append(numbersString);
        sb.append("]");

        return sb.toString();
    }
}
