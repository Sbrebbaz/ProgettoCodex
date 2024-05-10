package base;

import java.util.List;

public class PointCornerRequirementSide extends PlaceRequirementSide {

	public PointCornerRequirementSide(List<Symbol> center, Corner[] corners, List<Symbol> placeRequirements) {
		super(center, corners, placeRequirements);
		// TODO Auto-generated constructor stub
	}

	public PointCornerRequirementSide(List<Symbol> center, Corner cornerTopLeft, Corner cornerTopRight,
			Corner cornerBottomLeft, Corner cornerBottomRight, List<Symbol> placeRequirements) {
		super(center, cornerTopLeft, cornerTopRight, cornerBottomLeft, cornerBottomRight, placeRequirements);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public int getPoints(Map map,int x,int y){
		return this.getPointValue() * map.getNearCorner(x, y);
	}

	@Override
	public String toString() {
		return super.toString();
	}
}
