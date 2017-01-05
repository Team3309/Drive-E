package org.usfirst.frc.team3309.robot;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.usfirst.frc.team3309.communications.BlackBox;
import org.usfirst.frc.team3309.controllers.PID;
import org.usfirst.frc.team3309.sensor.MovingAverage;

import edu.wpi.first.wpilibj.Timer;

public class Drive {

	public static Actuator Actuator;
	public static XboxController driverRemote;

	private  PID leftPID = new PID();
	private  PID rightPID = new PID();
	private MovingAverage leftAvg = new MovingAverage(10, false);
	private MovingAverage rightAvg = new MovingAverage(10, false);
	
	private double LEFT_ENC_LAST = 0;
	private double RIGHT_ENC_LAST = 0;
	
	private Timer loopTimer;
	
	
	public void init() {
		Actuator = new Actuator();
		
		Actuator.init();
		
		leftPID.setOutputLimit(1);
		rightPID.setOutputLimit(1);
		
		leftPID.setPgain(.9);
		rightPID.setPgain(.9);
		leftPID.setIgain(.1);
		rightPID.setIgain(.1);
		leftPID.setDgain(0);
		rightPID.setDgain(0);
		
		leftPID.setItermLimit(0.15);
		rightPID.setItermLimit(0.15);
		
		leftPID.setInputScale(0.2); //Scales the input signal to work with the target signal, this value is tuned to two CIM motors
		rightPID.setInputScale(0.2);
		
		leftPID.clearHistory();
		rightPID.clearHistory();
		
		leftAvg.setOutlier(100); //Set percent over/under to discard
		rightAvg.setOutlier(100);
		
		driverRemote = new XboxController(0);
		
		driverRemote.setPolarityAnalog(driverRemote.AXIS_LEFT_Y,-1); //Sets the polarity Left Y Axis
		driverRemote.setDeadband(.025); //Set the dead band for the controller
		
		Sensors.resetEncoders();
		LEFT_ENC_LAST = 0;
		RIGHT_ENC_LAST = 0;
		
		loopTimer = new Timer();
		loopTimer.reset();
		loopTimer.start();
		
		Dashboard.init();
		
	}


	private void driveLeft()
	{
		driverRemote.setRumble(0);
		//Get desired speeds for left and right motors based on stick position simple control
		double rightSpeed = 0;
		double leftSpeed = 0;
		double leftPower = 0;
		double rightPower = 0;
		rightSpeed = (driverRemote.getLeftY() - 0.5*driverRemote.getLeftX());
		leftSpeed = (driverRemote.getLeftY() + 0.5*driverRemote.getLeftX());
		
		
		if(!this.driverRemote.getRB() && !this.driverRemote.getLB()) Constants.setAutoTurnLock(false); //Unlocks the Auto-Turn feature by checking if both buttons are released
		
		if((this.driverRemote.getRB() || this.driverRemote.getLB()) && !Constants.getAutoTurnLock()) //Executes an auto-turn if feature is unlocked
		{
			Turning.turnToEnc(20,1);				//Set turn target to a specific degree with a number of encoder counts for a dead band
			rightSpeed = Turning.getRightSpeed();//set rightSpeed
			leftSpeed = Turning.getLeftSpeed();//set leftSpeed
		}
		
		if(Constants.getAutoTurnFlag())		//Check to see if we're in a turn
		{
			rightSpeed = Turning.getRightSpeed();
			leftSpeed = Turning.getLeftSpeed();
			if(leftSpeed == 0 && rightSpeed == 0) Constants.setAutoTurnFlag(false); //If we've completed a turn
		}
		
		//Calculate the rate that the wheels are spinning. Recommend not using getRate as it is very noisy.
		
		double time = loopTimer.get();
		double leftEncRate = (Sensors.getLeftEncoder() - LEFT_ENC_LAST) / (Constants.ENCODER_CPR * time); //Calculates the rate of the wheels spinning in RPS
		double rightEncRate = (Sensors.getRightEncoder() - RIGHT_ENC_LAST) / (Constants.ENCODER_CPR * time); //Calculates RPS
		loopTimer.reset();
		
		LEFT_ENC_LAST = Sensors.getLeftEncoder();
		RIGHT_ENC_LAST = Sensors.getRightEncoder();
		
		
		leftPower = leftPID.PID_Control(leftEncRate, leftSpeed); //Use PID Controller to set a power for the Left side
		rightPower = rightPID.PID_Control(rightEncRate, rightSpeed); //Use PID Controller to set a power for the Right side
		
		BlackBox.logThis("LeftEncCount", Sensors.getLeftEncoder());
		BlackBox.logThis("RightEncCount", Sensors.getRightEncoder());
		BlackBox.logThis("Heading", 0.0);
		BlackBox.logThis("Pitch", 0.0);		
		
		Actuator.setMotorSpeed(RobotMap.RIGHT_DRIVE,rightPower); //Set motor power
		Actuator.setMotorSpeed(RobotMap.LEFT_DRIVE, leftPower);
		
		//Actuator.setMotorSpeed(RobotMap.RIGHT_DRIVE,rightSpeed); //Set motor power
		//Actuator.setMotorSpeed(RobotMap.LEFT_DRIVE, leftSpeed);
		
		/*
		Dashboard.setBasicText(0, "Left Count: " + Sensors.getLeftEncoder());
		Dashboard.setBasicText(5, "Right Count: " + Sensors.getRightEncoder());
		Dashboard.setBasicText(1, String.format("LeftEncSpeed: %.2f", leftEncRate));	//Send encoder counts to dashboard 
		Dashboard.setBasicText(6, String.format("RightEncSpeed: %.2f", rightEncRate));
		Dashboard.setBasicText(7, String.format("RightSpeedSignal: %.2f", rightSpeed));
		Dashboard.setBasicText(2 , String.format("LeftSpeedSignal: %.2f", leftSpeed));
		Dashboard.setBasicText(3, String.format("LeftPower: %.2f", leftPower*100));
		Dashboard.setBasicText(8, String.format("RightPower: %.2f", rightPower*100));
		*/
		Dashboard.clearBasicDash();
		if(Sensors.getNavXstatus()) Dashboard.setBasicText(0, "NavX Connected!");
		Dashboard.setBasicText(4, String.format("Heading: %.1f", Sensors.getHeading()));
		Dashboard.setBasicText(9, String.format("YawRate: %.1f",Sensors.getYawRate()));
		
		Dashboard.sendDash();

		
	}

	public void update() {
		
		//Uncomment one of the following lines to choose a drive style
		//this.driveGuitar();
		this.driveLeft();

	}

}
