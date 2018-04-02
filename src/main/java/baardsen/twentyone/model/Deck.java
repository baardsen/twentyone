package baardsen.twentyone.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Queue;

public class Deck {

	private final Queue<Card> cards;

	private Deck(List<Card> cards) {
		this.cards = new LinkedList<>(cards);
	}

	public static Deck random() {
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
	 * @throws NoSuchElementException if the deck is empty
	 */
	public Card deal() throws NoSuchElementException {
		return cards.remove();
	}

}
