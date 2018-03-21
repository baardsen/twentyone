package baardsen.twentyone.model;

import java.util.Optional;

public enum Suit {

	CLUB("C"), DIAMOND("D"), HEART("H"), SPADE("S");

	private final String code;

	private Suit(String code) {
		this.code = code;
	}

	public static Optional<Suit> fromString(String string) {
		for (var suit : values())
			if (suit.code.equals(string))
				return Optional.of(suit);
		return Optional.empty();
	}

	public String getCode() {
		return code;
	}

}
