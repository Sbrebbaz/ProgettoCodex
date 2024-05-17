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

		cards = new CardUtility().readCardsFromFile();		//memorizzo tutte le carte nell'ArrayList cards
	
		System.out.println(cards);							//stampo tutte la lista delle carte
	}
	
}
