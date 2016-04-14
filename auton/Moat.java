package auton;

import drive.MotorManager;
import drive.SensorManager;

public class Moat extends DefenseFrame
{
	private int caseSelector = 0;
	private boolean selectorCheck = false;
	private double speed1 = 0.6;
	private double speed2 = 1;
	private double time;
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
			forward(speed1);
			
			if(sensors.getGyroZAngle() > 5)
			{
				caseSelector = 2;
				time = System.currentTimeMillis();
				previousAngle = sensors.getGyroZAngle();
			}
			
			break;
			
		case 2:
			forward(speed2);
			
			if(sensors.getGyroZAngle() <= previousAngle && System.currentTimeMillis() - time >= 1500)
				caseSelector = 3;
			
			break;
			
		case 3:
			stop();
			System.out.println("Done!");
			break;
		}
		
		
		dr.tankDrive(-leftMotor, -rightMotor);
		dr.tiltArm(tiltMotorArm);
		dr.tiltShoot(tiltMotorShoot);
	}
}
