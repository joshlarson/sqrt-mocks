package sqrt.without.mock;

import static java.lang.Math.sqrt;

public class SquareRoot {

	public Double calculate(final double input) {
		if (input < 0.0) {
			return null;
		}
		return new Double(sqrt(input));
	}

}
