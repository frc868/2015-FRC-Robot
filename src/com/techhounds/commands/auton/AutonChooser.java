package com.techhounds.commands.auton;

import com.techhounds.OI;

import edu.wpi.first.wpilibj.command.Command;

/**
 * @author Atif Niyaz
 */
public class AutonChooser {
	
	public static final String [] AUTON_CHOICES = {
		"3 Tote Move Two Bins",
		"3 Tote Move Bin",
		"3 Tote",
		"3 Tote Reverse",
		"2 Tote Move Bin",
		"2 Tote",
		"1 Bin 1 Tote",
		"1 Tote",
		"1 Bin Left",
		"1 Bin Right",
		"Bin and Up",
		"Drive Forward",
		"Do Nothing"};

	public static Command getSelected() {
		
		Command [] options = new Command[] {
				new ThreeToteMoveTwoBin(true),
				new ThreeToteMoveBin(true),
				new ThreeTote(true, 2),
				new ReverseThreeTote(true, true, true),
				new TwoToteMoveBin(true),
				new TwoTote(true, 2),
				new BinAndTote(),
				new OneTote(),
				new OneBin(true),
				new OneBin(false),
				new BinAndUp(),
				new AutonDrive(8.5, 3),
				new DoNothing()
		};
		
		return options[OI.getInstance().getAutonChoice()];
	}
}
