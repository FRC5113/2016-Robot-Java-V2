package auton;

import drive.SensorManager;

public class RoughTerrain extends DefenseFrame
{
	private int caseSelector = 1;
	private double speed = 0.5;
	private double time;
	
	public void update(SensorManager sensors)
	{
		switch(caseSelector)
		{
		case 1:
			controller.forward(speed);
			time = System.currentTimeMillis();
			
			if(sensors.getGyroZAngle() > 5)
				caseSelector = 2;
			
			break;
		
		case 2:
			controller.forward(speed);
			
			if(sensors.getGyroZAngle() <= 5 && System.currentTimeMillis() - time > 1500)
				caseSelector = 3;
			
			break;
			
		case 3:
			controller.stop();
			System.out.println("Done!");
			
			break;
				
		}
	}
}
