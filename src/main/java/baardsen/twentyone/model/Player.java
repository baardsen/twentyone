package baardsen.twentyone.model;

import java.util.ArrayList;
import java.util.List;

public abstract class Player {

	private final List<Card> cards = new ArrayList<>();

	public static Player dealer(Player opponent) {
		return new Dealer(opponent);
	}

	public static Player sam() {
		return new Sam();
	}

	public abstract boolean hitMe();

	private static class Dealer extends Player {

		private final Player opponent;

		public Dealer(Player opponent) {
			this.opponent = opponent;
		}

		@Override
		public boolean hitMe() {
			return !isBust() &&
					score() <= opponent.getScore();
		}

	}

	private static class Sam extends Player {

		@Override
		public boolean hitMe() {
			return score() < 17;
		}

	}

	public void addCard(Card card) {
		cards.add(card);
	}

	public int getScore() {
		return isBust() ? 0 : score();
	}

	protected boolean isBust() {
		return score() > 21;
	}

	protected int score() {
		return cards.stream()
				.mapToInt(Card::getValue)
				.sum();
	}

}
