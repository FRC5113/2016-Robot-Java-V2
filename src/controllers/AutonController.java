package controllers;

import drive.MotorManager;
import drive.SensorManager;
import auton.ChivalDeFrise;
import auton.DefenseFrame;
import auton.Drawbridge;
import auton.Moat;
import auton.Ramparts;
import auton.RoughTerrain;

public class AutonController extends DriveController
{	
	private int caseSelector = 1;
	private double leftMotor, rightMotor;
	private double tiltMotorArm, tiltMotorShoot;
	
	
	Moat moat;
	Drawbridge drawbridge;
	Ramparts ramparts;
	RoughTerrain roughTerrain;
	ChivalDeFrise chivalDeFrise;
	

	@Override
	public void init() 
	{
		moat = new Moat();
		drawbridge = new Drawbridge();
		ramparts = new Ramparts();
		roughTerrain = new RoughTerrain();
		chivalDeFrise = new ChivalDeFrise();
	}

	@Override
	public void update(MotorManager dr)
	{
		
	}
	public void update(MotorManager dr, SensorManager sensors)
	{
		if(caseSelector == 1)
			chivalDeFrise.update(sensors);
		else if(caseSelector == 2)
			drawbridge.update(sensors);
		else if(caseSelector == 3)
			moat.update(sensors);
		else if(caseSelector == 4)
			ramparts.update(sensors);
		else if(caseSelector == 5)
			roughTerrain.update(sensors);
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
