package base;

import java.util.Arrays;
import java.util.List;

public class PointPositionRequirementSide extends PlaceRequirementSide {

	private MapCard[][] positionRequirement;
	/**
	 * creates a PlaceRequirementSide from the center, corners, point value, a List of Symbol required to place the side and 
	 * the subMap need to score points
	 * @param center
	 * @param corners
	 * @param pointValue
	 * @param placeRequirements
	 * @param positionRequirement
	 */
	public PointPositionRequirementSide(List<Symbol> center, Corner[] corners,int pointValue, List<Symbol> placeRequirements,
			MapCard[][] positionRequirement) {
		super(center, corners,pointValue, placeRequirements);
		this.positionRequirement = positionRequirement;
	}
	/**
	 * creates a PlaceRequirementSide from the center, corners, point value, a List of Symbol required to place the side and 
	 * the subMap need to score points
	 * @param center
	 * @param cornerTopLeft
	 * @param cornerTopRight
	 * @param cornerBottomLeft
	 * @param cornerBottomRight
	 * @param pointValue
	 * @param placeRequirements
	 * @param positionRequirement
	 */
	public PointPositionRequirementSide(List<Symbol> center, Corner cornerTopLeft, Corner cornerTopRight,
			Corner cornerBottomLeft, Corner cornerBottomRight,int pointValue, List<Symbol> placeRequirements,
			MapCard[][] positionRequirement) {
		super(center, cornerTopLeft, cornerTopRight, cornerBottomLeft, cornerBottomRight,pointValue, placeRequirements);
		this.positionRequirement = positionRequirement;
	}

	public MapCard[][] getPositionRequirement() {
		return positionRequirement;
	}

	public void setPositionRequirement(MapCard[][] positionRequirement) {
		this.positionRequirement = positionRequirement;
	}

	@Override
	public int getPoints(Map map,int x,int y){
		return map.findSubMap(positionRequirement)*this.getPointValue();
	}

	@Override
	public String toString() {
		return "PointPositionRequirementSide{" +
				"placeRequirementSide=" + super.toString() +
				", positionRequirement=" + Arrays.deepToString(positionRequirement) +
				"}\n";
	}
}
