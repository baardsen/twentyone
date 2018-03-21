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

}
