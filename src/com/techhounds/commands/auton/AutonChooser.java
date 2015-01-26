package com.techhounds.commands.auton;

import com.techhounds.OI;
import com.techhounds.commands.auton.options.DoNothing;

import edu.wpi.first.wpilibj.command.Command;

/**
 * @author Atif Niyaz
 */
public class AutonChooser {
	
	public static final String [] AUTON_CHOICES = {
//		"3 Tote Pickup",
//		"2 Tote Pickup",
//		"1 Tote Pickup",
//		"1 Tote Push",
//		"Move to Auto Zone",
		"Do Nothing"};

	public static Command getSelected() {
		
		Command [] options = new Command[] {
//				new DoNothing(),
//				new DoNothing(),
//				new DoNothing(),
//				new DoNothing(),
//				new DoNothing(),
				new DoNothing()
		};
		
		return options[OI.getInstance().getAutonChoice()];
	}
}
