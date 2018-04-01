package baardsen.twentyone.main;

import baardsen.twentyone.model.Deck;
import baardsen.twentyone.model.Game;
import baardsen.twentyone.utils.FileUtils;

public class TwentyOne {

	public static void main(String[] args) {
		Deck deck = null;
		if (args.length > 0) {
			var content = FileUtils.read(args[0]);
			if (!content.isPresent()) {
				System.out.printf("File '%s' could not be read\n", args[0]);
				return;
			}
			var optDeck = Deck.fromString(content.get());
			if (!optDeck.isPresent()) {
				System.out.printf("File content cannot be parsed to a deck\n");
				return;
			}
			deck = optDeck.get();
		} else
			deck = Deck.newDeck();
		var game = Game.play(deck);
		System.out.printf("%s\n", game.getSummary());
	}

}
