package Base;

import java.util.List;

public abstract class PointCard extends Side {

	public PointCard(List<Symbol> center, Symbol[] corners){
		super(center,corners);
	}
	
	public PointCard(List<Symbol> center,Symbol cornerTopLeft,Symbol cornerTopRight,
			Symbol cornerBottomLeft,Symbol cornerBottomRight) {
		
		super(center,cornerTopLeft,cornerTopRight,
				cornerBottomLeft,cornerBottomRight);
	}

}
