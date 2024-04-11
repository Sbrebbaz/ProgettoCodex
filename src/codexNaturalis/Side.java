package Base;
import java.util.List;

public abstract class Side {
	private int pointValue;
	private List<Symbol> center;
	private Symbol corners [];
	
	public Side(List<Symbol> center, Symbol[] corners){
		this.center=center;
		this.corners=corners;
	}
	
	public Side(List<Symbol> center,Symbol cornerTopLeft,Symbol cornerTopRight,
			Symbol cornerBottomLeft,Symbol cornerBottomRight) {
		this.center=center;
		corners=new Symbol[4];
		corners[0]=cornerTopLeft;
		corners[1]=cornerTopRight;
		corners[2]=cornerBottomLeft;
		corners[3]=cornerBottomRight;
	}
	
	public abstract boolean verifyCondition(Map map);//non mi ricordo cosa doveva fare
	
	public abstract int getPoints(Map map);//data la map e le condizioni della carta ti ritorna i numeri di punti che ha fatto

	public int getPointValue() {
		return pointValue;
	}

	public void setPointValue(int pointValue) {
		this.pointValue = pointValue;
	}

	public List<Symbol> getCenter() {
		return center;
	}

	public void setCenter(List<Symbol> center) {
		this.center = center;
	}

	public Symbol[] getCorners() {
		return corners;
	}

	public void setCorners(Symbol[] corners) {
		this.corners = corners;
	}
	
	
	
}
