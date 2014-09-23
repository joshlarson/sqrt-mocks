package sqrt.without.mock.processor;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import sqrt.without.mock.SquareRoot;
import sqrt.without.mock.io.IODevice;

public class SquareRootProcessorTest {

	@Test
	public void testGoodInput() {
		final SquareRoot sr = new SquareRoot();

		final StoringIODevice io = new StoringIODevice("100.0");

		final SquareRootProcessor proc = new SquareRootProcessor();
		proc.process(io, sr);

		assertEquals("10.0", io.contentWritten);
	}

	@Test
	public void testNonNumericInput() {
		final SquareRoot sr = new SquareRoot();

		final StoringIODevice io = new StoringIODevice("hello");

		final SquareRootProcessor proc = new SquareRootProcessor();
		proc.process(io, sr);

		assertEquals("Error: Non-numeric input", io.contentWritten);
	}

	@Test
	public void testNegativeInput() {
		final SquareRoot sr = new SquareRoot();

		final StoringIODevice io = new StoringIODevice("-10.0");

		final SquareRootProcessor proc = new SquareRootProcessor();
		proc.process(io, sr);

		assertEquals("Error: Negative input", io.contentWritten);
	}

	private final class StoringIODevice implements IODevice {

		private String contentWritten;
		private final String contentToRead;

		public StoringIODevice(final String contentToRead) {
			this.contentToRead = contentToRead;
		}

		@Override
		public void write(final String contentWritten) {
			this.contentWritten = contentWritten;
		}

		@Override
		public String read() {
			return contentToRead;
		}
	}

}
