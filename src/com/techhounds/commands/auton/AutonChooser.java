package com.techhounds.commands.auton;

import com.techhounds.OI;

import edu.wpi.first.wpilibj.command.Command;

/**
 * @author Atif Niyaz
 */
public class AutonChooser {
	
	public static final String [] AUTON_CHOICES = {
		"3 Tote",
		"3 Tote Reverse",
		"2 Tote",
		"1 Bin 1 Tote",
		"1 Tote",
		"1 Bin",
		"Move To Auto Zone",
		"Do Nothing"};

	public static Command getSelected() {
		
		Command [] options = new Command[] {
				new ThreeTote(true, 2),
				new ReverseThreeTote(true, true, true),
				new TwoTote(true, 2),
				new BinAndTote(),
				new OneTote(),
				new OneBin(),
				new MoveToAutoZone(1.5),
				new DoNothing()
		};
		
		return options[OI.getInstance().getAutonChoice()];
	}
}
