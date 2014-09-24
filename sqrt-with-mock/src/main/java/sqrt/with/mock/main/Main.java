package sqrt.with.mock.main;

import sqrt.with.mock.SquareRoot;
import sqrt.with.mock.io.ConsoleIODevice;
import sqrt.with.mock.io.IODevice;
import sqrt.with.mock.processor.SquareRootProcessor;

public class Main {

	public static void main(final String[] args) {
		final IODevice io = new ConsoleIODevice();
		final SquareRoot sr = new SquareRoot();
		new SquareRootProcessor().process(io, sr);
	}

}
