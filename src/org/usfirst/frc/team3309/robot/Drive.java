package org.usfirst.frc.team3309.robot;

import edu.wpi.first.wpilibj.Talon;

public class Drive {

	private static Drive instance;
	XboxController driverRemote = new XboxController(0);
	Talon rightFront = new Talon(RobotMap.RIGHT_DRIVE_FRONT);
	Talon rightBack = new Talon(RobotMap.RIGHT_DRIVE_BACK);
	Talon leftFront = new Talon(RobotMap.LEFT_DRIVE_FRONT);
	Talon leftBack = new Talon(RobotMap.LEFT_DRIVE_BACK);

	public static Drive getInstance() {
		if (instance == null) {
			instance = new Drive();
		}
		return instance;
	}

	private Drive() {
		leftFront.setInverted(true);
		leftBack.setInverted(true);
	}
	
	private void driveGuitar() {
		double speed = 0;
		if (this.driverRemote.getA()) {
			leftFront.set(1);
			leftBack.set(1);
			rightBack.set(-1);
			rightFront.set(-1);
		} else if (this.driverRemote.getB()) {
			leftFront.set(-1);
			leftBack.set(-1);
			rightBack.set(1);
			rightFront.set(1);
		} else if (this.driverRemote.getPOV() == 180) {
			speed = (this.driverRemote.getRightX() + 1) / 2;
			rightFront.set(speed);
			rightBack.set(speed);
			leftFront.set(speed);
			leftBack.set(speed);
		} else if (this.driverRemote.getPOV() == 0) {
			speed = -1 * (this.driverRemote.getRightX() + 1) / 2;
			rightFront.set(speed);
			rightBack.set(speed);
			leftFront.set(speed);
			leftBack.set(speed);
		} else {
			rightFront.set(0);
			rightBack.set(0);
			leftFront.set(0);
			leftBack.set(0);
		}
	}
	private void driveArcade()
	{
		double rightSide = (this.driverRemote.getLeftY() + this.driverRemote.getRightX());
		double leftSide = (this.driverRemote.getLeftY() - this.driverRemote.getRightX());
		
		rightFront.set(rightSide);
		rightBack.set(rightSide);
		leftFront.set(leftSide);
		leftBack.set(leftSide);
		
	}

	public void update() {
		//this.driveGuitar();
		this.driveArcade();
	}

}
