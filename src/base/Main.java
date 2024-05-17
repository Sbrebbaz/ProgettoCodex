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
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import com.google.gson.internal.LinkedTreeMap;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

public class Main {
	
	public static Card cards[];
	
	public static void main(String[] args) {
				
		_readCardsFromFile();
		ArrayList<Card> tmp = new ArrayList<Card>(Arrays.asList(cards));
		
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
		GsonBuilder gsonBldr = new GsonBuilder();
	    gsonBldr.registerTypeAdapter(Card[].class, new CardJsonAdapter());
	    
	    cards = gsonBldr.create().fromJson(reader, Card[].class);
	}
}
