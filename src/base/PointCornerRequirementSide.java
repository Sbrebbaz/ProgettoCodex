package base;

import java.util.List;

public class PointCornerRequirementSide extends PlaceRequirementSide {

    private PointCornerRequirementSide pointCornerRequirementSide;
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
        final StringBuilder sb = new StringBuilder("PointCornerRequirementSide{");
        sb.append("pointCornerRequirementSide=").append(pointCornerRequirementSide);
        sb.append('}');
        return sb.toString();
    }
}
