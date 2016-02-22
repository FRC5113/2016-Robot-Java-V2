package auton;

import drive.SensorManager;
import drive.MotorManager;
public class ChivalDeFrise extends DefenseFrame
{
	private int caseSelector;
	private boolean selectorCheck = false;
	private double speed1 = 0.5;
	private double speed2 = 1.0;
	private double speed3 = 0.5;
	private double degrees = 20.0;
	private double time;
	
	
	@Override
	public void update(SensorManager sensors)
	{
		if(sensors.getGyroZAngle() <= 5 && !selectorCheck)
			caseSelector = 1;
		
		switch(caseSelector)
		{
		case 1: //Approach the defense
			controller.forward(speed1);
			
			if(sensors.getGyroZAngle() > 5)
			{
				caseSelector = 2;
				time = System.currentTimeMillis();
			}
			
			break;
			
		case 2: // Drive over the defense
			controller.forward(speed2);
				//Lower shooting mechanism here
				controller.shootswing(speed3);
			
			if(sensors.getGyroZAngle() <= 5 && System.currentTimeMillis() - time >= 1500)
				//Raise shooting mechanism here
				controller.shootswing(-speed3);
			
				caseSelector = 3;
			
			break;
			
		case 3: // Stop
			controller.stop();
			System.out.println("Done!");
			break;
		}
		
	}
}
