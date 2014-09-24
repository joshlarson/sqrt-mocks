package sqrt.without.mock.main;

import sqrt.without.mock.SquareRoot;
import sqrt.without.mock.io.ConsoleIODevice;
import sqrt.without.mock.io.IODevice;
import sqrt.without.mock.processor.SquareRootProcessor;

public class Main {

	public static void main(final String[] args) {
		final IODevice io = new ConsoleIODevice();
		final SquareRoot sr = new SquareRoot();
		new SquareRootProcessor().process(io, sr);
	}

}
