package base;

import java.util.List;

public class PlaceRequirementSide extends GenericSide{

	private List<Symbol> placeRequirements;
	
	public PlaceRequirementSide(List<Symbol> center, Corner[] corners, List<Symbol> placeRequirements){
		super(center,corners);
		this.placeRequirements=placeRequirements;
	}
	
	public PlaceRequirementSide(List<Symbol> center,Corner cornerTopLeft,Corner cornerTopRight,
			Corner cornerBottomLeft,Corner cornerBottomRight, List<Symbol> placeRequirements) {
		super(center,cornerTopLeft,cornerTopRight,cornerBottomLeft,cornerBottomRight);
		this.placeRequirements=placeRequirements;
	}
	
	@Override
	public int getPoints(Map map) {
		return map.getCardConditionPoints(this);
	}

}
