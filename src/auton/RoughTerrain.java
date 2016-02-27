package auton;

import drive.MotorManager;
import drive.SensorManager;

public class RoughTerrain extends DefenseFrame
{
	private int caseSelector = 1;
	private double speed = 0.5;
	private double time;
	private boolean move = true;
	
	public void update(SensorManager sensors, MotorManager dr)
	{
		switch(caseSelector)
		{
		case 1:
			forward(speed);
			time = System.currentTimeMillis();
			
			if(sensors.getGyroZAngle() > 5)
				caseSelector = 2;
			
			break;
		
		case 2:
			if(sensors.getGyroZAngle() <= 5 && System.currentTimeMillis() - time > 500)
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
