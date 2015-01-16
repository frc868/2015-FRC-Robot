package Biscuits;

public abstract class Biscuit {
	
	public static boolean isEdible;
	public static boolean isCooked;
	public static int cookTemp;
	public static final int batch = 100;
	
	public static final int HOT = 3;
	public static final int WARM = 2;
	public static final int COLD = 1;
	
	public static String temp;
	
	public static final int RICH = 1;
	public static final int UPPER_MIDDLE = 2;
	public static final int ELSE = 3;
	
	public Biscuit() {
		
	}
	
	public abstract int cookTemp();
	
	public abstract boolean canEat();
	
	public abstract void cook(int status);
	
}
