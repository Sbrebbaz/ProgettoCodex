package base;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import base.*;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import com.google.gson.internal.LinkedTreeMap;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

public class Main {
	
	public static Card cards[];
	
	public static void main(String[] args) {
				
		/*ArrayList<Symbol> symb = new ArrayList<Symbol>();
		symb.add(Symbol.INK);
		symb.add(Symbol.SCROLL);
		
		Side s = new GenericSide(
				symb,
				new Corner[] {new Corner(Symbol.INK),new Corner(Symbol.INK),new Corner(Symbol.INK),new Corner(Symbol.INK)});

		Card c2 = new Card(Symbol.FEATHER,s,s,CardType.GOLD);
		Card c3 = new Card(Symbol.FEATHER,s,s,CardType.GOLD);
		
		ArrayList<Card> cards = new ArrayList<Card>();
		cards.add(c2);
		cards.add(c3);
		
		Gson gson = new Gson();
		
	    try {
	        FileWriter myWriter = new FileWriter("filename.txt");
	        myWriter.write(gson.toJson(cards));
	        myWriter.close();
	        System.out.println("Successfully wrote to the file.");
	      } catch (IOException e) {
	        System.out.println("An error occurred.");
	        e.printStackTrace();
	      }*/
		/*String data;
		try {
		      File myObj = new File("cards.json");
		      Scanner myReader = new Scanner(myObj);
		      while (myReader.hasNextLine()) {
		        data = myReader.nextLine();
		        System.out.println(data);
		      }
		      myReader.close();
		    } catch (FileNotFoundException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
		JsonParser jsonParser = new JsonParser();
		JsonArray jsonArray = (JsonArray) jsonParser.parse(data);*/
		_readCardsFromFile();
		ArrayList<Card> tmp = new ArrayList<Card>(Arrays.asList(cards));
		//tmp.putAll(cards.get(0));
		System.out.println(tmp);
		
	}
	
	private static void _readCardsFromFile() {
		Gson gson = new Gson();
		JsonReader reader = null;
		try {
			reader = new JsonReader(new FileReader("cards.json"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		cards = gson.fromJson(reader, Card[].class);
	}
}
