package baardsen.twentyone.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

class TestLoggingUtilsTest {

	@Test
	public void disableLogging_simpleBlock_blockIsExecutedExactlyOnce() {
		var list = new ArrayList<Object>();
		TestLoggingUtils.disableLogging(() -> {
			list.add(null);
		});
		assertEquals(1, list.size());
	}

	@Test
	public void disableLogging_simpleBlock_SystemOutIsChangedInsideBlock() {
		var origOut = System.out;
		TestLoggingUtils.disableLogging(() -> {
			assertNotEquals(origOut, System.out);
		});
	}

	@Test
	public void disableLogging_simpleBlock_SystemOutIsRestoredAfterBlock() {
		var origOut = System.out;
		TestLoggingUtils.disableLogging(() -> {});
		assertEquals(origOut, System.out);
	}

	@Test
	public void disableLogging_throwingBlock_SystemOutIsRestoredAfterBlock() {
		var origOut = System.out;
		assertThrows(RuntimeException.class, () -> {
			TestLoggingUtils.disableLogging(() -> {
				throw new RuntimeException();
			});
		});
		assertEquals(origOut, System.out);
	}
	
	@Test
	public void disableLogging_simpleBlock_SystemErrIsChangedInsideBlock() {
		var origErr = System.err;
		TestLoggingUtils.disableLogging(() -> {
			assertNotEquals(origErr, System.err);
		});
	}

	@Test
	public void disableLogging_simpleBlock_SystemErrIsRestoredAfterBlock() {
		var origErr = System.err;
		TestLoggingUtils.disableLogging(() -> {});
		assertEquals(origErr, System.err);
	}

	@Test
	public void disableLogging_throwingBlock_SystemErrIsRestoredAfterBlock() {
		var origErr = System.err;
		assertThrows(RuntimeException.class, () -> {
			TestLoggingUtils.disableLogging(() -> {
				throw new RuntimeException();
			});
		});
		assertEquals(origErr, System.err);
	}

}
