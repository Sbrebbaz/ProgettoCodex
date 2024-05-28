package game_logic;

import java.util.Collections;
import java.util.List;

import card_components.Card;
import enumeration.CardType;
import game_components.Player;
import game_components.Table;

public class Game {
	private List<Player> players;
	private Table table;
	private int turnCounter;
	/**
	 * construction for the Game, create a new game from a List of Player
	 * @param players
	 */
	public Game(List<Player> players) {
		this.players=players;
		Collections.shuffle(players);
		turnCounter = 0;
		this.table=new Table();
	}
	/**
	 * start the game
	 * @throws Exception, cast if a critical error occurs
	 */
	public void play() throws Exception{
		
		do {
			for(int i=0 ; i<getNumberOfPlayer();i++) {// set secret and starting card
	
				for(int j=0; j<2;j++) {
					drawCard(getCurrentPlayer(), CardType.OBJECTIVE);
				}
	
				UIUtility.setSecretObjectiveCard(getCurrentPlayer());
	
				drawCard(getCurrentPlayer(), CardType.STARTING);
				UIUtility.setStartingCard(getCurrentPlayer());
	
				drawCard(getCurrentPlayer(), CardType.RESOURCE);
				drawCard(getCurrentPlayer(), CardType.RESOURCE);
				drawCard(getCurrentPlayer(), CardType.GOLD);
	
				playNextTurn();
			}
	
			while(!verifyWinningCondition()) {					
				playTurn(true);
			}
	
			//Play LAST TURN
			for(int i=0 ; i<getNumberOfPlayer();i++) {
				playTurn(false);
			}
			//calculate objective points points
			for(int i=0 ; i<getNumberOfPlayer();i++) {
				calculatePlayerPoint(getCurrentPlayer());
				playNextTurn();
			}
	
			UIUtility.clearScreen();
			UIUtility.printScoreboard(this);
			UIUtility.WaitForPlayerInput();
		}while(UIUtility.playAnotherGame(this));
	}
	/**
	 * loads the default configurations
	 */
	public void restart() {
		Collections.shuffle(players);
		turnCounter = 0;
		this.table=new Table();
		for(Player player:players) {
			player.restart();
		}
	}
	/**
	 * draw a card from the table deck
	 * @param index
	 * @return
	 * @throws Exception
	 */
	public Card drawCardFormTable(int index) throws Exception{
		return table.drawTableCard(index);
	}
	/**
	 * play the turn for a player
	 * @param flagForDraw indicate if the player can draw or not
	 */
	public void playTurn(boolean flagForDraw) {//if set to true you can draw, else you can't
		UIUtility.clearScreen();

		UIUtility.printPlayer(getCurrentPlayer());
		do {
			UIUtility.playerTurnBase(this);
		}while(UIUtility.selectCardToPlace(getCurrentPlayer()));

		//Draw card or go back to end turn
		if(!this.getTable().isEmpty() && flagForDraw) {
			UIUtility.drawPhase(this);
		}				
		playNextTurn();	
	}	

	/**
	 * wheel players play the game
	 */
	public void playNextTurn(){
		turnCounter = (turnCounter+1)%players.size();
	}
	/**
	 * get the player playing the turn
	 * @return
	 */
	public Player getCurrentPlayer() {
		return players.get(turnCounter);
	}
	/**
	 * draw a card from the Type of the deck and give it to the indicates player
	 * @param player
	 * @param cardType
	 * @throws Exception
	 */
	public void drawCard(Player player, CardType cardType) throws Exception {
		player.DrawCard(table.drawCard(cardType));
	}
	/**
	 * returns a list of the winning players
	 * @return
	 */
	public List<Player> getWinningPlayers(){
		List<Player> tmp = players;
		Collections.sort(tmp);// non sono sicuro che sorti dal max al min
		for(int i=0; i<tmp.size()-1;i++) {
			if(!tmp.get(i).equals(tmp.get(i+1))) {
				return tmp.subList(0, i+1);
			}
		}
		return null;
	}
	/**
	 * calculates the points taken by the player from the objective cards
	 * @param player
	 */
	public void calculatePlayerPoint(Player player) {
		int addObjectivePoints = 0;
		for(int i = 0; i<table.getN_OBJECTIVE_TABLE_CARDS(); i++) {
			addObjectivePoints += table.getTableObjectiveCards(i).getVisibleSide().getPoints(player.getMap(), 0, 0);
		}
		addObjectivePoints += player.getSecretObjective().getVisibleSide().getPoints(player.getMap(), 0, 0);
		player.AddPoints(addObjectivePoints);
	}
	/**
	 * checks if a player has reached the winning requirements or if there are no more cards in the decks
	 * @return true if they have been achieved, false if not
	 */
	public boolean verifyWinningCondition() {
		for(Player player:players) {
			if(player.winningPoints()) {
				return true;
			}
		}
		return table.isEmpty();
	}
	/**
	 * returns the number of player
	 * @return
	 */
	public int getNumberOfPlayer() {
		return players.size();
	}
	/**
	 * get all players
	 * @return a List of Player
	 */
	public List<Player> getPlayers() {
		return players;
	}
	/**
	 * get the table
	 * @return
	 */
	public Table getTable() {
		return table;
	}
	/**
	 * returns a String with all informations of the game
	 */
	@Override
	public String toString() {
		return "Game{" +
				"players=" + players +
				", table=" + table +
				'}';
	}
}
