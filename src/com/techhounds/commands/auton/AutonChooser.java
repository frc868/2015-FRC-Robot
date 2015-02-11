package com.techhounds.commands.auton;

import com.techhounds.OI;

import edu.wpi.first.wpilibj.command.Command;

/**
 * @author Atif Niyaz
 */
public class AutonChooser {
	
	public static final String [] AUTON_CHOICES = {
		"3 Tote",
		"2 Tote",
		"1 Tote",
		"Move To Auto Zone",
		"Do Nothing"};

	public static Command getSelected() {
		
		Command [] options = new Command[] {
				new ThreeTote(true),
				new TwoTote(),
				new FirstTote(),
				new MoveToAutoZone(1.5),
				new DoNothing()
		};
		
		return options[OI.getInstance().getAutonChoice()];
	}
}
