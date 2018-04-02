package baardsen.twentyone.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class FileUtilsTest {

	@Test
	public void read_validPath_fileContent() {
		var content = FileUtils
				.read("src/test/resources/file-with-content.txt");
		assertTrue(content.isPresent());
		assertEquals("This is the file's content", content.get());
	}

	@Test
	public void read_nonexistentPath_emptyOptional() {
		TestLoggingUtils.disableLogging(() -> {
			var content = FileUtils
					.read("src/test/resources/does-not-exist.txt");
			assertFalse(content.isPresent());
		});
	}

}
