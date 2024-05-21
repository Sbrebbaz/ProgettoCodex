package base;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

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
		System.out.print("\n\033[H\033[2J");  
		System.out.flush();  
	}  

	public static void printTitle() {
		printLineColor("CODEX NATURALIS by group 45", ANSI_GREEN);
	}

	public static Boolean mainMenuManagement() {

		Boolean valid= false;

		do {
			try {
				System.out.println("1 - Start a game");	
				System.out.println("2 - Quit");	

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

		return valid;		
	}

	public static int playerSelectionManagement() {

		int players = -1;
		Boolean valid= false;

		do {
			try {
				clearScreen();

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

	public static Player playerCreation() {

		Boolean valid= false;
		String playerName = "";
		String playerColor= "";

		clearScreen();

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

		return new Player(playerName, playerColor);
	}

	public static void setSecretObjectiveCard(Player player) {
		Boolean valid= false;
		do{
			try {

				clearScreen();
				System.out.println("Choose your objective card (1-2):");
				System.out.println(player.ToStringPlayerHand());
				int input = Integer.parseInt(consoleReader.readLine());
				player.SetSecretObjective(player.selectCard(input));
				player.setHand(new ArrayList<Card>());
				valid = true;

			}catch(Exception e) {
				clearScreen();
				printLineColor("Invalid operation! please select a valid option!", ANSI_RED);
			}
		}while(!valid);
	}

	public static void setStartingCard(Player player) {
		Boolean valid= false;
		do{
			try {
				clearScreen();
				System.out.println("Place the starting card:");
				System.out.println("1 - Swap card side");
				System.out.println("2 - Place card");
				System.out.println(player.ToStringPlayerHand());
				int input = Integer.parseInt(consoleReader.readLine());
				switch(input) {
				case 1:
					player.selectCard(1).swapSide();
					break;
				case 2:
					player.placeStartingCard(1);
					valid = true;
					break;
				default:
					throw new IllegalArgumentException("Unexpected value: " + input);
				}
			}catch(Exception e) {
				clearScreen();
				printLineColor("Invalid operation! please select a valid option!", ANSI_RED);
			}
		}while(!valid);
	}

	public static void printPlayer(Player toPrint) {
		printLineColor(toPrint.toString(), toPrint.getColor());
	}

	public static void playerTurnBase(Game game) {
		Boolean valid= false;
		do{
			try {
				clearScreen();
				//System.out.println(game.getCurrentPlayer().ToStringPlayerHand()); --Maybe remove option #4 and always display the player hand?
				System.out.println("Player turn option:");
				System.out.println("1 - Show map");
				System.out.println("2 - Show players");
				System.out.println("3 - Show table");
				System.out.println("4 - Show hand");
				System.out.println("5 - Play card");
				int input = Integer.parseInt(consoleReader.readLine());
				switch(input) {
				case 1:
					System.out.println(game.getCurrentPlayer().getMap().toString());
					break;
				case 2:
					for(Player player : game.getPlayers()) {
						printPlayer(player);
					}						
					break;
				case 3:
					System.out.println(game.getTable().toString());				
					break;
				case 4:
					System.out.println(game.getCurrentPlayer().ToStringPlayerHand());
					break;
				case 5:

					valid = true;
					break;
				default:
					throw new IllegalArgumentException("Unexpected value: " + input);
				}
			}catch(Exception e) {
				clearScreen();
				printLineColor("Invalid operation! please select a valid option!", ANSI_RED);
			}
		}while(!valid);
	}

	public static void printScoreboard(Game game) {

		System.out.println("");	
		System.out.println("WINNER(S): ");	
		System.out.println("");	

		for(Player player : game.getWinningPlayers()) {
			printPlayer(player);
		}	
	}

	public static boolean selectCardToPlace(Player player) {//true: go back, false: continue whit the next phase
		boolean valid = false;
		clearScreen();
		do {
			try {
				System.out.println("Choose a card to place (1-3) or type -1 to go back:");
				System.out.println(player.ToStringPlayerHand());
				int index = Integer.parseInt(consoleReader.readLine());
				if(index == -1) {
					return true;
				}
				if(!player.inRange(index-1)) {
					throw new IllegalArgumentException("Unexpected value: " + index);
				}
				if(!selectSideToPlace(player,index)){
					valid = true;
				}else {
					clearScreen();
				}
			}catch(Exception e) {
				clearScreen();
				printLineColor("Invalid operation! please select a valid option!", ANSI_RED);
			}

		}while(!valid);
		return false;
	}

	public static boolean selectSideToPlace(Player player,int index) {//true: go back, false: continue whit the next phase
		boolean valid = false;
		clearScreen();
		do {
			try {
				System.out.println("Select the side to place:");
				System.out.println("1 - Swap card side");
				System.out.println("2 - Place card");
				System.out.println("3 - go back");
				System.out.println(player.selectCard(index).toStringVisibleSide());
				int input = Integer.parseInt(consoleReader.readLine());
				switch(input) {
				case 1:
					player.selectCard(index).swapSide();
					break;
				case 2:
					if(!placeCard(player,index)) {
						valid = true;
					}
					break;
				case 3:
					return true;
				default:
					throw new IllegalArgumentException("Unexpected value: " + input);
				}
			}catch(Exception e) {
				clearScreen();
				printLineColor("Invalid operation! please select a valid option!", ANSI_RED);
			}
		}while(!valid);
		return false;
	}

	public static boolean placeCard(Player player,int index) {//true: go back, false: continue whit the next phase
		boolean valid = false;
		clearScreen();
		do {
			try {
				System.out.println("Select where you want to place the card(x,y) or type -1 (in x or y) to go back:");
				System.out.println(player.getMap().toString());
				System.out.println(player.selectCard(index).toStringVisibleSide());
				int x = Integer.parseInt(consoleReader.readLine());
				if(x == -1) {
					return true;
				}
				int y = Integer.parseInt(consoleReader.readLine());
				if(y == -1) {
					return true;
				}
				player.playCard(x,y,index);
				valid = true;
			}catch(Exception e) {
				clearScreen();
				printLineColor("Invalid operation! please select a valid option!", ANSI_RED);
			}
		}while(!valid);
		return false;
	}

	public static void drawPhase(Game game) {
		boolean valid = false;
		clearScreen();
		do {
			try {
				System.out.println("Draw from:");
				System.out.println("1 - Resource deck");
				System.out.println("2 - Gold deck");
				System.out.println("3 - Table cards");
				System.out.println(game.getTable().toString());
				int input = Integer.parseInt(consoleReader.readLine());
				switch(input) {
				case 1:
					game.drawCard(game.getCurrentPlayer(), CardType.RESOURCE);
					valid = true;
					break;
				case 2:
					game.drawCard(game.getCurrentPlayer(), CardType.GOLD);
					valid = true;
					break;
				case 3:
					if(!drawFromTable(game)) {
						valid = true;
					}else {
						clearScreen();
					}
				}
			}
			catch(Exception e) {
				clearScreen();
				printLineColor("Invalid operation! please select a valid option!", ANSI_RED);
			}
		}while(!valid);
	}

	public static boolean drawFromTable(Game game) {//true: go back, false: continue whit the next phase
		boolean valid = false;
		clearScreen();
		do {
			try {
				System.out.println("Select Card to Draw (1-4) or type -1 to go back:");
				System.out.println(game.getTable().toString());
				int input = Integer.parseInt(consoleReader.readLine());
				if(input == -1) {
					return true;
				}
				game.drawCardFormTable(input-1);
				valid = true;
			}catch(Exception e) {
				clearScreen();
				printLineColor("Invalid operation! please select a valid option!", ANSI_RED);
			}
		}while(!valid);
		return false;
	}

}
