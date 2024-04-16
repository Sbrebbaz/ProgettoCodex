package Base;

import java.util.List;

public class GenericSide extends Side{

	public GenericSide(List<Symbol> center, Corner[] corners){
		super(center,corners);
	}
	
	public GenericSide(List<Symbol> center,Corner cornerTopLeft,Corner cornerTopRight,
			Corner cornerBottomLeft,Corner cornerBottomRight) {
		super(center,cornerTopLeft,cornerTopRight,cornerBottomLeft,cornerBottomRight);
	}
	
	@Override
	public boolean verifyCondition(Map map) {
		return map.verifyCardPosition(this);
	}
	
	@Override
	public int getPoints(Map map) {
		return this.getPointValue();
	}

}
