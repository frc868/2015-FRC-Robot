package com.techhounds.commands.auton;

import com.techhounds.subsystems.CameraSubsystem;
import com.techhounds.subsystems.DriveSubsystem;
import com.techhounds.subsystems.GyroSubsystem;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class AttackTarget extends Command {
	
	private DriveSubsystem drive;
	private GyroSubsystem gyro;
	private CameraSubsystem camera;
	
	private PIDController gyroPID;
	
	double degreesX;
	
	public AttackTarget() {
		drive = DriveSubsystem.getInstance();
		gyro = GyroSubsystem.getInstance();
		camera = CameraSubsystem.getInstance();
		
		requires(drive);
		requires(gyro);
		requires(camera);
		
		gyroPID = new PIDController(
				0.051, 
				DriveSubsystem.GYRO_Ki, 
				0.03,
				new PIDSource() {

					@Override
					public void setPIDSourceType(PIDSourceType pidSource) {
						
					}

					@Override
					public PIDSourceType getPIDSourceType() {
						// TODO Auto-generated method stub
						return PIDSourceType.kDisplacement;
					}

					@Override
					public double pidGet() {
						SmartDashboard.putNumber("PID ANGLE", getAngle());
						return getAngle();
					}
					
				},
				new PIDOutput() {
					public void pidWrite(double output) {
						SmartDashboard.putNumber("GyroPID RAW Output", output);
						output = output > 0.2 || output < -0.2
								? output : (output > 0 ? 0.2 : -0.2);
						drive.setPower(output, -output);
					}
			});
			gyroPID.setOutputRange(-.75, .75);
			gyroPID.setAbsoluteTolerance(1);
			SmartDashboard.putData("GYRO PID", gyroPID);
	}

	@Override
	protected void initialize() {
		degreesX = camera.getTargetAngle();
		gyroPID.setSetpoint(getAngle() + degreesX); 
		gyroPID.enable();
	}

	@Override
	protected void execute() {
		SmartDashboard.putNumber("GYRO PID ERROR", gyroPID.getError());
	}

	@Override
	protected boolean isFinished() {
		return Math.abs(gyroPID.getError()) < .5;
	}
	
	public static Command createSequence(int times) {
		CommandGroup cg = new CommandGroup();
		for(int i = 0; i < times; i++) {
			cg.addSequential(new AttackTarget());
			cg.addSequential(new WaitCommand(1));
		}
		return cg;
	}

	@Override
	protected void end() {
		gyroPID.disable();
	}
	
	public double getAngle() {
		return gyro.getRotation();
	}

	@Override
	protected void interrupted() {
		end();
	}

}
