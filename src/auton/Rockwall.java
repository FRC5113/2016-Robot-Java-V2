package auton;

import drive.MotorManager;
import drive.SensorManager;

public class Rockwall extends DefenseFrame
{
	private int caseSelector = 0;
	private double speed = 0.6;
	private double time;
	private boolean move = true;
	private double previousAngle;
	
	public void update(SensorManager sensors, MotorManager dr)
	{
		switch(caseSelector)
		{
		case 0:
			sensors.resetGyroAngles();
			caseSelector = 1;
		
			break;
			
		case 1:
			forward(speed);
			time = System.currentTimeMillis();
			
			if(sensors.getGyroZAngle() > 10)
			{
				caseSelector = 2;
				previousAngle = sensors.getGyroZAngle();
			}
			
			break;
		
		case 2:
			if(sensors.getGyroZAngle() < previousAngle && System.currentTimeMillis() - time > 1500)
				caseSelector = 3;
			
			break;
			
		case 3:
			stop();
			move = false;
			System.out.println("Done!");
			
			break;
				
		}
		
		dr.tankDrive(-leftMotor, -rightMotor);
		dr.tiltArm(tiltMotorArm);
		dr.tiltShoot(tiltMotorShoot);
		
	}
}
