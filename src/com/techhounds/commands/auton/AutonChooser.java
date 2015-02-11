package com.techhounds.commands.auton;

import com.techhounds.OI;

import edu.wpi.first.wpilibj.command.Command;

/**
 * @author Atif Niyaz
 */
public class AutonChooser {
	
	public static final String [] AUTON_CHOICES = {
		"3 Tote",
		"2 Tote with Bin",
		"2 Tote",
		"1 Tote",
		"Move To Auto Zone",
		"Do Nothing"}; // TODO: Come up with more possibilities. These are the basic.

	public static Command getSelected() {
		
		Command [] options = new Command[] {
				new ThreeTote(),
				new TwoToteBin(true),
				new TwoToteBin(false),
				new FirstTote(),
				new MoveToAutoZone(5),
				new DoNothing()
		};
		
		return options[OI.getInstance().getAutonChoice()];
	}
}
