package sqrt.with.mock;

import static java.lang.Math.sqrt;

public class SquareRoot {

	public Double calculate(final double input) {
		if (input < 0.0) {
			throw new IllegalArgumentException();
		}
		return new Double(sqrt(input));
	}

}
