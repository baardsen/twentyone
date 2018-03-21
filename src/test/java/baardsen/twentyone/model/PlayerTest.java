package baardsen.twentyone.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class PlayerTest {

	@Test
	public void getScore_validHand_sumOfCardValues() {
		var player = Player.sam();
		player.addCard(Card.of(Suit.SPADE, Rank.TEN));
		player.addCard(Card.of(Suit.HEART, Rank.TWO));
		player.addCard(Card.of(Suit.CLUB, Rank.SEVEN));

		assertEquals(19, player.getScore());
	}

	@Test
	public void getScore_bustedHand_0() {
		var player = Player.sam();
		player.addCard(Card.of(Suit.SPADE, Rank.TEN));
		player.addCard(Card.of(Suit.HEART, Rank.EIGHT));
		player.addCard(Card.of(Suit.CLUB, Rank.SEVEN));

		assertEquals(0, player.getScore());
	}

	@Test
	public void isBust_validHand_false() {
		var player = Player.sam();
		player.addCard(Card.of(Suit.SPADE, Rank.ACE));
		player.addCard(Card.of(Suit.SPADE, Rank.KING));

		assertFalse(player.isBust());
	}

	@Test
	public void isBust_bustedHand_true() {
		var player = Player.sam();
		player.addCard(Card.of(Suit.SPADE, Rank.ACE));
		player.addCard(Card.of(Suit.HEART, Rank.ACE));

		assertTrue(player.isBust());
	}

	@Test
	public void hitMe_samWithScoreOf16_true() {
		var player = Player.sam();
		player.addCard(Card.of(Suit.SPADE, Rank.KING));
		player.addCard(Card.of(Suit.SPADE, Rank.SIX));

		assertEquals(16, player.getScore());
		assertTrue(player.hitMe());
	}

	@Test
	public void hitMe_samWithScoreOf17_false() {
		var player = Player.sam();
		player.addCard(Card.of(Suit.SPADE, Rank.KING));
		player.addCard(Card.of(Suit.SPADE, Rank.SEVEN));

		assertEquals(17, player.getScore());
		assertFalse(player.hitMe());
	}

	@Test
	public void hitMe_dealerWithLowerScoreThanSam_true() {
		var sam = Player.sam();
		sam.addCard(Card.of(Suit.SPADE, Rank.KING));
		sam.addCard(Card.of(Suit.SPADE, Rank.EIGHT));

		assertEquals(18, sam.getScore());

		var dealer = Player.dealer(sam);
		dealer.addCard(Card.of(Suit.SPADE, Rank.KING));
		dealer.addCard(Card.of(Suit.SPADE, Rank.SIX));

		assertEquals(16, dealer.getScore());

		assertTrue(dealer.hitMe());
	}

	@Test
	public void hitMe_dealerWithHigherScoreThanSam_false() {
		var sam = Player.sam();
		sam.addCard(Card.of(Suit.SPADE, Rank.KING));
		sam.addCard(Card.of(Suit.SPADE, Rank.EIGHT));

		assertEquals(18, sam.getScore());

		var dealer = Player.dealer(sam);
		dealer.addCard(Card.of(Suit.SPADE, Rank.KING));
		dealer.addCard(Card.of(Suit.SPADE, Rank.JACK));

		assertEquals(20, dealer.getScore());

		assertFalse(dealer.hitMe());
	}

	@Test
	public void hitMe_dealerWhoIsBust_false() {
		var sam = Player.sam();
		sam.addCard(Card.of(Suit.SPADE, Rank.KING));
		sam.addCard(Card.of(Suit.SPADE, Rank.EIGHT));

		assertEquals(18, sam.getScore());

		var dealer = Player.dealer(sam);
		dealer.addCard(Card.of(Suit.SPADE, Rank.KING));
		dealer.addCard(Card.of(Suit.SPADE, Rank.SIX));
		dealer.addCard(Card.of(Suit.SPADE, Rank.TEN));

		assertTrue(dealer.isBust());

		assertFalse(dealer.hitMe());
	}

	@Test
	public void hitMe_dealerWhenSamIsBust_false() {
		var sam = Player.sam();
		sam.addCard(Card.of(Suit.SPADE, Rank.KING));
		sam.addCard(Card.of(Suit.SPADE, Rank.SIX));
		sam.addCard(Card.of(Suit.SPADE, Rank.EIGHT));

		assertTrue(sam.isBust());

		var dealer = Player.dealer(sam);
		dealer.addCard(Card.of(Suit.SPADE, Rank.KING));
		dealer.addCard(Card.of(Suit.SPADE, Rank.TEN));

		assertFalse(dealer.hitMe());
	}

	@Test
	public void hitMe_dealerWhoHasTheSameScoreAsSam_true() {
		var sam = Player.sam();
		sam.addCard(Card.of(Suit.SPADE, Rank.ACE));
		sam.addCard(Card.of(Suit.SPADE, Rank.EIGHT));

		assertEquals(19, sam.getScore());

		var dealer = Player.dealer(sam);
		dealer.addCard(Card.of(Suit.HEART, Rank.ACE));
		dealer.addCard(Card.of(Suit.HEART, Rank.EIGHT));

		assertEquals(19, dealer.getScore());

		assertTrue(dealer.hitMe());
	}

}
