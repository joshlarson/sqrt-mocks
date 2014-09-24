package sqrt.with.mock;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class SquareRootTest {

	private static final double DELTA = 0.01;

	@Test
	public void testZero() {
		final SquareRoot sr = new SquareRoot();
		assertEquals(0.0, sr.calculate(0.0).doubleValue(), DELTA);
	}

	@Test
	public void testOne() {
		final SquareRoot sr = new SquareRoot();
		assertEquals(1.0, sr.calculate(1.0).doubleValue(), DELTA);
	}

	@Test
	public void testTwo() {
		final SquareRoot sr = new SquareRoot();
		assertEquals(1.414, sr.calculate(2.0).doubleValue(), DELTA);
	}

	@Test(expected=IllegalArgumentException.class)
	public void testNegativeOne() {
		final SquareRoot sr = new SquareRoot();
		sr.calculate(-1.0);
	}

}
