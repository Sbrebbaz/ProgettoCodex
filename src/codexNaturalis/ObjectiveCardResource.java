package Base;

import java.util.List;

public class ObjectiveCardResource extends ObjectiveCard{

	private Symbol requirement;
	
	public ObjectiveCardResource(List<Symbol> center, Symbol[] corners, Symbol requirement){
		super(center,corners);
		this.requirement=requirement;
	}
	
	public ObjectiveCardResource(List<Symbol> center,Symbol cornerTopLeft,Symbol cornerTopRight,
			Symbol cornerBottomLeft,Symbol cornerBottomRight, Symbol requirement) {
		
		super(center,cornerTopLeft,cornerTopRight,
				cornerBottomLeft,cornerBottomRight);
		this.requirement=requirement;
	}
	
	@Override
	public int getPoints(Map map) {
		return map.getCardConditionPoints(this);
	}

	public Symbol getRequirement() {
		return requirement;
	}

	public void setRequirement(Symbol requirement) {
		this.requirement = requirement;
	}
	
	

}
