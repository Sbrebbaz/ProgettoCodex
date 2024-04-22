package base;

import java.util.List;

public class Game {
	private List<Player> players;
	private Table table;
	private final int N_STARTING_OBJECTIVE = 2;
	public Game(List<Player> players) {
		this.players=players;
		this.table=new Table();
	}
	
	public void playStartingTurn(Player player) throws Exception{
		Card objective[] = new Card[N_STARTING_OBJECTIVE]; 
		for(int i=0;i<N_STARTING_OBJECTIVE;i++) {
			objective[i] = table.drawCard(CardType.OBJECTIVE);
		}
	}
	
	public void drawCard(Player player, CardType cardType) throws Exception {
		player.DrawCard(table.drawCard(cardType));
	}
	
	public List<Player> getWinningPlayers(){
		List<Player> tmp = players;
		tmp.sort(null);// non sono sicuro che sorti dal max al min
		for(int i=0; i<tmp.size()-1;i++) {
			if(!tmp.get(i).equals(tmp.get(i+1))) {
				return tmp.subList(0, i+1);
			}
		}
		return null;
	}
}
