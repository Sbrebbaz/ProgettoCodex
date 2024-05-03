package base;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class Map {

	private final int xSize = 80;
	private final int ySize = 80;
	private MapCard[][] grid;
	private Point subMap[];

	public Map() {
		grid = new MapCard[xSize][ySize];
		for(int i=0;i<2;i++) {
			subMap[i] = new Point(xSize/2,ySize/2);
		}
	}

	public void placeStartingCard(Card card) throws Exception{//piazza la carta iniziale al centro (40,40)
		if( card!=null && card.getCardType() == CardType.STARTING) {
			grid[xSize/2][ySize/2] = card.getMapCard(0);
			updateSubMap(xSize/2,ySize/2);
		}else {
			throw new Exception();
		}
	}

	private boolean outOfIndex(int x,int y) {// ritorna vero se il punto si trova fuori dalla griglia, false se no
		if(x>=xSize || y>=ySize || x<0 || y<0) {
			return true;
		}
		return false;
	}

	/**
	 * place a card in in the map, return the number of point score placing the card
	 * @param x
	 * @param y
	 * @param card
	 * @return
	 * @throws Exception
	 * @throws IndexOutOfBoundsException
	 */
	public int placeCard(int x, int y, Card card) throws Exception,IndexOutOfBoundsException {
		if(outOfIndex(x,y)) {//controlla se le coordinate si trovano nella griglia
			throw new IndexOutOfBoundsException();
		}
		if(card!=null && card.getCardType() != CardType.STARTING) {// controlla se è una carta di tipo accettabile 
			if(checkCardPosition(x,y,card) && card.getVisibleSide().verifyCondition(this)) {//controlla se in quella posizione si puo piazzare una carta e che abbia i requisiti base per essere piazzata

				grid[x][y] = new MapCard(card,getZindexMapCard(x,y));//piazza la carta nel punto
				updateSubMap(x,y);//aggionra la sotto matrice con tutte le carte dentro
				return card.getVisibleSide().getPoints(this, x, y);
			}else {
				throw new Exception();
			}
		}else {
			throw new Exception();
		}
	}

	public boolean checkCardPosition(int x,int y,Card card) throws Exception {
		boolean tmpCorner = false;//flag per indicare se si è trovato una carta con angolo alle diagonali
		if(outOfIndex(x,y)) {
			return false;
		}
		for(int i=-1;i<1;i+=2) {
			for(int j=-1;j<1;j+=2) {

				if(!outOfIndex(x+i,y+j) && grid[x+i][y+j]!=null && grid[x+i][y+j].getCard() != null) {
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

	private int getZindexMapCard(int x,int y) {// ritorna lo zIndex  massimo +1  delle carte collegate da un corner
		int max=Integer.MIN_VALUE;
		for(int i=-1;i<1;i+=2) {
			for(int j=-1;j<1;j+=2) {
				if(!outOfIndex(x+i,y+j) && grid[x+i][y+j]!=null && grid[x+i][y+j].getzIndex() > max) {
					max = grid[x+i][y+j].getzIndex();
				}
			}

		}
		if(max == Integer.MIN_VALUE) {
			return 0;
		}else {
			return max+1;
		}
	}

	private void updateSubMap(int x, int y) {//aggiorna i 2 putni della subMap per renderla più grande dello spazio con carte giocate, usando le coordinate dell'ultima carta piazzata
		for(int i=-1;i<2;i+=2) {
			if(subMap[i].x == x) {
				subMap[i].x += i;
			}
			if(subMap[i].y == y) {
				subMap[i].y += i;
			}
		}
	}

	public int getSymbolCount(Symbol symbol) {//conta il numero di corner visibili con il simbolo cercato nella mappa
		Corner tmp[];//contiene i 4 corner della carta attualmente scansionata
		int count=0;
		for(int i=subMap[0].x+1; i<subMap[1].x ; i++) {// gli indici partono dalla sottomatrice subMap
			for(int j=subMap[0].y+1; j<subMap[1].y ; j++) {
				if(grid[i][j]!=null && grid[i][j].getCard() != null) {
					tmp = grid[i][j].getCard().getVisibleSide().getCorners();
					for(int k=0; k<4 ; k++) {
						if(tmp[k]!= null && tmp[k].getSymbol() == symbol && grid[i][j].getzIndex() > getZindexFromIndexCorner(i,j,k)) {//se il corner esiste, è uguale al simbolo cercato e la carta del corner analizzato..
							count++;																									//... si trova più in alto di quella collegata dal corner aumento il count
						}
					}
				}
			}
		}
		return count;
	}

	private int getZindexFromIndexCorner(int x,int y,int indexCorner) {// per ogni corner ti dice il valore dello zIndex della carta ad esso collegata
		switch(indexCorner) {
		case 0:
			if(outOfIndex(x-1,y-1) == false && grid[x-1][y-1]!=null && grid[x-1][y-1].getCard()!= null) {
				return grid[x-1][y-1].getzIndex();
			}else {
				return -1;
			}
		case 1:
			if(outOfIndex(x-1,y+1) == false && grid[x-1][y+1]!=null && grid[x-1][y+1].getCard()!= null) {
				return grid[x-1][y+1].getzIndex();
			}else {
				return -1;
			}
		case 2:
			if(outOfIndex(x+1,y-1) == false && grid[x+1][y-1]!=null && grid[x+1][y-1].getCard()!= null) {
				return grid[x+1][y-1].getzIndex();
			}else {
				return -1;
			}
		case 3:
			if(outOfIndex(x+1,y+1) == false && grid[x+1][y+1]!=null && grid[x+1][y+1].getCard()!= null) {
				return grid[x+1][y+1].getzIndex();
			}else {
				return -1;
			}
		default:
			return -1;
		}
	}

	public boolean findListSymbol(List<Symbol> symbol) {
		Corner tmp[];//contiene i 4 corner della carta attualmente scansionata
		boolean finish = false;//indica se hai gia finito di cerca i simboli nella lista -> lista vuota
		if(symbol.isEmpty()) {
			return true;
		}
		for(int i=subMap[0].x+1; i<subMap[1].x && finish == false; i++) {// gli indici partono dalla sottomatrice subMap
			for(int j=subMap[0].y+1; j<subMap[1].y && finish == false; j++) {
				
				if(grid[i][j]!=null && grid[i][j].getCard() != null) {
					
					tmp = grid[i][j].getCard().getVisibleSide().getCorners();
					
					for(int k=0; k<4 && finish == false ; k++) {
						
						if(tmp[k]!= null && symbol.contains(tmp[k].getSymbol()) && 
								grid[i][j].getzIndex() > getZindexFromIndexCorner(i,j,k)) {//se il corner esiste, il simbolo esiste nella lista e la carta del corner analizzato..
																							//... si trova più in alto di quella collegata dal corner tolgo il simbolo dalla lista
							symbol.remove(tmp[k].getSymbol());	
							if(symbol.isEmpty()){//se non ci sono altri simboli da cercare esco
								finish = true;
							}
							
						}
					}
					
				}
				
			}
		}
		return finish;
	}

	public int getNearCorner(int x,int y){
		int totalCorner=0;//numero totale di conrner adiacenti
		for(int i=-1;i<1;i+=2) {
			for(int j=-1;j<1;j+=2) {

				if(!outOfIndex(x+i,y+j) && grid[x+i][y+j]!=null && grid[x+i][y+j].getCard() != null) {//se esiste una carta in quel corner
					totalCorner++;//aumento il count del corner
				}

			}	
		}
		return totalCorner;
	}

	public int findSubMap(MapCard[][] positionRequirement) {
		int count =0;
		List<Integer> usedCard = new ArrayList<Integer>();
		for(int i=subMap[0].x+1; i<subMap[1].x ; i++) {//per tutta la subMap...
			for(int j=subMap[0].y+1; j<subMap[1].y ; j++) {
				if(isSubMapEquals(i,j,positionRequirement,usedCard)) {//conto le sotto matrice di griglia uguale ai requisiti allora
					count++;
				}
			}
		}
		return count;
	}
	
	private boolean isSubMapEquals(int x, int y, MapCard[][] positionRequirement,List<Integer> usedCard) {
		
		boolean isEquals = true;//indica se le 2 sotto matrici sono uguali, rimane vero anche se in una cella di grid c'è una carta ma in positionRequirement non c'è in una certa posizione
		List<Integer> tmpUsedCard = new ArrayList<Integer>();
		if(positionRequirement == null)
			return false;
		
		if(outOfIndex(x+positionRequirement[0].length,y+positionRequirement.length)) {//se la sotto matrice uscirebbe dalla grid...
			return false;//...dimensioni diversi quindi diverse
		}
		
		for(int i=x ; i<x+positionRequirement[0].length && isEquals; i++) {
			for(int j=y; j<y+positionRequirement.length && isEquals; j++) {
				
				if(outOfIndex(x+i,y+j)) {//se esco dalla grid vado alla prossima riga
					break;
				}
				
				if(grid[x+i][y+j]!=null && grid[x+i][y+j].getCard() != null && positionRequirement[i][j] != null) {// se in entrambe le sotto matrice c'è una carta...
					if(grid[x+i][y+j].getCard().getCardType() != positionRequirement[i][j].getCard().getCardType() || usedCard.contains(grid[x+i][y+j].getCard().getId())) {//... e sono di tipo diverso o è una carta gia usata
						isEquals = false;//le 2 sotto matrici non sono uguali
					}else {
						tmpUsedCard.add(grid[x+i][y+j].getCard().getId());
					}
					
				}else {//se no...
					if(grid[x+i][y+j]!=null &&  grid[x+i][y+j].getCard() == null && positionRequirement[i][j] != null ) {//... se esiste una carta in positionRequirement, ma non i grid in una certa posizione...
						isEquals = false;//... le 2 sotto matrici non sono uguali
					}
				}
				
			}
		}
		if(isEquals == true) {
			usedCard.addAll(tmpUsedCard);
		}
		return isEquals;
	}

	public int getxSize() {
		return xSize;
	}

	public int getySize() {
		return ySize;
	}
	
	
}
