package base;

import java.util.List;

public class PointResourceRequirementSide extends PlaceRequirementSide {

	private List<Symbol> pointRequirement;
	
	public PointResourceRequirementSide(List<Symbol> center, Corner[] corners,int pointValue, List<Symbol> placeRequirements ,List<Symbol> pointRequirement){
		super(center,corners,pointValue,placeRequirements);
		this.pointRequirement=pointRequirement;
	}
	
	public PointResourceRequirementSide(List<Symbol> center,Corner cornerTopLeft,Corner cornerTopRight,
			Corner cornerBottomLeft,Corner cornerBottomRight,int pointValue, List<Symbol> placeRequirements, List<Symbol> pointRequirements) {
		super(center,cornerTopLeft,cornerTopRight,cornerBottomLeft,cornerBottomRight,pointValue,placeRequirements);
		this.pointRequirement=pointRequirements;
	}

	public List<Symbol> getPointRequirement() {
		return pointRequirement;
	}

	public void setPointRequirement(List<Symbol> pointRequirement) {
		this.pointRequirement = pointRequirement;
	}
	
	@Override
	public int getPoints(Map map,int x,int y) {
		int totalPoint = 0;
		for(Symbol symbol: pointRequirement) {
			totalPoint += this.getPointValue() * map.getSymbolCount(symbol);
		}
		return totalPoint;
	}

	@Override
	public String toString() {
		return "PointResourceRequirementSide{" +
				"pointRequirement=" + pointRequirement +
				"placeRequirementSide= "+ super.toString() +
				'}';
	}

}
