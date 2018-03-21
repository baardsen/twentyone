package baardsen.twentyone.model;

import java.util.Optional;
import java.util.regex.Pattern;

public class Card {

	private final Suit suit;
	private final Rank rank;

	private Card(Suit suit, Rank rank) {
		this.suit = suit;
		this.rank = rank;
	}

	private static Pattern CARD_PATTERN = Pattern
			.compile("(?<suit>[CDHS])(?<rank>[2-9JKQA]|10)");

	public static Card of(Suit suit, Rank rank) {
		return new Card(suit, rank);
	}

	public static Optional<Card> fromString(String string) {
		if (string == null)
			return Optional.empty();

		var matcher = CARD_PATTERN.matcher(string);
		if (!matcher.matches())
			return Optional.empty();

		var suit = Suit.fromString(matcher.group("suit"));
		if (!suit.isPresent())
			return Optional.empty();

		var rank = Rank.fromString(matcher.group("rank"));
		if (!rank.isPresent())
			return Optional.empty();

		return Optional.of(new Card(suit.get(), rank.get()));
	}

	public Suit getSuit() {
		return suit;
	}

	public Rank getRank() {
		return rank;
	}

}
