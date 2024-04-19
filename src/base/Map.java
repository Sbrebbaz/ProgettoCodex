package base;

import java.awt.Point;
import java.util.List;

public class Map {

	private final int xSize = 80;
	private final int ySize = 80;
	private MapCard[][] grid;
	private Player player;
	private Point subMap[];
	
	public Map(Player player) {
		this.player = player;
		grid = new MapCard[xSize][ySize];
		for(int i=0;i<2;i++) {
			subMap[i] = new Point(xSize/2,ySize/2);
		}
	}
	
	public void placeStartingCard(Card card) throws Exception{
		if(card.getCardType() == CardType.STARTING) {
			grid[xSize/2][ySize/2] = card.getMapCard(0);
			updateSubMap(xSize/2,ySize/2);
		}else {
			throw new Exception();
		}
	}
	
	public void placeCard(int x, int y, Card card) throws Exception {
		if(card.getCardType() != CardType.STARTING && card.getCardType() != CardType.OBJECTIVE) {// controlla se è una carta di tipo accettabile
			if(checkCardPosition(x,y,card)) {
				
				grid[x][y] = new MapCard(card,getZindexMapCard(x,y));
				updateSubMap(x,y);
				
			}else {
				throw new Exception();
			}
		}else {
			throw new Exception();
		}
	}
	
	public boolean checkCardPosition(int x,int y,Card card) throws Exception {
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
	
	public void updateSubMap(int x, int y) {
		for(int i=-1;i<2;i+=2) {
			if(subMap[i].x == x) {
				subMap[i].x += i;
			}
			if(subMap[i].y == y) {
				subMap[i].y += i;
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
}
