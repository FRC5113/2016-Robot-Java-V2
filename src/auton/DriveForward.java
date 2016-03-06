package auton;

import drive.MotorManager;
import drive.SensorManager;

public class DriveForward extends DefenseFrame
{
	private int caseSelector = 0;
	private double speed = 0.4;
	private double time;
	private boolean move = true;
	
	public void update(SensorManager sensors, MotorManager dr)
	{
		switch(caseSelector)
		{
		case 0:
			time = System.currentTimeMillis();
			caseSelector = 1;
			
			break;
			
		case 1:
			forward(speed);
	
			if(System.currentTimeMillis() - time > 1000)
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
