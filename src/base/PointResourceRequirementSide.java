package base;

import java.util.List;

public class PointResourceRequirementSide extends PlaceRequirementSide {

	private List<Symbol> pointRequirement;
	/**
	 * creates a PlaceRequirementSide from the center, corners, point value, a List of Symbol required to place the side and a List of Symbol need to score points
	 * @param center
	 * @param corners
	 * @param pointValue
	 * @param placeRequirements
	 * @param pointRequirement
	 */
	public PointResourceRequirementSide(List<Symbol> center, Corner[] corners,int pointValue, List<Symbol> placeRequirements ,List<Symbol> pointRequirement){
		super(center,corners,pointValue,placeRequirements);
		this.pointRequirement=pointRequirement;
	}
	/**
	 * creates a PlaceRequirementSide from the center, corners, point value, a List of Symbol required to place the side and a List of Symbol need to score points
	 * @param center
	 * @param cornerTopLeft
	 * @param cornerTopRight
	 * @param cornerBottomLeft
	 * @param cornerBottomRight
	 * @param pointValue
	 * @param placeRequirements
	 * @param pointRequirements
	 */
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
		
		StringBuilder sb = new StringBuilder();
		
		sb.append(super.toString());

		sb.append("Points Requirement ");
		sb.append(pointRequirement);
		sb.append(" ");
		
		return sb.toString();
	}

}
