package baardsen.twentyone.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;

public class FileUtils {

	public static Optional<String> read(String filePath) {
		var path = Paths.get(filePath);
		try {
			var lines = Files.readAllLines(path);
			return Optional.of(String.join("\n", lines));
		} catch (IOException e) {
			System.out.printf("Failed to open file: %s\n%s\n", filePath, e);
			return Optional.empty();
		}
	}

}
