package sqrt.with.mock.processor;

import sqrt.with.mock.SquareRoot;
import sqrt.with.mock.io.IODevice;

public class SquareRootProcessor {

	public void process(final IODevice io, final SquareRoot sr) {
		try {
			final Double result = sr.calculate(Double.parseDouble(io.read()));
			if (result != null) {
				io.write(Double.toString(result));
			} else {
				io.write("Error: Negative input");
			}
		} catch (final NumberFormatException e) {
			io.write("Error: Non-numeric input");
		}
	}

}
