package sqrt.with.mock.processor;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Test;

import sqrt.with.mock.SquareRoot;
import sqrt.with.mock.io.IODevice;

public class SquareRootProcessorTest {

	@Test
	public void testGoodInput() {
		final SquareRoot sr = mock(SquareRoot.class);
		when(sr.calculate(100.0)).thenReturn(new Double(10.0));

		final IODevice io = mock(IODevice.class);
		when(io.read()).thenReturn("100.0");

		final SquareRootProcessor proc = new SquareRootProcessor();
		proc.process(io, sr);

		verify(io).write("10.0");
	}

	@Test
	public void testNonNumericInput() {
		final SquareRoot sr = mock(SquareRoot.class);

		final IODevice io = mock(IODevice.class);
		when(io.read()).thenReturn("hello");

		final SquareRootProcessor proc = new SquareRootProcessor();
		proc.process(io, sr);

		verify(io).write("Error: Non-numeric input");
	}

	@Test
	public void testNegativeInput() {
		final SquareRoot sr = mock(SquareRoot.class);
		when(sr.calculate(-10.0)).thenThrow(new IllegalArgumentException());

		final IODevice io = mock(IODevice.class);
		when(io.read()).thenReturn("-10.0");

		final SquareRootProcessor proc = new SquareRootProcessor();
		proc.process(io, sr);

		verify(io).write("Error: Negative input");
	}

}
