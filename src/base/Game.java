package base;

import java.util.Collections;
import java.util.List;

import javax.swing.plaf.synth.Region;

public class Game {
	private List<Player> players;
	private Table table;
	private int turnCounter;
	public Game(List<Player> players) {
		this.players=players;
		Collections.shuffle(players);
		turnCounter = 0;
		this.table=new Table();
	}
	
	public void play() throws Exception{
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
									
			UIUtility.clearScreen();
			
			UIUtility.printPlayer(getCurrentPlayer());
			do {
				UIUtility.playerTurnBase(this);
			}while(UIUtility.selectCardToPlace(getCurrentPlayer()));
			
			
			
			//Draw card or go back to end turn
			if(!this.getTable().isEmpty()) {
				UIUtility.drawPhase(this);
			}				
			playNextTurn();			
		}
		
		//Play LAST TURN
		
		
		
		UIUtility.printScoreboard(this);
	}
	
	public Card drawCardFormTable(int index) throws Exception{
		return table.drawTableCard(index);
	}
	
	
	
	
	public void playNextTurn(){
		turnCounter = (turnCounter+1)%players.size();
	}
	
	public Player getCurrentPlayer() {
		return players.get(turnCounter);
	}
	
	public void drawCard(Player player, CardType cardType) throws Exception {
		player.DrawCard(table.drawCard(cardType));
	}
	
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
	
	public void calculatePlayerPoint(Player player) {
		int addObjectivePoints = 0;
		for(int i = 0; i<table.getN_OBJECTIVE_TABLE_CARDS(); i++) {
			addObjectivePoints += table.getTableObjectiveCards(i).getVisibleSide().getPoints(player.getMap(), 0, 0);
		}
		addObjectivePoints += player.getSecretObjective().getVisibleSide().getPoints(player.getMap(), 0, 0);
		player.AddPoints(addObjectivePoints);
	}
	
	public boolean verifyWinningCondition() {
		for(Player player:players) {
			if(player.winningPoints()) {
				return true;
			}
		}
		return table.isEmpty();
	}
	
	public int getNumberOfPlayer() {
		return players.size();
	}

	public List<Player> getPlayers() {
		return players;
	}
	
	public Table getTable() {
		return table;
	}
	
	@Override
	public String toString() {
		return "Game{" +
				"players=" + players +
				", table=" + table +
				'}';
	}
}
