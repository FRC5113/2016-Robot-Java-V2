package auton;

import drive.SensorManager;
import drive.MotorManager;
public class ChivalDeFrise extends DefenseFrame
{
	private int caseSelector;
	private boolean selectorCheck = false;
	private double speed1 = 0.3;
	private double speed2 = 0.4;
	private double speed3 = 0.8;
	private double degrees = 20.0;
	private double time;
	
	
	
	public void update(SensorManager sensors, MotorManager dr)
	{
		if(sensors.getGyroZAngle() <= 5 && !selectorCheck)
			caseSelector = 1;
		
		switch(caseSelector)
		{
		case 1: //Approach the defense
			forward(speed1);
			
			if(sensors.getGyroZAngle() > 5)
			{
				stop();
				caseSelector = 2;
				time = System.currentTimeMillis();
			}
			
			break;
			
		case 2: 
				//Lower shooting mechanism here
			shootswing(speed3);
			
			//if(sensors.getGyroZAngle() <= 5 && System.currentTimeMillis() - time >= 1500)
			if(sensors.getLowerLimit())
			{
				forward(speed2);
				shootswing(-speed3);
			}
			
			if(sensors.getGyroZAngle() <= 5 && System.currentTimeMillis() - time >= 1500)
				caseSelector = 3;
			
			break;
			
		case 3: // Stop
			shootswing(0);
			stop();
			System.out.println("Done!");
			break;
		}
		
		dr.tankDrive(-leftMotor, -rightMotor);
		dr.tiltArm(tiltMotorArm);
		dr.tiltShoot(tiltMotorShoot);
		
	}
}
