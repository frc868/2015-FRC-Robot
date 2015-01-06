package com.techhounds.commands.auton;

import com.techhounds.commands.auton.options.DoNothing;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 * @author Atif Niyaz
 */
public class AutonChooser {
	
	public static final String [] AUTON_CHOICES = {
		"Do Nothing"
	};

	public static Command getSelected() {
		
		Command [] options = new Command[] {
				new DoNothing()
		};
		
		return options[0];
	}
}
