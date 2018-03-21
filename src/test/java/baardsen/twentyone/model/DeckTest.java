package baardsen.twentyone.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.DynamicTest.dynamicTest; 

import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;

public class DeckTest {

	@Test
	public void deal_generatedDeck_hasExactly52CardsInDeck() {
		var deck = Deck.newDeck();
		IntStream.range(0, 52).forEach(i -> assertNotNull(deck.deal()));
		assertThrows(IndexOutOfBoundsException.class, () -> deck.deal());
	}

	@TestFactory
	public Stream<DynamicTest> fromString_validStringOfCards_deckIsGeneratedCorrectly() {
		var strings = Map.of(
				"SA, H10, C5", List.of(
						Card.of(Suit.SPADE, Rank.ACE),
						Card.of(Suit.HEART, Rank.TEN),
						Card.of(Suit.CLUB, Rank.FIVE)),
				"SA,H10,C5", List.of(
						Card.of(Suit.SPADE, Rank.ACE),
						Card.of(Suit.HEART, Rank.TEN),
						Card.of(Suit.CLUB, Rank.FIVE)),
				"DQ, C2, S7", List.of(
						Card.of(Suit.DIAMOND, Rank.QUEEN),
						Card.of(Suit.CLUB, Rank.TWO),
						Card.of(Suit.SPADE, Rank.SEVEN)));

		return strings.entrySet().stream()
				.map(entry -> dynamicTest(entry.getKey(), () -> {
					var deck = Deck.fromString(entry.getKey()).get();
					entry.getValue()
							.forEach(card -> assertEquals(card, deck.deal()));
					assertThrows(IndexOutOfBoundsException.class,
							() -> deck.deal());
				}));
	}
	
	@Test
	public void fromString_invalidStringOfCards_emptyOptional() {
		var deck = Deck.fromString("SA, H1, H2");
		assertFalse(deck.isPresent());
	}

}
