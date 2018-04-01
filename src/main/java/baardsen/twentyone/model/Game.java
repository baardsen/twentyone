package baardsen.twentyone.model;

public class Game {

	private final Deck deck;
	private final Player dealer;
	private final Player sam;

	private Game(Deck deck) {
		this.deck = deck;
		this.sam = Player.sam();
		this.dealer = Player.dealer(this.sam);

		dealInitialHands();
		if (sam.getScore() != 21 && !dealer.isBust()) {
			dealCardsTo(sam);
			dealCardsTo(dealer);
		}
	}

	public static Game play(Deck deck) {
		return new Game(deck);
	}

	public boolean dealerWon() {
		return dealer.getScore() > sam.getScore() ||
				(dealer.isBust() && sam.isBust());
	}

	private void dealInitialHands() {
		sam.addCard(deck.deal());
		dealer.addCard(deck.deal());
		sam.addCard(deck.deal());
		dealer.addCard(deck.deal());
	}

	private void dealCardsTo(Player player) {
		while (player.hitMe())
			player.addCard(deck.deal());
	}

}
