package base;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import java.lang.reflect.Type;
import java.util.*;
public class Card {
	private int id;
	private static int COUNTER=0;
	private Symbol cardColor;
	private Side side [];//0:front, 1:back
	private CardType cardType;
	private int visibleSide;
	
	
	public Card(Symbol cardColor, Side front, Side back, CardType cardType) {
		this.id=COUNTER;
		COUNTER++;
		this.cardColor = cardColor;
		this.cardType = cardType;
		side = new Side [2];
		side[0]=front;
		side[1]=back;
	}
	
	public MapCard getMapCard(int zIndex) {
		return new MapCard(this,zIndex);
	}

	public Symbol getCardColor() {
		return cardColor;
	}

	public void setCardColor(Symbol cardColor) {
		this.cardColor = cardColor;
	}

	public Side getSide(int index) {
		return side[index];
	}

	public void setSide(Side[] side) {
		this.side = side;
	}

	public int getId() {
		return id;
	}

	public CardType getCardType() {
		return cardType;
	}

	public void setCardType(CardType cardType) {
		this.cardType = cardType;
	}

	public Side getVisibleSide() {
		return side[visibleSide];
	}

	public void swapSide() {
		
		if(this.getCardType() != CardType.OBJECTIVE && this.getSide((visibleSide+1)%2) != null) {
			visibleSide = (visibleSide+1)%2; 
		}
	}
	
	public boolean placeableCard() {
		if(this.getCardType() == CardType.OBJECTIVE) {
			return false;
		}else {
			return true;
		}
	}
	
	public String toString() {
		return "["+" id:"+id+" colore:"+cardColor+" tipo:"+cardType+" front:"+side[0].toString()+" back:"+side[1].toString()+"]\n";
	}
	
	public static class CardJsonAdapte implements  JsonDeserializer<Card>{

		@Override
		public Card deserialize(JsonElement elem, Type type, JsonDeserializationContext context) throws JsonParseException {
			JsonObject jObject = elem.getAsJsonObject();
			CardType cardType = CardType.valueOf(jObject.get("cardType").getAsString());
			Symbol cardColor = Symbol.valueOf(jObject.get("cardColor").getAsString());
			JsonObject jFrontSide = jObject.get("frontSide").getAsJsonObject();
			//front side preparation:
			Side front;
			int pointValue = jFrontSide.get("pointValue").getAsInt();
			Symbol [] center = new GsonBuilder().create().fromJson(jFrontSide.get("center"),Symbol[].class);
			Symbol [] placeRequirement = new GsonBuilder().create().fromJson(jFrontSide.get("placeRequiremenmts"),Symbol[].class);
			
			String tmpCorners[] = new GsonBuilder().create().fromJson(jFrontSide.get("corner"),String[].class);//estrai i corners
			Corner[] corners = new Corner[4];
			for(int i=0;i<corners.length;i++) {
				if(!tmpCorners[i].equals("NULL")) {
					corners[i] = new Corner(Symbol.valueOf(tmpCorners[i]));
				}
			}
			
			switch(jFrontSide.get("type").getAsString()) {//crea classe Side per front
				case ("genericSide"):
					front = new GenericSide(new ArrayList<Symbol>(Arrays.asList(center)),corners,pointValue);
					break;
				case ("PlaceRequirementSide"):
					front = new PlaceRequirementSide(new ArrayList<Symbol>(Arrays.asList(center)),corners,pointValue,new ArrayList<Symbol>(Arrays.asList(placeRequirement)));
					break;
				case ("PointResourceRequirementSide"):
					Symbol [] pointRequirement = new GsonBuilder().create().fromJson(jFrontSide.get("pointRequirements"),Symbol[].class);
					front = new PointResourceRequirementSide(new ArrayList<Symbol>(Arrays.asList(center)),corners,pointValue,
							new ArrayList<Symbol>(Arrays.asList(placeRequirement)),new ArrayList<Symbol>(Arrays.asList(pointRequirement)));
					break;
				case ("PointCornerRequirementSide"):
					front = new PointCornerRequirementSide(new ArrayList<Symbol>(Arrays.asList(center)),corners,pointValue,new ArrayList<Symbol>(Arrays.asList(placeRequirement)));
					break;
				case ("PointPositionRequirementSide"):
					String [][] pointRequirementMatrix = new GsonBuilder().create().fromJson(jFrontSide.get("pointRequirements"),String[][].class);
					MapCard [][] tmpMap = new MapCard[pointRequirementMatrix.length][pointRequirementMatrix[0].length];
					Side tmpSide = new GenericSide(null,null,0);
					for(int i=0;i<pointRequirementMatrix.length;i++) {
						for(int j=0;j<pointRequirementMatrix[0].length;j++) {
							if(!pointRequirementMatrix[i][j].equals("NULL")) {
								tmpMap[i][j] = new MapCard (new Card(Symbol.valueOf(pointRequirementMatrix[i][j]),tmpSide,tmpSide,CardType.RESOURCE),0);
							}
						}
					}
					front = new PointPositionRequirementSide(new ArrayList<Symbol>(Arrays.asList(center)),corners,pointValue,new ArrayList<Symbol>(Arrays.asList(placeRequirement)),tmpMap);
					break;
				default:
					front = new GenericSide(null,null,0);
					
			}
			
			JsonObject jBackSide = jObject.get("backSide").getAsJsonObject();
			Side back;
			pointValue = jBackSide.get("pointValue").getAsInt();
			center = new GsonBuilder().create().fromJson(jBackSide.get("center"),Symbol[].class);
			tmpCorners = new GsonBuilder().create().fromJson(jFrontSide.get("corner"),String[].class);//estrai i corners
			corners = new Corner[4];
			for(int i=0;i<corners.length;i++) {
				if(!tmpCorners[i].equals("NULL")) {
					corners[i] = new Corner(Symbol.valueOf(tmpCorners[i]));
				}
			}
			back = new GenericSide(new ArrayList<Symbol>(Arrays.asList(center)),corners,pointValue);
			
			Card finishCard = new Card(cardColor,front,back,cardType);
			System.out.println(finishCard.toString());
			return finishCard;
		}
		
	}
	
}
