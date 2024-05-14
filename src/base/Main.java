package base;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import base.*;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

public class Main {

	private static ArrayList<Card> cards;
	
	public static void main(String[] args) {
		
		_readCardsFromFile();
		
		System.out.println(cards);	
	}
	
	private static void _readCardsFromFile() {
		Gson gson = new Gson();
		JsonReader reader = null;
		try {
			reader = new JsonReader(new FileReader("cards.json"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		cards = gson.fromJson(reader, ArrayList.class);
	}
}
