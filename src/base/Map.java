package base;

import java.util.List;

public class Map {

	private final int xSize = 80;
	private final int ySize = 80;
	private MapCard[][] grid;
	private Player player;
	private Coordinate subMap[];
	
	public Map(Player player) {
		this.player = player;
		grid = new MapCard[xSize][ySize];
		for(int i=0;i<4;i++) {
			subMap[i] = new Coordinate(xSize/2,ySize/2);
		}
	}
	
	public void placeStartingCard(Card card) throws Exception{
		if(card.getCardType() == CardType.STARTING) {
			grid[xSize/2][ySize/2] = card.getMapCard(0);
		}else {
			throw new Exception();
		}
	}
	
	public void placeCard(int x, int y, Card card) throws Exception {
		if(card.getCardType() != CardType.STARTING && card.getCardType() != CardType.OBJECTIVE) {// controlla se è una carta di tipo accettabile
			if(checkCardPosition(x,y,card)) {//
				
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
	
	public void updateSubMap(int x, int y) throws Exception {
		for(int i=0;i<4;i++) {
			if(subMap[i].x == x) {
				subMap[i].getRowFromIndex(i);
			}
		}
	}

	public boolean verifyCondition(Side side) {
		//TODO implement
		return true;
	}
	
	public int getCardConditionPoints(Side side) {
		//TODO implement
		return -1;
	}
	
	private class Coordinate{
		public int x;
		public int y;
		
		public Coordinate(int x, int y) {
			this.x=x;
			this.y=y;
		}
		
		public int getRowFromIndex(int index) throws Exception{
			if(index == 0 || index == 1) {
				return -1;
			}else if(index == 2 || index == 3) {
				return 1;
			}
			else {
				throw new Exception();
			}
		}
		
		
		public int getColFromIndex(int index) throws Exception {
			if(index == 0 || index == 2) {
				return -1;
			}else if(index == 3 || index == 1) {
				return 1;
			}
			else {
				throw new Exception();
			}
		}
	}

}
