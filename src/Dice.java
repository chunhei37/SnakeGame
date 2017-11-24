import java.lang.Math;

public class Dice extends Player {
	private int dicePoint;
	
	public Dice() {
		setDicePoint((int)(Math.random() * 6 + 1));
	}
	
	public void setDicePoint(int dicePoint) {
		this.dicePoint = dicePoint;
	}
	
	public int getDicePoint() {
		return dicePoint;
	}
	
}
