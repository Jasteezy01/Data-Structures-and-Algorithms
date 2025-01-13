package comprehensive;

import java.io.IOException;

public class RandomPhraseGenerator {

	public static void main(String[] args) throws NumberFormatException, IOException {
		new GrammerOrganizer(args[0], Integer.parseInt(args[1]));
	}

}
