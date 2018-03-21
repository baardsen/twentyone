package baardsen.twentyone.model;

import java.util.ArrayList;
import java.util.List;

public class Player {

	private final List<Card> cards = new ArrayList<>();

	public static Player dealer() {
		return new Player();
	}

	public static Player sam() {
		return new Player();
	}

	public void addCard(Card card) {
		cards.add(card);
	}

	public int getScore() {
		return isBust() ? 0 : score();
	}

	public boolean isBust() {
		return score() > 21;
	}

	private int score() {
		return cards.stream()
				.mapToInt(Card::getValue)
				.sum();
	}

}
