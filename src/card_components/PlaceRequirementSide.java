package card_components;

import java.util.List;

import enumeration.Symbol;
import game_components.Map;

public class PlaceRequirementSide extends GenericSide {

    private final List<Symbol> placeRequirements;
    /**
     * creates a PlaceRequirementSide from the center, corners, point value and a List of Symbol required to place the side
     * @param center
     * @param corners
     * @param pointValue
     * @param placeRequirements
     */
    public PlaceRequirementSide(List<Symbol> center, Corner[] corners,int pointValue, List<Symbol> placeRequirements) {
        super(center, corners,pointValue);
        this.placeRequirements = placeRequirements;
    }
    /**
     * creates a PlaceRequirementSide from the center, corners, point value and a List of Symbol required to place the side
     * @param center
     * @param cornerTopLeft
     * @param cornerTopRight
     * @param cornerBottomLeft
     * @param cornerBottomRight
     * @param pointValue
     * @param placeRequirements
     */
    public PlaceRequirementSide(List<Symbol> center, Corner cornerTopLeft, Corner cornerTopRight,
                                Corner cornerBottomLeft, Corner cornerBottomRight,int pointValue, List<Symbol> placeRequirements) {
        super(center, cornerTopLeft, cornerTopRight, cornerBottomLeft, cornerBottomRight,pointValue);
        this.placeRequirements = placeRequirements;
    }
    @Override
    public boolean verifyCondition(Map map) {
        return map.findListSymbol(placeRequirements);
    }

    @Override
    public String toString() {
		StringBuilder sb = new StringBuilder();
		
		sb.append(super.toString());

		sb.append("Place Requirement ");
		sb.append(placeRequirements);
		sb.append(" ");
		
		return sb.toString();
    }
}
