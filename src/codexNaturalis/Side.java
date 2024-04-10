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
	
	public abstract boolean verifyCondition();//non mi ricordo cosa doveva fare
	
	public abstract int getPoints();//data la map e le condizioni della carta ti ritorna i numeri di punti che ha fatto
	
}
