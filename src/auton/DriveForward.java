package auton;

import drive.MotorManager;
import drive.SensorManager;

public class DriveForward extends DefenseFrame
{
	private int caseSelector = 1;
	private double speed = 0.4;
	private double time;
	private boolean move = true;
	
	public void update(SensorManager sensors, MotorManager dr)
	{
		switch(caseSelector)
		{
		case 1:
			forward(speed);
			time = System.currentTimeMillis();
			
			if(System.currentTimeMillis() - time > 500)
				caseSelector = 2;
			
			break;
		
		case 2:
			stop();
			move = false;
			System.out.println("Done!");
			
			break;
				
		}
		
		dr.tankDrive(-leftMotor, -rightMotor);
		
	}
}
