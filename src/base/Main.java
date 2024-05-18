package base;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
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


	public static void main(String[] args) {

		int playerCount = -1;
		ArrayList<Player> players = new ArrayList<Player>();

		try {
			//Read card configuration from file
			CardUtility.readCardsFromFile();

			UIUtility.printTitle();

			UIUtility.mainMenuManagement();

			playerCount = UIUtility.playerSelectionManagement();

			for(int i = 0;i < playerCount; i++) {
				players.add(UIUtility.playerCreation(i));
			}

			for(Player player : players) {
				UIUtility.printLineColor(player.toString(), player.getColor());
			}
			

			//Create decks from different card types


		}
		catch(Exception e) {
			UIUtility.printLineColor("There was a fatal problem while processing the latest action: " + e.getMessage(), UIUtility.ANSI_RED);
			UIUtility.printLineColor("Stacktrace: " + e.getStackTrace(), UIUtility.ANSI_RED);
		}
	}







}
