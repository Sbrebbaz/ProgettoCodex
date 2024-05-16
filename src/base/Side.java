package base;
import java.util.Arrays;
import java.util.List;

public abstract class Side {
	private int pointValue;
	private List<Symbol> center;
	private Corner corners [];
	
	public Side(List<Symbol> center, Corner[] corners){
		this.center=center;
		this.corners=corners;
	}
	
	public Side(List<Symbol> center,Corner cornerTopLeft,Corner cornerTopRight,
			Corner cornerBottomLeft,Corner cornerBottomRight) {
		this.center=center;
		corners=new Corner[4];
		corners[0]=cornerTopLeft;
		corners[1]=cornerTopRight;
		corners[2]=cornerBottomLeft;
		corners[3]=cornerBottomRight;
	}
	
	public Corner getCornerFromPosition(int x, int y) throws Exception{
		if(x == -1) {
			if( y == -1) {
				return corners[3];
			}else if(y == 1) {
				return corners[2];
			}else{
				throw new Exception();
			}
		}else if(x == 1) {
			if( y == -1) {
				return corners[1];
			}else if(y == 1) {
				return corners[0];
			}else{
				throw new Exception();
			}
		}else {
			throw new Exception();
		}
	}
	
	public abstract boolean verifyCondition(Map map);//non mi ricordo cosa doveva fare
	
	public abstract int getPoints(Map map,int x,int y);//data la map e le condizioni della carta ti ritorna i numeri di punti che ha fatto

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

	public Corner[] getCorners() {
		return corners;
	}

	public void setCorners(Corner[] corners) {
		this.corners = corners;
	}

	@Override
	public String toString() {
		return String.format("\n Corners %s Center %s Points %s \n",Arrays.toString(corners),center,pointValue);
	}
}
