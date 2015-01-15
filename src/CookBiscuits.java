
public class CookBiscuits {
	
	public static int biscuits;
	
	public static void main(String [] args) {
		biscuits = 0;
		getBiscuits();
		freezerBurn(biscuits);
		ship(biscuits);
	}
	
	public static void getBiscuits() {
		biscuits += 10000;
		System.out.println("Got 10000 biscuits.");
	}
	
	public static void freezerBurn(int biscuits) {
		System.out.println(biscuits + " biscuits were freezer burned.");
	}
	
	public static void ship(int biscuits) {
		System.out.println("Shipped " + biscuits + " to plebians.");
	}

}
