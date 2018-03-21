package baardsen.twentyone.model;

import static java.util.Map.entry;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;

import java.util.Map;
import java.util.stream.Stream;

import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;

class SuitTest {

	@TestFactory
	public Stream<DynamicTest> fromString_allSuitsAreParsedCorrectly() {
		var values = Map.ofEntries(
				entry("S", Suit.SPADE),
				entry("H", Suit.HEART),
				entry("D", Suit.DIAMOND),
				entry("C", Suit.CLUB));
		return values.entrySet().stream()
				.map(entry -> dynamicTest(
						String.format("Suit.fromString(\"%s\")", 
								entry.getKey()),
						() -> {
							assertEquals(entry.getValue(),
									Suit.fromString(entry.getKey()).get());
						}));
	}

}
