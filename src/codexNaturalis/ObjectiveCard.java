package Base;

import java.util.List;

public class ObjectiveCard extends PointCard{

	public ObjectiveCard(List<Symbol> center, Symbol[] corners){
		super(center,corners);
	}
	
	public ObjectiveCard(List<Symbol> center,Symbol cornerTopLeft,Symbol cornerTopRight,
			Symbol cornerBottomLeft,Symbol cornerBottomRight) {
		
		super(center,cornerTopLeft,cornerTopRight,
				cornerBottomLeft,cornerBottomRight);
	}
	
	@Override
	public boolean verifyCondition(Map map) {
		return map.verifyCardCondition(this);
	}
	
	@Override
	public int getPoints(Map map) {
		return this.getPoints();
	}

}
