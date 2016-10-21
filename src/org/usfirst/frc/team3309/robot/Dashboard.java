package org.usfirst.frc.team3309.robot;

import org.usfirst.frc.team3309.communications.UdpServer;

import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Dashboard {
	
	static UdpServer friarServer;
	
	private static double HEADING;
	private double PITCH;
	private double ROLL;
	
	private double YAW_RATE;
	private double PITCH_RATE;
	private double ROLL_RATE;
	
	private double RIGHT_ENC;
	private double LEFT_ENC;
	private double RIGHT_SPEED;
	private double LEFT_SPEED;
	
	public static void init()
	{
		friarServer = new UdpServer();
	}
	
	public static void setBasicText(int index, String text)
	{
		String key = "DB/String " + index;
		SmartDashboard.putString(key, text);
	}
	
	public static void clearBasicDash()
	{
		String key = "";
		for(int i = 0; i<10; i++)
		{
			key = "DB/String " + i;
			SmartDashboard.putString(key, "");
		}
	}
	
	public static void sendDash()
	{
		HEADING = HEADING + .2;
		if(HEADING>360) HEADING =0;
		
		friarServer.put("HEADING", (int)HEADING);
	}
	
}
