package baardsen.twentyone.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class GameTest {

	@Test
	public void dealerWon_samGetsAHigherScore_false() {
		dealerWon("SA, H10, S8, H7, H9", false);
	}

	@Test
	public void dealerWon_dealerGetsAHigherScore_true() {
		dealerWon("SA, H10, S8, H7, H3", true);
	}

	@Test
	public void dealerWon_samStartsWith21_false() {
		dealerWon("SA, H10, SK, H7, H9", false);
	}

	@Test
	public void dealerWon_dealerStartsWith21_true() {
		dealerWon("SA, HA, S8, HK", true);
	}

	@Test
	public void dealerWon_bothStartWith21_false() {
		dealerWon("SA, HA, SK, HK, H10", false);
	}

	@Test
	public void dealerWon_samStartsWith22_true() {
		dealerWon("SA, H10, CA, H7", true);
	}

	@Test
	public void dealerWon_dealerStartsWith22_false() {
		dealerWon("SA, HA, S8, DA", false);
	}

	@Test
	public void dealerWon_bothStartWith22_true() {
		dealerWon("SA, HA, CA, DA", true);
	}

	@Test
	public void dealerWon_dealerMatchesScoreOf21_dealerDrawsAnotherCardAndLoses() {
		dealerWon("SA, H10, S5, H5, S3, S2, H6, H2", false);
	}

	@Test
	public void dealerWon_dealerStartsWith22AndSamWillGoBustIfHeDrawsACard_samDoesNotDrawACardAndWins() {
		dealerWon("SA, HA, S5, DA, SK", false);
	}

	@Test
	public void dealerWon_bothStartWith21_dealerLosesBeforeDrawingAnotherCard() {
		dealerWon("SA, HA, SK, HK", false);
	}

	@Test
	public void dealerWon_dealerStartsWith21_dealerWinsBeforeSamDrawsMoreCards() {
		dealerWon("SA, HA, S2, HK", true);
	}

	private void dealerWon(String string, boolean expected) {
		var deck = Deck.fromString(string).get();
		var game = Game.play(deck);
		assertEquals(expected, game.dealerWon());
	}

	@Test
	public void getSummary_deckWhereSamWins_summaryIsGeneratedDeclaringSamAsTheWinner() {
		getSummary(
				"SK, H10, S8, H7, H9",
				"sam\nsam: SK, S8\ndealer: H10, H7, H9");
	}

	@Test
	public void getSummary_deckWhereDealerWins_summaryIsGeneratedDeclaringDealerAsTheWinner() {
		getSummary(
				"SK, H10, S6, H5, S10",
				"dealer\nsam: SK, S6, S10\ndealer: H10, H5");
	}

	private void getSummary(String string, String expected) {
		var deck = Deck.fromString(string).get();
		var game = Game.play(deck);
		assertEquals(expected, game.getSummary());
	}

}
