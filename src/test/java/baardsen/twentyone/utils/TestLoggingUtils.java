package baardsen.twentyone.utils;

import java.io.OutputStream;
import java.io.PrintStream;

public class TestLoggingUtils {

	public static void disableLogging(Runnable runnable) {
		var origOut = System.out;
		var origErr = System.err;
		var printStream = new PrintStream(new NullOutputStream());
		System.setOut(printStream);
		System.setErr(printStream);
		try {
			runnable.run();
		} finally {
			System.setOut(origOut);
			System.setErr(origErr);
		}
	}

	private static class NullOutputStream extends OutputStream {

		@Override
		public void write(int b) {}

	}
}
