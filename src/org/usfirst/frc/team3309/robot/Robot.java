
package org.usfirst.frc.team3309.robot;

import org.usfirst.frc.team3309.communications.BlackBox;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends IterativeRobot {
	final String defaultAuto = "Default";
	final String customAuto = "My Auto";
	String autoSelected;
	SendableChooser chooser;
	private static Drive drive;

	private int count= 0;
	

	public void robotInit() {
		chooser = new SendableChooser();
		chooser.addDefault("Default Auto", defaultAuto);
		chooser.addObject("My Auto", customAuto);
		SmartDashboard.putData("Auto choices", chooser);
		drive = new Drive();
		drive.init();
		Constants.setFmsMatch(DriverStation.getInstance().isFMSAttached());
		BlackBox.initializeLog(Constants.LOG_HEADER,Constants.getMatch(),false);
	}

	public void autonomousInit() {
		autoSelected = (String) chooser.getSelected();
		// autoSelected = SmartDashboard.getString("Auto Selector",
		// defaultAuto);
		System.out.println("Auto selected: " + autoSelected);
		BlackBox.writeString("AUTONOMUS STARTED");
	}

	public void autonomousPeriodic() 
	{
		
		BlackBox.writeLog();
	}

	public void teleopInit() 
	{
		drive.init();
		BlackBox.writeString("TELEOP STARTED");
	}
	
	public void disabledInit()
	{
		drive.init();
		BlackBox.writeString("DISABLED");
	}

	public void teleopPeriodic() 
	{
		drive.update();
		BlackBox.writeLog();
	}

	public void testPeriodic() 
	{

	}

}
