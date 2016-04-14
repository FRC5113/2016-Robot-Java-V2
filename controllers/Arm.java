package controllers;

import drive.MotorManager;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Arm
{
	
	private DigitalInput minAngleBase;
	private DigitalInput maxAngleBase;
	
	private double tiltValueBase;
	private double tiltValueJoint;
	private double hookMove;
	private double temHookValue;
	
	public double testValueDouble;
	
	public void init() 
	{
		minAngleBase = new DigitalInput(8);//fake
		maxAngleBase = new DigitalInput(9);//fake
		
		testValueDouble = .1;
		
		temHookValue = 3;
		
	}

	
	public void update(MotorManager dr, JoystickController monitor) 
	{
		//tiltBase(dr, monitor);
		tiltJoint(dr, monitor);
		moveHook(dr, monitor);
		
		//System.out.println("Hook Axis: " + monitor.getHook());
		//System.out.println("Arm Axis: " + monitor.getTiltJoint());
	}
	
	//moves both the base and the joint of the arm at the same rate?
	//when the base is used the arm must be used as well
	/*public void tiltBase(MotorManager dr, JoystickController monitor)
	{
		tiltValueBase = monitor.getTiltArm();
		
		if(Math.abs(tiltValueBase) < 0.05)
			tiltValueBase = 0;
	
		dr.tiltArm(tiltValueBase / 3.5);
	}*/
	
	public void tiltJoint(MotorManager dr, JoystickController monitor)
	{
		tiltValueJoint = monitor.getTiltJoint();
		
		if(Math.abs(tiltValueJoint) < 0.3)
			tiltValueJoint = 0;
		
		dr.tiltJoint(-tiltValueJoint / 2.5);
		
		//System.out.println("ARM JOINT VALUE: " + tiltValueJoint);
	}
	
	
	//We need to get full extension between 5 and 10 seconds
	public void moveHook(MotorManager dr, JoystickController monitor)
	{	
		//if(Math.abs(temHookValue) < .7 && (monitor.getHookDrop() || monitor.getHookLift()))
			//temHookValue += .01;
		
		if(monitor.getHookLift())
			dr.moveHook(0.99);
		else if(monitor.getHook() > 0.05 || monitor.getHook() < -0.05)
			dr.moveHook(Math.abs(monitor.getHook()));
		else
			dr.moveHook(0);
		
		
		//if(monitor.getHook() > 0.05 || monitor.getHook() < -0.05)
		//	dr.moveHook(Math.abs(monitor.getHook()));
		//else
		//	dr.moveHook(0);
	}
	
	//Don't put this in until we get the actual robot
	//put it into a loop checking for top limit switch and until it's hit move hook up
	//call this in init so the hook is in the right spot every time
	public void reset()
	{
		
	}

}
