package Biscuits;

public class BurnedBiscuit extends Biscuit{
	
	public BurnedBiscuit() {
		isEdible = false;
		cookTemp = WARM;
	}

	public int cookTemp() {
		return cookTemp;
	}

	public boolean canEat() {
		return isEdible;
	}
	
	public void cook(int status) {
		if (status == Biscuit.RICH) {
			cookTemp = HOT;
			isEdible = true;
			temp = "hot";
		} else if (status == Biscuit.UPPER_MIDDLE) {
			cookTemp = WARM;
			isEdible = true;
			temp = "warm";
		} else {
			cookTemp = COLD;
			temp = "cold";
		}
		
	}
	
	public String toString() {
		return "burned";
	}
	
}
