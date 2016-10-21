package org.usfirst.frc.team3309.robot;

/**
 * Mapping sensors and actuators to various ports on RoboRio
 * 
 * @author ROM
 *
 */
public class RobotMap 
{
	// Motor Controllers
	// Drive
	public static final int RIGHT_DRIVE_FRONT = 0;
	public static final int RIGHT_DRIVE_BACK = 3;
	public static final int LEFT_DRIVE_FRONT = 1;
	public static final int LEFT_DRIVE_BACK = 2;
	
	public static final int LEFT_DRIVE = 0;
	public static final int RIGHT_DRIVE = 1;
	
	//Sensors
	//Encoders
	public static final int LEFT_ENCODER_A = 0;
	public static final int LEFT_ENCODER_B = 1;
	public static final int RIGHT_ENCODER_A = 2;
	public static final int RIGHT_ENCODER_B = 3;
	
	//Polarity
	public static final int LEFT_ENCODER_POLARITY = 1;
	public static final int RIGHT_ENCODER_POLARITY = 1;
	
	public static final boolean LEFT_DRIVE_FRONT_POLARITY = false;
	public static final boolean LEFT_DRIVE_BACK_POLARITY = false;
	public static final boolean RIGHT_DRIVE_FRONT_POLARITY = true;
	public static final boolean RIGHT_DRIVE_BACK_POLARITY = true;
	
}
