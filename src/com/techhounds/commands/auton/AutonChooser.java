package com.techhounds.commands.auton;

import com.techhounds.OI;

import edu.wpi.first.wpilibj.command.Command;

/**
 * @author Atif Niyaz
 */
public class AutonChooser {
	
	public static final String [] AUTON_CHOICES = {
		"3 Tote with Bin",
		"3 Tote",
		"2 Tote with Bin",
		"2 Tote",
		"1 Tote with Bin",
		"1 Tote",
		"Bin",
		"Push",
		"Move To Auto Zone",
		"Do Nothing"}; // TODO: Come up with more possibilities. These are the basic.

	public static Command getSelected() {
		
		Command [] options = new Command[] {
				new DoNothing(),
				new DoNothing(),
				new DoNothing(),
				new DoNothing(),
				new DoNothing(),
				new DoNothing(),
				new DoNothing(),
				new DoNothing(),
				new DoNothing(),
				new DoNothing()
		}; // TODO: Replace with actual commands
		
		return options[OI.getInstance().getAutonChoice()];
	}
}
