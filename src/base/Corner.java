package base;

public class Corner {
	private Symbol symbol;
	/**
	 * construction of corner
	 * @param symbol indicates the type of corner
	 */
	public Corner(Symbol symbol){
		this.symbol=symbol;
	}
	/**
	 * get type of Corner
	 * @return
	 */
	public Symbol getSymbol() {
		return symbol;
	}
	/**
	 * set type of Corner
	 * @param symbol
	 */
	public void setSymbol(Symbol symbol) {
		this.symbol = symbol;
	}
	/**
	 * return a string with all informations of the Corner
	 */
	@Override
	public String toString() {
		return symbol.toString();
	}
}
