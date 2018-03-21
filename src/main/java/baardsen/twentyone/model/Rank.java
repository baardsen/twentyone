package baardsen.twentyone.model;

import java.util.Optional;

public enum Rank {

	TWO("2", 2),
	THREE("3", 3),
	FOUR("4", 4),
	FIVE("5", 5),
	SIX("6", 6),
	SEVEN("7", 7),
	EIGHT("8", 8),
	NINE("9", 9),
	TEN("10", 10),
	JACK("J", 10),
	QUEEN("Q", 10),
	KING("K", 10),
	ACE("A", 11);

	private final String code;
	private final int value;

	private Rank(String code, int value) {
		this.code = code;
		this.value = value;
	}

	public static Optional<Rank> fromString(String string) {
		for (var rank : values())
			if (rank.code.equals(string))
				return Optional.of(rank);
		return Optional.empty();
	}

	public String getCode() {
		return code;
	}

	public int getValue() {
		return value;
	}
}
