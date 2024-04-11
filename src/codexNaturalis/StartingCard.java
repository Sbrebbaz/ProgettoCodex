package Base;

import java.util.List;

public class StartingCard extends Side {

	public StartingCard(List<Symbol> center, Symbol[] corners){
		super(center,corners);
	}
	
	public StartingCard(List<Symbol> center,Symbol cornerTopLeft,Symbol cornerTopRight,
			Symbol cornerBottomLeft,Symbol cornerBottomRight) {
		
		super(center,cornerTopLeft,cornerTopRight,
				cornerBottomLeft,cornerBottomRight);
	}
	
	@Override
	public boolean verifyCondition(Map map) {
		return true;
	}
	
	@Override
	public int getPoints(Map map) {
		return 0;
	}

}
