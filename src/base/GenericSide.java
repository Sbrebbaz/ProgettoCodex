package base;

import java.util.List;

public class GenericSide extends Side{

	public GenericSide(List<Symbol> center, Corner[] corners,int pointValue){
		super(center,corners,pointValue);
	}

	public GenericSide(List<Symbol> center,Corner cornerTopLeft,Corner cornerTopRight,
			Corner cornerBottomLeft,Corner cornerBottomRight,int pointValue) {
		super(center,cornerTopLeft,cornerTopRight,cornerBottomLeft,cornerBottomRight,pointValue);
	}
	
	@Override
	public boolean verifyCondition(Map map) {
		return true;
	}
	
	@Override
	public int getPoints(Map map,int x,int y) {
		return this.getPointValue();
	}
}
