package base;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class UIUtility {

	private static BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));

	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_BLACK = "\u001B[30m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	public static final String ANSI_BLUE = "\u001B[34m";
	public static final String ANSI_PURPLE = "\u001B[35m";
	public static final String ANSI_CYAN = "\u001B[36m";
	public static final String ANSI_WHITE = "\u001B[37m";

	public static void printLineColor(String lineToPrint, String color) {
		System.out.println(color + lineToPrint + ANSI_RESET);	
	}

	public static void clearScreen() {  
		System.out.print("\033[H\033[2J");  
		System.out.flush();  
	}  
	
	public static void printTitle() {
		printLineColor("CODEX NATURALIS by group 45", ANSI_GREEN);
	}

	public static void mainMenuManagement() {

		Boolean valid= false;

		do {
			try {
				System.out.println("Press '1' to start a game!");	
				System.out.println("Press '2' to quit!");	

				String input = consoleReader.readLine();

				switch (input) {
				case "1": {
					valid = true;
					break;
				}
				case "2": {
					valid = true;
					System.exit(0);
					break;
				}
				default:{
					throw new IllegalArgumentException("Unexpected value: " + input);
				}
				}
			}
			catch(IllegalArgumentException | IOException ex) {
				clearScreen();
				printLineColor("Invalid operation! please select a valid option!", ANSI_RED);
			}
		}while(!valid);
	}

	public static int playerSelectionManagement() {

		int players = -1;
		Boolean valid= false;

		do {
			try {
				System.out.println("How many people are playing the game? (2-4)");

				String input = consoleReader.readLine();

				switch (input) {
				case "2":
				case "3":
				case "4":
				{
					valid = true;
					players = Integer.parseInt(input);
					break;
				}
				default:{
					throw new IllegalArgumentException("Unexpected value: " + input);
				}
				}
			}
			catch(IllegalArgumentException | IOException ex) {
				clearScreen();
				printLineColor("Invalid operation! please select a valid option!", ANSI_RED);
			}
		}while(!valid);

		return players;
	}

	public static Player playerCreation(int id) {

		Boolean valid= false;
		String playerName = "";
		String playerColor= "";

		do {
			try {
				System.out.println("Whats the name of the player?");

				playerName = consoleReader.readLine();	

				valid = true;
			}
			catch(IllegalArgumentException | IOException ex) {
				clearScreen();
				printLineColor("Invalid operation! please select a valid option!", ANSI_RED);
			}
		}while(!valid);

		valid= false;

		do {
			try {
				System.out.println("Choose a color for this player:");
				printLineColor("1 - RED",ANSI_RED);
				printLineColor("2 - GREEN",ANSI_GREEN);
				printLineColor("3 - YELLOW",ANSI_YELLOW);
				printLineColor("4 - BLUE",ANSI_BLUE);
				printLineColor("5 - PURPLE",ANSI_PURPLE);
				printLineColor("6 - CYAN",ANSI_CYAN);
				
				playerColor = consoleReader.readLine();	
				
				switch (playerColor) {
				case "1":
				{
					valid = true;
					playerColor = ANSI_RED;
					break;
				}
				case "2":
				{
					valid = true;
					playerColor = ANSI_GREEN;
					break;
				}
				case "3":
				{
					valid = true;
					playerColor = ANSI_YELLOW;
					break;
				}
				case "4":
				{
					valid = true;
					playerColor = ANSI_BLUE;
					break;
				}
				case "5":
				{
					valid = true;
					playerColor = ANSI_PURPLE;
					break;
				}
				case "6":
				{
					valid = true;
					playerColor = ANSI_CYAN;
					break;
				}
				default:{
					throw new IllegalArgumentException("Unexpected value: " + playerColor);
				}
				}
			}
			catch(IllegalArgumentException | IOException ex) {
				clearScreen();
				printLineColor("Invalid operation! please select a valid option!", ANSI_RED);
			}
		}while(!valid);

		return new Player(id, playerName, playerColor);
	}
}
