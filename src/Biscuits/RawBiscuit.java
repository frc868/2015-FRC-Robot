package Biscuits;

public class RawBiscuit extends Biscuit {

	public RawBiscuit() {
		isEdible = true;
		cookTemp = COLD;
	}

	public int cookTemp() {
		return cookTemp;
	}

	@Override
	public boolean canEat() {
		return isEdible;
	}

	public void cook() {
		cookTemp = COLD;
	}

	@Override
	public void cook(int status) {
		cookTemp = COLD;
		temp = "cold";
	}
	
	public String toString() {
		return "raw";
	}

}
