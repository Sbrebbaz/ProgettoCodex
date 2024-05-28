package card_components;

import java.util.List;

import enumeration.Symbol;
import game_components.Map;

public class PointCornerRequirementSide extends PlaceRequirementSide {

    
    public PointCornerRequirementSide(List<Symbol> center, Corner[] corners,int pointValue, List<Symbol> placeRequirements) {
        super(center, corners,pointValue, placeRequirements);
        // TODO Auto-generated constructor stub
    }
    public PointCornerRequirementSide(List<Symbol> center, Corner cornerTopLeft, Corner cornerTopRight,
                                      Corner cornerBottomLeft, Corner cornerBottomRight,int pointValue, List<Symbol> placeRequirements) {
        super(center, cornerTopLeft, cornerTopRight, cornerBottomLeft, cornerBottomRight,pointValue, placeRequirements);
        // TODO Auto-generated constructor stub
    }

    @Override
    public int getPoints(Map map, int x, int y) {
        return this.getPointValue() * map.getNearCorner(x, y);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        
        sb.append(super.toString());
        
        sb.append("[Corner Requirement]");
        
        return sb.toString();
    }
}
