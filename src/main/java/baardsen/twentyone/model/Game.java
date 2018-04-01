package baardsen.twentyone.model;

import static java.util.stream.Collectors.joining;

public class Game {

	private final Deck deck;
	private final Player dealer;
	private final Player sam;
	private final String summary;

	private Game(Deck deck) {
		this.deck = deck;
		this.sam = Player.sam();
		this.dealer = Player.dealer(this.sam);

		dealInitialHands();
		if (sam.getScore() != 21 && !dealer.isBust()) {
			dealCardsTo(sam);
			dealCardsTo(dealer);
		}
		this.summary = generateSummary();
	}

	public static Game play(Deck deck) {
		return new Game(deck);
	}

	public boolean dealerWon() {
		return dealer.getScore() > sam.getScore() ||
				(dealer.isBust() && sam.isBust());
	}

	public String getSummary() {
		return summary;
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

	private static final String SUMMARY_FORMAT = "%s\nsam: %s\ndealer: %s";

	private String generateSummary() {
		return String.format(SUMMARY_FORMAT,
				dealerWon() ? "dealer" : "sam",
				stringifyCards(sam),
				stringifyCards(dealer));
	}

	private String stringifyCards(Player player) {
		return player.getCards().stream()
				.map(Card::toString)
				.collect(joining(", "));
	}

}
