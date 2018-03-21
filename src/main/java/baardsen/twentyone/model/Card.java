package baardsen.twentyone.model;

import java.util.Objects;
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

	@Override
	public String toString() {
		return String.format("%s%s", suit.getCode(), rank.getCode());
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Card))
			return false;
		var that = (Card) obj;

		return this.suit == that.suit &&
				this.rank == that.rank;
	}

	@Override
	public int hashCode() {
		return Objects.hash(suit, rank);
	}

	public Suit getSuit() {
		return suit;
	}

	public Rank getRank() {
		return rank;
	}

	public int getValue() {
		return rank.getValue();
	}

}
