package game_components;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import card_components.Card;
import card_components.Corner;
import enumeration.CardType;
import enumeration.Symbol;

public class Map {

	private final int xSize = 82;
	private final int ySize = 82;
	private MapCard[][] grid;

	private Point subMap[];
	/**
	 * construct a new map
	 */
	public Map() {
		grid = new MapCard[xSize][ySize];
		subMap = new Point[2];
		for (int i = 0; i < 2; i++) {
			subMap[i] = new Point((xSize / 2)-1, (ySize / 2)-1);
		}
	}
	/**
	 * place the starting card at the center of the map
	 * @param card
	 * @throws Exception
	 */
	public void placeStartingCard(Card card) throws Exception {//piazza la carta iniziale al centro (40,40)
		if (card != null && card.getCardType() == CardType.STARTING) {
			grid[(xSize / 2)-1][(ySize / 2)-1] = card.getMapCard(0);// centro matrice (1-80) = 80/2, centro indice matrice (0-79) = (80/2)-1
			updateSubMap((xSize / 2)-1, (ySize / 2)-1);
		} else {
			throw new Exception();
		}
	}
	/**
	 * find if the coordinates are outside of the map
	 * @param x
	 * @param y
	 * @return true if they are outside, false if not
	 */
	private boolean outOfIndex(int x, int y) {// ritorna vero se il punto si trova fuori dalla griglia, false se no
		if (x >= xSize || y >= ySize || x < 0 || y < 0) {
			return true;
		}
		return false;
	}

