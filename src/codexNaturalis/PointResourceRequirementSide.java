package Base;

import java.util.List;

public class PointResourceRequirementSide extends PlaceRequirementSide {

	private List<Symbol> pointRequirement;
	
	public PointResourceRequirementSide(List<Symbol> center, Corner[] corners, List<Symbol> placeRequirements ,List<Symbol> pointRequirement){
		super(center,corners,placeRequirements);
		this.pointRequirement=pointRequirement;
	}
	
	public PointResourceRequirementSide(List<Symbol> center,Corner cornerTopLeft,Corner cornerTopRight,
			Corner cornerBottomLeft,Corner cornerBottomRight, List<Symbol> placeRequirements, List<Symbol> pointRequirements) {
		super(center,cornerTopLeft,cornerTopRight,cornerBottomLeft,cornerBottomRight,placeRequirements);
		this.pointRequirement=pointRequirements;
	}

	public List<Symbol> getPointRequirement() {
		return pointRequirement;
	}

	public void setPointRequirement(List<Symbol> pointRequirement) {
		this.pointRequirement = pointRequirement;
	}

	
}
