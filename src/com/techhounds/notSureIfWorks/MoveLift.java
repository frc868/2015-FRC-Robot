package com.techhounds.notSureIfWorks;

import com.techhounds.subsystems.LiftSubsystem;

import edu.wpi.first.wpilibj.command.Command;

/**
 * @author Alex Fleig, Matt Simons, Ayon Mitra, Clayton Detke
 */
public class MoveLift extends Command {

	private LiftSubsystemAtif lift;
	private LiftSubsystemAtif.Direction direction;
	
    public MoveLift(LiftSubsystemAtif.Direction direction) {
        super("Move Lift");
        
    	lift = LiftSubsystemAtif.getInstance();
    	requires(lift);
    	
    	this.direction = direction;
    }

    protected void initialize() {
    	lift.setLiftDirection(direction, LiftSubsystem.LIFT_POWER);
    }

    protected void execute() {
    	/* Hello Alex, Ayon, Clayton, and Matt,
    	 * So tonight I was looking over the code and I came across that
    	 * you have three commands. As I was looking over, I realized...
    	 * hmmmm...maybe there is a better way to write this, but it would be
    	 * in one command!
    	 * 
    	 * Here it is folks!
    	 * 
    	 * So basically, what this command's constructor does is that it passes
    	 * a parameter of type LiftSubsystem.Direction called direction. There are
    	 * three values for LiftSubsystem.Direction, UP, DOWN, and STOP. With these
    	 * three values, the constructor saves it into the object this direction.
    	 * 
    	 * So in initialize, it direction is not STOP, it will set the Lift to go in the
    	 * desired direction at the speed you give it. In this case, it is 
    	 * LiftSubsystem.LIFT_POWER. If it is STOP, then it will stop it.
    	 * 
    	 * In the execute method, will be this comment, doing nothing but taking, many
    	 * mere lines of Java. Oh what fun it is to waste lines. Muahahaha!
    	 * 
    	 * The isFinished seems sort of confusing to you. Well, Ill help you.
    	 * 
    	 * So heres the first part.
    	 * 		direction == LiftSubsystem.Direction.STOP;
    	 * 
    	 * If it is stop, then why do we need to stay in the command? Just leave it lol.
    	 * If it is not stop, then we will let the command do magic.
    	 * 
    	 * So heres the second part.
    	 * 		(direction == LiftSubsystem.Direction.UP ? lift.getTop() : lift.getBottom());
    	 * 
    	 * Ah, I see you might be confused. You first might say, what the hell is a question 
    	 * mark and a colon doing in this. Well let me explain. Heres a general format for this
    	 * Java sentence:
    	 * 
    	 * 		boolean condition ? action1 : action2;
    	 * 
    	 * So what happens, if the condition is TRUE, then it will execute action1,
    	 * If condition is FALSE, it will execute action2.
    	 * 
    	 * This is much easier to use than if statements (and cleaner!). Note that this only works
    	 * for actions that have return types.
    	 * 
    	 * You probably know what end and interrupted do. It should be self-explanatory!
    	 * 
    	 * To summarize, I simplified your code into one command. It would be much easier
    	 * to call.
    	 * 
    	 * Hey, good job guys, I like what you had done so far, Keep it up!
    	 * 
    	 * ~~Atif
    	 */
    }

    protected boolean isFinished() {
        return direction == LiftSubsystemAtif.Direction.STOP || 
        		(direction == LiftSubsystemAtif.Direction.UP ? lift.isAtTop() : lift.isAtBottom());
    }

    protected void end() {
    	lift.stopLift();
    }

    protected void interrupted() {
    	end();
    }
}
