package card_components;

import java.util.List;

import enumeration.Symbol;
import game_components.Map;

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
