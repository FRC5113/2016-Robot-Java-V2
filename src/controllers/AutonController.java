package controllers;

import drive.MotorManager;
import drive.SensorManager;
import auton.ChivalDeFrise;
import auton.DefenseFrame;
import auton.Drawbridge;
import auton.Moat;
import auton.Ramparts;
import auton.RoughTerrain;

public class AutonController
{	
	private int caseSelector = 5;
	private double leftMotor, rightMotor;
	private double tiltMotorArm, tiltMotorShoot;
	
	
	Moat moat;
	Drawbridge drawbridge;
	Ramparts ramparts;
	RoughTerrain roughTerrain;
	ChivalDeFrise chivalDeFrise;
	

	
	public void init() 
	{
		moat = new Moat();
		drawbridge = new Drawbridge();
		ramparts = new Ramparts();
		roughTerrain = new RoughTerrain();
		chivalDeFrise = new ChivalDeFrise();
	}


	public void update(MotorManager dr, SensorManager sensors)
	{
		if(caseSelector == 1)
			chivalDeFrise.update(sensors, dr);
		else if(caseSelector == 2)
			drawbridge.update(sensors, dr);
		else if(caseSelector == 3)
			moat.update(sensors, dr);
		else if(caseSelector == 4)
			ramparts.update(sensors, dr);
		else if(caseSelector == 5)
			roughTerrain.update(sensors, dr);
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


	
}
