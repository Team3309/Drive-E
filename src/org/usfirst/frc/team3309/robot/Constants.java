package org.usfirst.frc.team3309.robot;

public class Constants 
{
	//The following constants are used to assist in a turn in place operation
	//
	public static String TIMEZONE = "PST";
	public static double TRACK_WIDTH = 26.84; //Track width of vehicle in inches
	public static double WHEEL_SIZE = 8.125; //Wheel diameter in inches
	public static int ENCODER_CPR = 350; //Counts per revolution for the installed shaft encoders
	public static int ENCODER_RATE_OS = 60; //Sets the number of samples to average (Oversample) for calculation of encoder pulse period (rate)
	public static double ENC_COUNT_PER_DEGREE = 0; //This must be calculated in an init method
	public static double ENC_CPD_OFFSET = 0; //An offset that allows for fine tuning of encoder counts/degree for turn in place operations

	public static double AUTO_TURN_SPEED = 0.04; //Base speed for automatic turn in place
	
	public static boolean AUTO_TURN_FLAG = false; //Flag indicating if the robot is in an auto-turn routine
	public static boolean AUTO_TURN_LOCK = false; //Flag to release/allow turn routine to operate, must be set to false to allow routine
	public static boolean FMS_MATCH = false;
	
	public static String[] LOG_HEADER =
			{
					"Timestamp",
					"Heading",
					"Pitch",
					"Roll",
					"LeftEncCount",
					"RightEncCount",
					"TotalCurrent",
					"BatteryVoltage",
					"Power"
			};
	
	
	static
	{
		setEncoderCountPerDegree();
	}

	//Get Methods
	public static boolean getMatch()
	{
		return FMS_MATCH;
	}
	
	public static int getEncoderOs()
	{
		return ENCODER_RATE_OS;
	}
	public static double getEncoderCountPerDegree() 
	{
		return ENC_COUNT_PER_DEGREE;
	}
	
	public static double getAutoTurnSpeed() 
	{
		return AUTO_TURN_SPEED;
	}
	
	public static boolean getAutoTurnFlag()
	{
		return AUTO_TURN_FLAG;
	}
	
	public static boolean getAutoTurnLock()
	{
		return AUTO_TURN_LOCK;
	}
	

	//Set Methods
	public static void setFmsMatch(boolean fms)
	{
		FMS_MATCH = fms;
	}
	
	public static void setEncoderCountPerDegree() 
	{
		//The following equation for calculating counts per degrees is flawed. Hard coded a value below to continue testing
		//ENC_COUNT_PER_DEGREE = (WHEEL_SIZE * 360) / (ENCODER_CPR * TRACK_WIDTH) + ENC_CPD_OFFSET; //Calculates the ratio of number of encoder counts to degrees required to pivot a specific angle
		ENC_COUNT_PER_DEGREE = 2.58; //Hard coded value...bad practice
	}

	public static void setAutoTurnFlag(boolean flag)
	{
		AUTO_TURN_FLAG = flag;
	}
	
	public static void setAutoTurnLock(boolean flag)
	{
		AUTO_TURN_LOCK = flag;
	}

}
