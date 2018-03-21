package baardsen.twentyone.model;

import static java.util.Map.entry;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;

import java.util.Map;
import java.util.stream.Stream;

import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;

public class RankTest {

	@TestFactory
	public Stream<DynamicTest> getValue_allRanksHaveCorrectValue() {
		var values = Map.ofEntries(
				entry(Rank.TWO, 2),
				entry(Rank.THREE, 3),
				entry(Rank.FOUR, 4),
				entry(Rank.FIVE, 5),
				entry(Rank.SIX, 6),
				entry(Rank.SEVEN, 7),
				entry(Rank.EIGHT, 8),
				entry(Rank.NINE, 9),
				entry(Rank.TEN, 10),
				entry(Rank.JACK, 10),
				entry(Rank.QUEEN, 10),
				entry(Rank.KING, 10),
				entry(Rank.ACE, 11));
		return values.entrySet().stream()
				.map(entry -> dynamicTest(
						String.format("Rank.%s.value", entry.getKey().name()),
						() -> {
							int expected = entry.getValue();
							int actual = entry.getKey().getValue();
							assertEquals(expected, actual);
						}));
	}
	
	@TestFactory
	public Stream<DynamicTest> fromString_allRanksAreParsedCorrectly() {
		var values = Map.ofEntries(
				entry("2", Rank.TWO),
				entry("3", Rank.THREE),
				entry("4", Rank.FOUR),
				entry("5", Rank.FIVE),
				entry("6", Rank.SIX),
				entry("7", Rank.SEVEN),
				entry("8", Rank.EIGHT),
				entry("9", Rank.NINE),
				entry("10", Rank.TEN),
				entry("J", Rank.JACK),
				entry("Q", Rank.QUEEN),
				entry("K", Rank.KING),
				entry("A", Rank.ACE));
		return values.entrySet().stream()
				.map(entry -> dynamicTest(
						String.format("Rank.fromString(\"%s\")", 
								entry.getKey()),
						() -> {
							assertEquals(entry.getValue(),
									Rank.fromString(entry.getKey()).get());
						}));
	}

}
