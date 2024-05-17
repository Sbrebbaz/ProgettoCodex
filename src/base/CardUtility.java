package base;

import java.util.*;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import java.io.*;



public class CardUtility {
	
	//METODO MEMORIZZAZIONE CARTE
	public ArrayList<Card> readCardsFromFile() {				//metodo che importa le strutture di tutte le carte memorizzate in un file.json
		Gson gson = new Gson();
		JsonReader reader = null;								//creo una nuova istanza di tipo JsonReader l'oggetto che "leggerà" il file.json
		try { 
			reader = new JsonReader(new FileReader("cards.json"));   //controllo che il file esista
		} catch (FileNotFoundException e) {
			loadException();									//richiamo l'eccezione in caso di errore
		}
			 return gson.fromJson(reader, ArrayList.class);		//memorizzo le informazioni del file nell'arrayList cards.
		}
		
	public static void loadException() {
		System.out.println("FILE NOT FOUND");
	}
}
