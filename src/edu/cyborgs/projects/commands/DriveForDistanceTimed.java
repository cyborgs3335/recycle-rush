package edu.cyborgs.projects.commands;

import edu.wpi.first.wpilibj.BuiltInAccelerometer;
import edu.wpi.first.wpilibj.Timer;

/**
 *
 */
public class DriveForDistanceTimed extends CommandBase {

	private static final double feetPerSecond = 3.9;
	
	private boolean isForward;

    public DriveForDistanceTimed(double distance) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(mechDriveSystem);
    	if (distance > 0)
    		isForward = true;
    	else
    		isForward = false;
    	setTimeout(Math.abs(distance)/feetPerSecond);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	mechDriveSystem.resetGyro();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (isForward)
    		mechDriveSystem.drive(0, -1, 0, false);
    	else
    		mechDriveSystem.drive(0, 1, 0, false);
    		
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return isTimedOut();
    }

    // Called once after isFinished returns true
    protected void end() {
    	mechDriveSystem.drive(0, 0, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
