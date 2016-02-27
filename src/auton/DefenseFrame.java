package auton;

import controllers.AutonController;
import drive.MotorManager;
import drive.SensorManager;


public class DefenseFrame
{		
	public AutonController controller;
	
	public boolean flagCompleted;
	
	public double leftMotor = 0, rightMotor = 0;
	public double tiltMotorArm = 0, tiltMotorShoot = 0;

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
