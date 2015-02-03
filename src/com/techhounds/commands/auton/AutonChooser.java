package com.techhounds.commands.auton;

import com.techhounds.OI;
import com.techhounds.commands.auton.tote.OneToteBin;
import com.techhounds.commands.auton.tote.ThreeToteBin;
import com.techhounds.commands.auton.tote.TwoToteBin;

import edu.wpi.first.wpilibj.command.Command;

/**
 * @author Atif Niyaz
 */
public class AutonChooser {
	
	public static final String [] AUTON_CHOICES = {
	//	"Totes mah goats",
	//	"1 Tote 2 Tote Red Tote Blue Tote",
	//	"Goats ma totes",
	//	"Suicide run into other team",
	//	"LED rave",
		"Totes mah Goats",
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
				new ThreeToteBin(true),
				new ThreeToteBin(false),
				new TwoToteBin(true),
				new TwoToteBin(false),
				new OneToteBin(true),
				new OneToteBin(false),
				new CollectBin(),
				new Push(),
				new MoveToAutoZone(),
				new DoNothing()
		};
		
		return options[OI.getInstance().getAutonChoice()];
	}
}
