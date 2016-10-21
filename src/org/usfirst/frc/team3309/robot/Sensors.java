package org.usfirst.frc.team3309.robot;

import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.SerialPort;

public class Sensors {
	// Drive
	private static Encoder leftEnc;
	private static Encoder rightEnc;

	private static AHRS navX;

	private static PowerDistributionPanel pdp;

	public static void init() {
		System.out.println("STARTING INIT");
		
		rightEnc = new Encoder(RobotMap.RIGHT_ENCODER_A, RobotMap.RIGHT_ENCODER_B, false);
		leftEnc = new Encoder(RobotMap.LEFT_ENCODER_A, RobotMap.LEFT_ENCODER_B, false);
		
		rightEnc.reset();
		leftEnc.reset();
		rightEnc.setSamplesToAverage(Constants.getEncoderOs());
		leftEnc.setSamplesToAverage(Constants.getEncoderOs());

		navX = new AHRS(SPI.Port.kMXP);
		pdp = new PowerDistributionPanel();

	}

	static {
		init();
	}
	
	public static boolean getNavXstatus()
	{
		return navX.isConnected();
	}
	
	public static double getYawRate() {
		return navX.getRawGyroZ();
	}

	public static double getHeading() {
		return navX.getFusedHeading();
	}

	public static double getRoll() {
		return navX.getRoll();
	}

	public static void resetEncoders() {
		rightEnc.reset();
		leftEnc.reset();
	}

	public static double getRightEncoder()
	{
		return rightEnc.get();
	}

	public static double getRightEncRate() 
	{
		return rightEnc.getRate();
	}

	public static double getLeftEncoder()
	{
		return leftEnc.get();
	}

	public static double getLeftEncRate()
	{
		return leftEnc.getRate();
	}
	
}
