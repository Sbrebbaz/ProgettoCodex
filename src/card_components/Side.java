package card_components;

import java.util.List;

import enumeration.Symbol;
import game_components.Map;
public abstract class Side {
	private int pointValue;
	private List<Symbol> center;
	private Corner corners [];
	/**
	 * creates a GenericSide from the center, corners and point value
	 * @param center
	 * @param corners
	 * @param pointValue
	 */
	public Side(List<Symbol> center, Corner[] corners ,int pointValue){
		this.pointValue = pointValue;
		this.center=center;
		this.corners=corners;
	}
	/**
	 *  creates a GenericSide from the center, corners and point value
	 * @param center
	 * @param cornerTopLeft
	 * @param cornerTopRight
	 * @param cornerBottomLeft
	 * @param cornerBottomRight
	 * @param pointValue
	 */
	public Side(List<Symbol> center,Corner cornerTopLeft,Corner cornerTopRight,
			Corner cornerBottomLeft,Corner cornerBottomRight,int pointValue) {
		this.center=center;
		corners=new Corner[4];
		corners[0]=cornerTopLeft;
		corners[1]=cornerTopRight;
		corners[2]=cornerBottomLeft;
		corners[3]=cornerBottomRight;
		this.pointValue = pointValue;
	}
	/**
	 * takes the corner adjacent to a card from their relative position
	 * @param x
	 * @param y
	 * @return
	 * @throws Exception
	 */
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
	/**
	 * verify  if the requirements for placing the side have been met
	 */
	public abstract boolean verifyCondition(Map map);
	/**
	 * get the point score by the card
	 */
	public abstract int getPoints(Map map,int x,int y);
	/**
	 * get the point value of this side
	 * @return
	 */
	public int getPointValue() {
		return pointValue;
	}
	/**
	 * set the point value of this side
	 * @param pointValue
	 */
	public void setPointValue(int pointValue) {
		this.pointValue = pointValue;
	}
	/**
	 * get the center of this side
	 * @return
	 */
	public List<Symbol> getCenter() {
		return center;
	}
	/**
	 * set the center of this side
	 * @param center
	 */
	public void setCenter(List<Symbol> center) {
		this.center = center;
	}
	/**
	 * get the corners of this side
	 * @return
	 */
	public Corner[] getCorners() {
		return corners;
	}
	/**
	 * set the corners of this side
	 * @param corners
	 */
	public void setCorners(Corner[] corners) {
		this.corners = corners;
	}
	/**
	 * return a String with all informations of this side
	 */
	@Override
	public String toString() {
			
		StringBuilder sb = new StringBuilder();

		sb.append("Center ");
		sb.append(center);
		sb.append(" ");
		
		if(corners != null) {
			sb.append("Corners [ ");

			sb.append("TL: ");
			if(corners[0] != null) {
				sb.append(corners[0]);
			}
			else {
				sb.append("NULL");
			}
			sb.append(" ");

			sb.append("TR: ");
			if(corners[1] != null) {
				sb.append(corners[1]);
			}
			else {
				sb.append("NULL");
			}
			sb.append(" ");

			sb.append("BL: ");
			if(corners[2] != null) {
				sb.append(corners[2]);
			}
			else {
				sb.append("NULL");
			}
			sb.append(" ");

			sb.append("BR: ");
			if(corners[3] != null) {
				sb.append(corners[3]);
			}
			else {
				sb.append("NULL");
			}
			sb.append(" ] ");
		}
		
		sb.append("Points [ ");
		sb.append(pointValue);
		sb.append(" ] ");
		
		return sb.toString();
	}
	
}
