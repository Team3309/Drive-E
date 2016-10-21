package org.usfirst.frc.team3309.robot;

//This class allows a standard tank-drive robot to turn to a specified angle using either encoders on the wheels or a compass heading.

public class Turning {

	private static double LEFT_TARGET = 0;
	private static double RIGHT_TARGET = 0;
	private static double DEAD_BAND = 0;
	
	public static void turnToHead(double degrees, double deadBand)
	{
		System.out.println("Turn to Heading initiated...");
		
		DEAD_BAND = deadBand;
	}
	
	public static void turnToEnc(double degrees, int deadBand)	//Turns to a relative angle in degrees using encoders
	{
		System.out.println("Turn to Encoder initiated...");
		Constants.setAutoTurnFlag(true); //Sets the auto turning flag so we know we're in an auto turn routine
		Constants.setAutoTurnLock(true); //Sets the turn lock so we don't do more than one turn per click
		
		int direction = 1; //Defaults a positive relative turn (Right)
		
		if (degrees<0) direction = -1; //Direction -1 indicates a left turn, 1 is right
		
		double leftEnc = Sensors.getLeftEncoder();
		double rightEnc = Sensors.getRightEncoder();
		
		double leftEncStartCount = leftEnc; //Set initial encoder counts
		double rightEncStartCount = rightEnc;
		
		double turnCounts = degrees * Constants.getEncoderCountPerDegree(); //Calculates the encoder counts required to turn a certain number of degrees. Casts to int because encoder counts are integers.

		LEFT_TARGET = leftEncStartCount + turnCounts;
		RIGHT_TARGET = rightEncStartCount - turnCounts;	
		DEAD_BAND = deadBand; //Sets the deadband, or acceptable encoder counts to complete a turn
		System.out.println("Left Target: " + LEFT_TARGET + "/" + leftEncStartCount);
		System.out.println("Right Target: " + RIGHT_TARGET + "/" + rightEncStartCount);
		System.out.println("Deadband: " + DEAD_BAND);
	}
	
	public static double getLeftSpeed()
	{
		double leftEnc = Sensors.getLeftEncoder();

		double leftError = LEFT_TARGET - leftEnc;

		if(Math.abs(leftError)<DEAD_BAND)
		{
			return 0;
		}
		
		double speed = Math.signum(leftError) * Constants.getAutoTurnSpeed(); //Multiplies the auto turn speed by the sign of the error
		
		return speed;
	}
	
	public static double getRightSpeed()
	{
		double rightEnc = Sensors.getRightEncoder();
		
		double rightError = RIGHT_TARGET - rightEnc;
		
		if(Math.abs(rightError)<DEAD_BAND)
		{
			return 0;
		}
			
		double speed = Math.signum(rightError) * Constants.getAutoTurnSpeed(); //Multiplies the auto turn speed by the sign of the error
		
		return speed;
	}
}
