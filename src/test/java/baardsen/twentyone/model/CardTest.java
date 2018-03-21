package baardsen.twentyone.model;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import baardsen.twentyone.model.Card;

public class CardTest {

	@TestFactory
	public Stream<DynamicTest> fromString_validCardPattern_presentOptional() {
		var suits = Set.of("C", "H", "D", "S");
		var ranks = Set.of(
				"2", "3", "4", "5", "6", "7", "8",
				"9", "10", "J", "Q", "K", "A");
		return ranks.stream()
				.flatMap(rank -> suits.stream().map(suit -> suit + rank))
				.map(code -> dynamicTest(
						String.format("Card.fromString(\"%s\")", code),
						() -> {
							Optional<Card> card = Card.fromString(code);
							assertTrue(card.isPresent());
						}));
	}

	@ParameterizedTest
	@ValueSource(strings = {"S1", "T2", "H11", "C0"})
	public void fromString_invalidCardPattern_emptyOptional(String code) {
		var card = Card.fromString(code);
		assertFalse(card.isPresent());
	}

}
