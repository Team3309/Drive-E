package org.usfirst.frc.team3309.robot;

import edu.wpi.first.wpilibj.Talon;

public class Actuator {
	
	private static Talon rightFront;
	private static Talon rightBack;
	private static Talon leftFront;
	private static Talon leftBack;
	
	static
	{
		rightFront = new Talon(RobotMap.RIGHT_DRIVE_FRONT);
		rightBack = new Talon(RobotMap.RIGHT_DRIVE_BACK);
		leftFront = new Talon(RobotMap.LEFT_DRIVE_FRONT);
		leftBack = new Talon(RobotMap.LEFT_DRIVE_BACK);
		

		rightFront.setInverted(RobotMap.RIGHT_DRIVE_FRONT_POLARITY);
		rightBack.setInverted(RobotMap.RIGHT_DRIVE_BACK_POLARITY);
		leftFront.setInverted(RobotMap.LEFT_DRIVE_FRONT_POLARITY);
		leftBack.setInverted(RobotMap.LEFT_DRIVE_BACK_POLARITY);
	}
	
	public void init()
	{
		rightFront.set(0);
		rightBack.set(0);
		leftFront.set(0);
		leftBack.set(0);
		
	}
	
	public void setMotorSpeed(int motor, double speed)
	{
		switch(motor)
		{
		case RobotMap.LEFT_DRIVE:
			leftFront.set(speed);//Set left motors
			leftBack.set(speed);
			break;
		
		case RobotMap.RIGHT_DRIVE:
			rightFront.set(speed);//Set Right Motors
			rightBack.set(speed);
			break;
		
		default:
			//Optional default behavior, recommend leaving this blank
			break;
		}
	}
	
}
