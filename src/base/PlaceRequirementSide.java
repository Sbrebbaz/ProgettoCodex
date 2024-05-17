package base;

import java.util.List;

public class PlaceRequirementSide extends GenericSide {

    private final List<Symbol> placeRequirements;

    public PlaceRequirementSide(List<Symbol> center, Corner[] corners,int pointValue, List<Symbol> placeRequirements) {
        super(center, corners,pointValue);
        this.placeRequirements = placeRequirements;
    }

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
        return "PlaceRequirementSide{" +
                "side=" + super.toString() +
                ", placeRequirements=" + placeRequirements +
                '}';
    }
}
