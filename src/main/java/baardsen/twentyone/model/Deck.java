package baardsen.twentyone.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class Deck {

	private final List<Card> cards;

	private Deck(List<Card> cards) {
		this.cards = new ArrayList<>(cards);
	}

	public static Deck newDeck() {
		var cards = new ArrayList<Card>();
		for (var suit : Suit.values())
			for (var rank : Rank.values())
				cards.add(Card.of(suit, rank));
		Collections.shuffle(cards);
		return new Deck(cards);
	}

	public static Optional<Deck> fromString(String string) {
		if (string == null)
			return Optional.empty();
		var cards = new ArrayList<Card>();
		for (var part : string.split("\\s*,\\s*")) {
			var card = Card.fromString(part);
			if (!card.isPresent())
				return Optional.empty();
			cards.add(card.get());
		}
		return Optional.of(new Deck(cards));
	}

	/**
	 * 
	 * @return The top card from the deck
	 * @throws IndexOutOfBoundsException if the deck is empty
	 */
	public Card deal() {
		return cards.remove(0);
	}

}
