package com.techhounds.commands.arms;

import edu.wpi.first.wpilibj.command.Command;
import com.techhounds.subsystems.ArmsSubsystem;;

/**
 *
 */
public class SetArms extends Command {
	
	public ArmsSubsystem arms;
	public double power;
	
	private boolean position;

    public SetArms(double power, boolean position) {
        super("Set Arms");
        arms = ArmsSubsystem.getInstance();
        this.power = power;
        requires(arms);
        this.position = position;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	arms.setPower(power);
    	arms.setPosition(position);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
