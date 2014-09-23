package sqrt.without.mock.io;

import java.util.Scanner;

public class ConsoleIODevice implements IODevice {

	@Override
	public String read() {
		final Scanner scan = new Scanner(System.in);
		final String line = scan.nextLine();
		scan.close();
		return line;
	}

	@Override
	public void write(final String content) {
		System.out.println(content);
	}

}
