package base;

import java.util.Collections;
import java.util.List;

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
}
