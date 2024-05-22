package base;

import java.io.FileNotFoundException;
import java.io.FileReader;

import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;

public class CardUtility {

	private static Card cards[];
	/**
	 * get the Arrays of Card from the Json file
	 */
	public static void readCardsFromFile() {

		JsonReader reader = null;
		try {
			reader = new JsonReader(new FileReader("cards.json"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		GsonBuilder gsonBldr = new GsonBuilder();
		gsonBldr.registerTypeAdapter(Card[].class, new CardJsonAdapter());

		cards = gsonBldr.create().fromJson(reader, Card[].class);
	}
	/**
	 * get the Arrays of all Card
	 * @return Arrays of Card
	 */
	public static Card[] getCards() {
		return cards;
	}
}
