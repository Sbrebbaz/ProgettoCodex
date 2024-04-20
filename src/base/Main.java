package base;

import ui.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import base.Player;

public class Main {

	
	public static void main(String[] args) {
		
		List<Player> players = new ArrayList<Player>();
				
		System.out.println("GAME START");

		try {

			MainMenu mainMenuWindow = new MainMenu();
			System.out.println("MAIN MENU WINDOW OPENED");
			mainMenuWindow.open();
			System.out.println("MAIN MENU WINDOW CLOSED");
			
			if (mainMenuWindow.isStartGame()) {
				System.out.println("START GAME");

				PlayerCountSelection playerCountSelectionWindow = new PlayerCountSelection();
				System.out.println("PLAYER COUNT SELECTION OPENED");
				playerCountSelectionWindow.open();

				int playerCount = playerCountSelectionWindow.getPlayerCount();
				for (int i = 0; i < playerCount; i++) {

					PlayerCreation  playerCreationWindow = new PlayerCreation(i);
					playerCreationWindow.open();
					players.add(playerCreationWindow.getPlayer());					
					
					System.out.println("ADDED NEW PLAYER : " + playerCreationWindow.getPlayer().toString());
					
				}
			}

		} catch (Exception e) {
			System.out.println("EXCEPTION MESSAGE: " + e.getMessage());
			System.out.println("EXCEPTION STACKTRACE: " + e.getStackTrace());
		}

		System.out.println("GAME CLOSE");
	}

}