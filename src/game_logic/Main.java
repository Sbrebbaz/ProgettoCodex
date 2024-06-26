package game_logic;

import java.util.ArrayList;

import game_components.Player;

public class Main {

	//
	public static void main(String[] args) {
		int playerCount = -1;
		ArrayList<Player> players = new ArrayList<Player>();

		try {
			//Read card configuration from file
			CardUtility.readCardsFromFile();
							

			while(UIUtility.mainMenuManagement()) {
				
				players = new ArrayList<Player>();
				playerCount = UIUtility.playerSelectionManagement();

				for(int i = 0;i < playerCount; i++) {
					players.add(UIUtility.playerCreation());
				}

				UIUtility.clearScreen();
				System.out.println("Players: ");
				for(Player player : players) {
					UIUtility.printPlayer(player);
				}
				
				new Game(players).play();					
			}			
		}
		catch(Exception e) {
			UIUtility.printLineColor("There was a fatal problem while processing the latest action: " + e.getMessage(), UIUtility.ANSI_RED);
			UIUtility.printLineColor("Stacktrace: " + e.getStackTrace(), UIUtility.ANSI_RED);
		}
	}







}
