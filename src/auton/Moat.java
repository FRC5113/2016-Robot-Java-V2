package auton;

import drive.SensorManager;

public class Moat extends DefenseFrame
{
	private int caseSelector;
	private boolean selectorCheck = false;
	private double speed1 = 0.5;
	private double speed2 = 1;
	private double time;
	
	public void update(SensorManager sensors)
	{
		if(sensors.getGyroZAngle() <= 5 && !selectorCheck)
			caseSelector = 1;
		
		switch(caseSelector)
		{
		case 1:
			controller.forward(speed1);
			
			if(sensors.getGyroZAngle() > 5)
			{
				caseSelector = 2;
				time = System.currentTimeMillis();
			}
			
			break;
			
		case 2:
			controller.forward(speed2);
			
			if(sensors.getGyroZAngle() <= 5 && System.currentTimeMillis() - time >= 1500)
				caseSelector = 3;
			
			break;
			
		case 3:
			controller.stop();
			System.out.println("Done!");
			break;
		}
	}
}
