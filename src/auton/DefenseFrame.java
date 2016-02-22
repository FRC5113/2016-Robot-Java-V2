package auton;

import controllers.AutonController;
import drive.SensorManager;


public abstract class DefenseFrame
{	
	public abstract void update(SensorManager sensors);
	
	public AutonController controller;
	
	public boolean flagCompleted;
}
