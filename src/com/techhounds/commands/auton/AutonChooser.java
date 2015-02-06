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
		"Move To Auto Zone",
		"Do Nothing"}; // TODO: Come up with more possibilities. These are the basic.

	public static Command getSelected() {
		
		Command [] options = new Command[] {
				new ThreeToteBin(true),
				new ThreeToteBin(false),
				new TwoToteBin(true),
				new TwoToteBin(false),
				new OneToteBin(true),
				new OneToteBin(false),
				new CollectBin(),
				new MoveToAutoZone(5),
				new DoNothing()
		};
		
		return options[OI.getInstance().getAutonChoice()];
	}
}
