package baardsen.twentyone.model;

import java.util.Optional;

public enum Rank {

	TWO("2"),
	THREE("3"),
	FOUR("4"),
	FIVE("5"),
	SIX("6"),
	SEVEN("7"),
	EIGHT("8"),
	NINE("9"),
	TEN("10"),
	JACK("J"),
	QUEEN("Q"),
	KING("K"),
	ACE("A");

	private final String code;

	private Rank(String code) {
		this.code = code;
	}

	public static Optional<Rank> fromString(String string) {
		for (var rank : values())
			if (rank.code.equals(string))
				return Optional.of(rank);
		return Optional.empty();
	}
}
