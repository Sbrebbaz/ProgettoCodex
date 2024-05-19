package base;

import java.util.ArrayList;

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
				players.add(UIUtility.playerCreation());
			}

			for(Player player : players) {
				UIUtility.printLineColor(player.toString(), player.getColor());
			}
			Game game = new Game(players);
			game.play();
		}
		catch(Exception e) {
			UIUtility.printLineColor("There was a fatal problem while processing the latest action: " + e.getMessage(), UIUtility.ANSI_RED);
			UIUtility.printLineColor("Stacktrace: " + e.getStackTrace(), UIUtility.ANSI_RED);
		}
	}







}
