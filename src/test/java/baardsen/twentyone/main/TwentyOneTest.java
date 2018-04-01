package baardsen.twentyone.main;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

import org.junit.jupiter.api.Test;

class TwentyOneTest {

	@Test
	public void main_noFileInput_gameSummaryIsPrintedToStdOut() {
		var output = main();
		var format = "(sam|dealer)\n"
				+ "sam:[ ,0-9SHCDJQKA]+\n"
				+ "dealer:[ ,0-9SHCDJQKA]+\n";
		assertTrue(output.matches(format));
	}

	@Test
	public void main_validFileInput_gameSummaryIsPrintedToStdOut() {
		var output = main("src/test/resources/valid-deck.txt");
		var expected = "sam\nsam: CA, H9\ndealer: D5, HQ, S8\n";
		assertEquals(expected, output);
	}

	@Test
	public void main_invalidFileInput_warningIsPrintedToStdOut() {
		var output = main("src/test/resources/invalid-deck.txt");
		assertEquals("File content cannot be parsed to a deck\n", output);
	}

	@Test
	public void main_nonExistentFileInput_warningIsPrintedToStdOut() {
		var output = main("does-not-exist.txt");
		String expected = "File 'does-not-exist.txt' could not be read\n";
		assertTrue(output.endsWith(expected));
	}

	private String main(String... args) {
		var bao = new ByteArrayOutputStream();
		var printStream = new PrintStream(bao, true, StandardCharsets.UTF_8);
		var origOut = System.out;
		System.setOut(printStream);
		TwentyOne.main(args);
		System.setOut(origOut);
		return bao.toString();
	}

}
