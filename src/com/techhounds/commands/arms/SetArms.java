package com.techhounds.commands.arms;

import edu.wpi.first.wpilibj.command.Command;
import com.techhounds.subsystems.ArmsSubsystem;;

/**
 *
 */
public class SetArms extends Command {
	
	public ArmsSubsystem arms;
	public double power;

    public SetArms(double power) {
        super("Set Arms");
        arms = ArmsSubsystem.getInstance();
        this.power = power;
        requires(arms);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	arms.setPower(power);
    	arms.setPosition(arms.OPEN);
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
