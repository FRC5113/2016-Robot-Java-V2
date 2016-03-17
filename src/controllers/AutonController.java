package controllers;

import drive.MotorManager;
import drive.SensorManager;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import auton.ChivalDeFrise;
import auton.DefenseFrame;
//import auton.Drawbridge;
import auton.DriveForward;
import auton.Moat;
import auton.Ramparts;
import auton.Rockwall;
import auton.RoughTerrain;

public class AutonController
{	
	private int caseSelector = 5;
	private double leftMotor, rightMotor;
	private double tiltMotorArm, tiltMotorShoot;
	private String autoName = "";
	
	
	Moat moat;
	//Drawbridge drawbridge;
	Ramparts ramparts;
	RoughTerrain roughTerrain;
	ChivalDeFrise chivalDeFrise;
	Rockwall rockwall;
	DriveForward forward;

	
	public void init() 
	{
		moat = new Moat();
		//drawbridge = new Drawbridge();
		ramparts = new Ramparts();
		roughTerrain = new RoughTerrain();
		chivalDeFrise = new ChivalDeFrise();
		rockwall = new Rockwall();
		forward = new DriveForward();
	}


	public void update(MotorManager dr, SensorManager sensors)
	{
		if(caseSelector == 0)
			forward.update(sensors, dr);
		else if(caseSelector == 1)
			chivalDeFrise.update(sensors, dr);
		//else if(caseSelector == 2)
			//drawbridge.update(sensors, dr);
		else if(caseSelector == 3)
			moat.update(sensors, dr);
		else if(caseSelector == 4)
			ramparts.update(sensors, dr);
		else if(caseSelector == 5)
			roughTerrain.update(sensors, dr);
		else if(caseSelector == 6)
			rockwall.update(sensors, dr);
	}
	
	public void forward(double speed)
	{
		leftMotor = speed;
		rightMotor = speed;
	}
	
	public void stop()
	{
		leftMotor = 0;
		rightMotor = 0;
	}
	
	

	public void shootswing(double speed)
	{
		tiltMotorShoot = speed;
	}
	
	public void armswing(double speed)
	{
		tiltMotorArm = speed;
	}

	public void changeMode(boolean switchMode)
	{
		if(switchMode)
		{
			if(caseSelector == 6)
				caseSelector = 0;
			else
				caseSelector++;
		}
		
		if(caseSelector == 0)
			autoName = "Forward";
		else if(caseSelector == 1)
			autoName = "Chival";
		else if(caseSelector == 2)
			autoName = "Drawbridge";
		else if(caseSelector == 3)
			autoName = "Moat";
		else if(caseSelector == 4)
			autoName = "Ramparts";
		else if(caseSelector == 5)
			autoName = "RoughTerrain";
		else if(caseSelector == 6)
			autoName = "RockWall";
		
		SmartDashboard.putString("Auto Mode", autoName);
		
	}
	
	
	
}
