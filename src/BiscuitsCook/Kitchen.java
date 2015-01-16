package BiscuitsCook;
import Biscuits.*;
import java.util.Scanner;

public class Kitchen {
	
	
	public static Scanner food = new Scanner(System.in);
	
	public static Biscuit [] biscuit;
	public static int status;
	public static String rich;
	
	public static void main(String [] args) {
		intro();
		while(true) {
			order();
			getStatus();
			cook();
			ship();
		}
	}
	
	public static void intro() {
		System.out.println("Welcome to programming kitchen.\nGet biscuits for plebians and cool folks.");
	}
	
	public static void order() {
		int choice;
		String thing = "";
		System.out.print("What kind of biscuit would you like?\n1. Burned\n2. Raw\n3. Leave\nChoice: ");
		choice = food.nextInt();
		
		if (choice == 1) {
			biscuit = new BurnedBiscuit[Biscuit.batch];
			for (int i= 0; i < biscuit.length; i++)
				biscuit[i] = new BurnedBiscuit();
			thing = "burned";
		} else if (choice == 2) {
			biscuit = new RawBiscuit[Biscuit.batch];
			for (int i= 0; i < biscuit.length; i++)
				biscuit[i] = new RawBiscuit();
			thing = "raw";
		} else {
			System.exit(666);
		}
		
		System.out.println("\nOrdered a batch of " + thing + " biscuits.\n");
	}
	
	public static void getStatus() {
		System.out.print("Which class of people are you serving?\n1. Upper\n2. Upper Middle\n3. Other\nChoice: ");
		status = food.nextInt();
		if (status == 1) {
			rich = "rich";
		} else if (status == 2) {
			rich = "well-off";
		} else if (status == 3) {
			rich = "plebian";
		}
		
	}
	
	public static void cook() {
		for (int i = 0; i < biscuit.length; i++) {
			biscuit[i].cook(status);
		}
		
		System.out.println("\nYou have freeze burned the biscuits for the given social class.  The temperature"
				+ " of the biscuits is now " + Biscuit.temp + ".");
		food.nextLine();
	}
	
	public static void ship() {
		System.out.print("\nYou shipped the batch of " + biscuit[0].toString() + " biscuits to the " + rich + " people.\n\n");
		food.nextLine();
	}
	
	
}
