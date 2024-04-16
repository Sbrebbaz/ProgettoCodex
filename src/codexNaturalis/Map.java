package Base;

import java.util.List;

public class Map {

	private final int xSize = 80;
	private final int ySize = 80;
	private MapCard[][] grid;
	private Player player;
	
	public Map(Player player) {
		this.player = player;
		grid = new MapCard[xSize][ySize];
	}
	
	public void placeStartingCard(Card card) throws Exception{
		if(card.getCardType() == CardType.STARTING) {
			grid[xSize/2][ySize/2] = card.getMapCard(0);
		}else {
			throw new Exception();
		}
	}
	
	public void placeCard(int x, int y, Card card) throws Exception {
		if(card.getCardType() != CardType.STARTING && card.getCardType() != CardType.OBJECTIVE) {
			if(checkCardPosition(x,y,card)) {
				
				grid[x][y] = new MapCard(card,getZindexMapCard(x,y));
				
			}else {
				throw new Exception();
			}
		}else {
			throw new Exception();
		}
	}
	
	public boolean checkCardPosition(int x,int y,Card card) throws Exception {
		//boolean tmpPass = true;//flag per indicare se la carta ha passato i criteri di piazzamento
		boolean tmpCorner = false;//flag per indicare se si è trovato una carta con angolo alle diagonali
		for(int i=-1;i<1;i+=2) {
			for(int j=-1;j<1;j+=2) {
				
				if(grid[x+i][y+j].getCard() != null) {
					if(grid[x+i][y+j].getCard().getVisibleSide().getCornerFromPosition(i,j) == null) {
						return false;
					}else {
						tmpCorner = true;
					}
				}
				
			}
				
		}
		return tmpCorner;
	}
	
	private int getZindexMapCard(int x,int y) {
		int max=Integer.MIN_VALUE;
		for(int i=-1;i<1;i+=2) {
			for(int j=-1;j<1;j+=2) {
				if(grid[x+i][y+j].getzIndex() > max) {
					max = grid[x+i][y+j].getzIndex();
				}
			}
				
		}
		return max+1;
	}
	

}