	/**
	 * place a card in in the map, return the number of point score placing the card
	 *
	 * @param x
	 * @param y
	 * @param card
	 * @return
	 * @throws Exception
	 * @throws IndexOutOfBoundsException
	 */
	public int placeCard(int x, int y, Card card) throws Exception, IndexOutOfBoundsException {
		if (outOfIndex(x, y)) {//controlla se le coordinate si trovano nella griglia
			throw new IndexOutOfBoundsException();
		}
		if (card != null && card.getCardType() != CardType.STARTING && card.placeableCard()) {// controlla se è una carta di tipo accettabile
			if (checkCardPosition(x, y, card) && card.getVisibleSide().verifyCondition(this)) {//controlla se in quella posizione si puo piazzare una carta e che abbia i requisiti base per essere piazzata

				grid[x][y] = new MapCard(card, getZindexMapCard(x, y));//piazza la carta nel punto
				updateSubMap(x, y);//aggionra la sotto matrice con tutte le carte dentro
				return card.getVisibleSide().getPoints(this, x, y);
			} else {
				throw new Exception();
			}
		} else {
			throw new Exception();
		}
	}
	/**
	 * checks if the coordinates entered to place the card are correct
	 * @param x
	 * @param y
	 * @param card
	 * @return true if you can place, false if not
	 * @throws Exception
	 */
	public boolean checkCardPosition(int x, int y, Card card) throws Exception {
		boolean tmpCorner = false;//flag per indicare se si è trovato una carta con angolo alle diagonali
		if (outOfIndex(x, y)) {
			return false;
		}
		for (int i = -1; i <= 2; i += 2) {
			for (int j = -1; j <= 2; j += 2) {

				if (!outOfIndex(x + i, y + j) && grid[x + i][y + j] != null && grid[x + i][y + j].getCard() != null) {
					if (grid[x + i][y + j].getCard().getVisibleSide().getCornerFromPosition(i, j) == null) {
						return false;
					} else {
						tmpCorner = true;
					}
				}

			}

		}
		return tmpCorner;
	}
	/**
	 * get the maximum zIndex +1 of the cards connected by a corner
	 * @param x
	 * @param y
	 * @return
	 */
	private int getZindexMapCard(int x, int y) {// ritorna lo zIndex  massimo +1  delle carte collegate da un corner
		int max = Integer.MIN_VALUE;
		for (int i = -1; i <= 2; i += 2) {
			for (int j = -1; j <= 2; j += 2) {
				if (!outOfIndex(x + i, y + j) && grid[x + i][y + j] != null && grid[x + i][y + j].getzIndex() > max) {
					max = grid[x + i][y + j].getzIndex();
				}
			}

		}
		if (max == Integer.MIN_VALUE) {
			return 0;
		} else {
			return max + 1;
		}
	}
	/**
	 * update the subMap to ensure that all cards are within the sub-matrix 
	 * @param x
	 * @param y
	 */
	private void updateSubMap(int x, int y) {//aggiorna i 2 punti della subMap per renderla più grande dello spazio con carte giocate, usando le coordinate dell'ultima carta piazzata
		int subMapValue= -1;
		for (int i = 0; i < 2; i ++) {
			if (subMap[i].x == x) {
				subMap[i].x += subMapValue;
			}
			if (subMap[i].y == y) {
				subMap[i].y += subMapValue;
			}
			subMapValue +=2;
		}
	}
	/**
	 * get number of time the set of symbol is found 
	 * @param symbol
	 * @return
	 */
	public int getSetSymbolCount(List<Symbol> symbol) {//conta il numero di corner visibili con il simbolo cercato nella mappa
		List<Symbol> allCorners = new ArrayList<Symbol>(getAllVisibleSymbol(symbol));
		int count = 0;
		if (symbol.isEmpty()) {
			return 0;
		}
		while(allCorners.containsAll(symbol)) {
			count++;
			for(Symbol tmpSymbol:symbol) {
				allCorners.remove(tmpSymbol);
			}
		}
		return count;
	}
	/**
	 * get all visible symbol in the map
	 * @param symbol
	 * @return
	 */
	public ArrayList<Symbol> getAllVisibleSymbol(List<Symbol> symbol) {
		Corner tmp[];//contiene i 4 corner della carta attualmente scansionata
		ArrayList<Symbol> symbolFound = new ArrayList<Symbol>();
		
		for (int i = subMap[0].x + 1; i < subMap[1].x; i++) {// gli indici partono dalla sottomatrice subMap
			for (int j = subMap[0].y + 1; j < subMap[1].y; j++) {

				if (grid[i][j] != null && grid[i][j].getCard() != null) {

					tmp = grid[i][j].getCard().getVisibleSide().getCorners();
					if(grid[i][j].getCard().getVisibleSide().getCenter() != null) {
						symbolFound.addAll(grid[i][j].getCard().getVisibleSide().getCenter());
					}
					
					for (int k = 0; k < tmp.length; k++) {

						if (tmp[k] != null && symbol.contains(tmp[k].getSymbol()) &&
								grid[i][j].getzIndex() > getZindexFromIndexCorner(i, j, k)) {//se il corner esiste, non è mai stato usato, il simbolo esiste nella lista e la carta del corner analizzato..
							//... si trova più in alto di quella collegata dal corner aggiungo il corner
							symbolFound.add(tmp[k].getSymbol());
						}
					}

				}

			}
		}
		return symbolFound;
	}
	/**
	 * from the corner get the zIndex value of the card connected to it
	 * @param x
	 * @param y
	 * @param indexCorner
	 * @return -1 if card not found, else the zIndex of the card in the relative position form the corner
	 */
	private int getZindexFromIndexCorner(int x, int y, int indexCorner) {// per ogni corner ti dice il valore dello zIndex della carta ad esso collegata
		switch (indexCorner) {
		case 0:
			if (outOfIndex(x - 1, y - 1) == false && grid[x - 1][y - 1] != null && grid[x - 1][y - 1].getCard() != null) {
				return grid[x - 1][y - 1].getzIndex();
			} else {
				return -1;
			}
		case 1:
			if (outOfIndex(x - 1, y + 1) == false && grid[x - 1][y + 1] != null && grid[x - 1][y + 1].getCard() != null) {
				return grid[x - 1][y + 1].getzIndex();
			} else {
				return -1;
			}
		case 2:
			if (outOfIndex(x + 1, y - 1) == false && grid[x + 1][y - 1] != null && grid[x + 1][y - 1].getCard() != null) {
				return grid[x + 1][y - 1].getzIndex();
			} else {
				return -1;
			}
		case 3:
			if (outOfIndex(x + 1, y + 1) == false && grid[x + 1][y + 1] != null && grid[x + 1][y + 1].getCard() != null) {
				return grid[x + 1][y + 1].getzIndex();
			} else {
				return -1;
			}
		default:
			return -1;
		}
	}
	/**
	 * find if a List of Symbol are present in the map
	 * @param symbol
	 * @return true if they are found, false if not
	 */
	public boolean findListSymbol(List<Symbol> symbol) {
		List<Symbol> allCorners = new ArrayList<Symbol>(getAllVisibleSymbol(symbol));
		if (symbol.isEmpty()) {
			return true;
		}
		return allCorners.containsAll(symbol);
	}
	/**
	 * get the number of near corner connected to the position
	 * @param x
	 * @param y
	 * @return
	 */
	public int getNearCorner(int x, int y) {
		int totalCorner = 0;//numero totale di conrner adiacenti
		for (int i = -1; i <= 2; i += 2) {
			for (int j = -1; j <= 2; j += 2) {

				if (!outOfIndex(x + i, y + j) && grid[x + i][y + j] != null && grid[x + i][y + j].getCard() != null) {//se esiste una carta in quel corner
					totalCorner++;//aumento il count del corner
				}

			}
		}
		return totalCorner;
	}
	/**
	 * get the number of unique subMap are found in the Map
	 * @param positionRequirement
	 * @return
	 */
	public int findSubMap(MapCard[][] positionRequirement) {
		int count = 0;
		List<Integer> usedCard = new ArrayList<Integer>();
		for (int i = subMap[0].x + 1; i < subMap[1].x; i++) {//per tutta la subMap...
			for (int j = subMap[0].y + 1; j < subMap[1].y; j++) {
				if (isSubMapEquals(i, j, positionRequirement, usedCard)) {//conto le sotto matrice di griglia uguale ai requisiti allora
					count++;
				}
			}
		}
		return count;
	}
	/**
	 * find if the subMap are equals
	 * @param x
	 * @param y
	 * @param positionRequirement
	 * @param usedCard
	 * @return true if they are equals, false if not
	 */
	private boolean isSubMapEquals(int x, int y, MapCard[][] positionRequirement, List<Integer> usedCard) {

		boolean isEquals = true;//indica se le 2 sotto matrici sono uguali, rimane vero anche se in una cella di grid c'è una carta ma in positionRequirement non c'è in una certa posizione
		List<Integer> tmpUsedCard = new ArrayList<Integer>();
		if (positionRequirement == null)
			return false;

		if (outOfIndex(x + positionRequirement[0].length, y + positionRequirement.length)) {//se la sotto matrice uscirebbe dalla grid...
			return false;//...dimensioni diversi quindi diverse
		}

		for (int i = 0; i < positionRequirement[0].length && isEquals; i++) {
			for (int j = 0; j < positionRequirement.length && isEquals; j++) {

				if (outOfIndex(x + i, y + j)) {//se esco dalla grid vado alla prossima riga
					break;
				}

				if (grid[x + i][y + j] != null && grid[x + i][y + j].getCard() != null && positionRequirement[i][j] != null) {// se in entrambe le sotto matrice c'è una carta...
					if (grid[x + i][y + j].getCard().getCardType() != positionRequirement[i][j].getCard().getCardType() || usedCard.contains(grid[x + i][y + j].getCard().getId())) {//... e sono di tipo diverso o è una carta gia usata
						isEquals = false;//le 2 sotto matrici non sono uguali
					} else {
						tmpUsedCard.add(grid[x + i][y + j].getCard().getId());
					}

				} else {//se no...
					if (grid[x + i][y + j] != null && grid[x + i][y + j].getCard() == null && positionRequirement[i][j] != null) {//... se esiste una carta in positionRequirement, ma non i grid in una certa posizione...
						isEquals = false;//... le 2 sotto matrici non sono uguali
					}
				}

			}
		}
		if (isEquals == true) {
			usedCard.addAll(tmpUsedCard);
		}
		return isEquals;
	}
	/**
	 * get the rows size of the map
	 * @return
	 */
	public int getxSize() {
		return xSize;
	}
	/**
	 * get the coulums size of the map
	 * @return
	 */
	public int getySize() {
		return ySize;
	}
	/**
	 * returns a String with all informations of the Map
	 */
	@Override
	public String toString() {

		StringBuilder sb = new StringBuilder();
		sb.append("Map:");
		sb.append("\n");
		int mapIndex = 0;
		
		for(int i=subMap[0].x+1;i<subMap[1].x;i++) {
			for(int j=subMap[0].y+1;j<subMap[1].y;j++) {
				sb.append("[");
				if(grid[i][j] != null) {
					if(mapIndex<9) {
						sb.append(" ");
					}
					sb.append(mapIndex);	
					mapIndex++;
				}
				else {
					sb.append("  ");
				}
				sb.append("]");
			}
			sb.append("\n");
		}
		sb.append("\n");
		
		mapIndex = 0;
		
		for(int i=subMap[0].x+1;i<subMap[1].x;i++) {
			for(int j=subMap[0].y+1;j<subMap[1].y;j++) {
				if(grid[i][j] != null) {
					sb.append(mapIndex);
					sb.append(" ~ ");
					sb.append(grid[i][j].toString());
					sb.append(" X: " + i);
					sb.append(" Y: " + j);
					sb.append("\n");
					mapIndex++;
				}
			}
		}

		return sb.toString();
	}
}
