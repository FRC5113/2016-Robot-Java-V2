package controllers;

import drive.MotorManager;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DigitalInput;

public class Arm
{
	
	private DigitalInput minAngleBase;
	private DigitalInput maxAngleBase;
	
	private double tiltValueBase;
	private double tiltValueJoint;
	private double hookMove;
	
	
	public void init() 
	{
		minAngleBase = new DigitalInput(8);//fake
		maxAngleBase = new DigitalInput(9);//fake
		
	}

	
	public void update(MotorManager dr, JoystickController monitor) 
	{
		tiltBase(dr, monitor);
		tiltJoint(dr, monitor);
		moveHook(dr, monitor);
	}
	
	//moves both the base and the joint of the arm at the same rate?
	//when the base is used the arm must be used as well
	public void tiltBase(MotorManager dr, JoystickController monitor)
	{
		tiltValueBase = monitor.getTiltArm();
	
		dr.tiltArm(tiltValueBase);
	}
	
	public void tiltJoint(MotorManager dr, JoystickController monitor)
	{
		tiltValueJoint = monitor.getTiltJoint();
		
		dr.tiltJoint(tiltValueJoint);
	}
	
	public void moveHook(MotorManager dr, JoystickController monitor)
	{
		if(monitor.getHookDrop())
			hookMove = -.5;
		else if(monitor.getHookLift())
				hookMove = .5;
		else
			hookMove = 0;
		
		dr.moveHook(hookMove);
	}
	
	//Don't put this in until we get the actual robot
	//put it into a loop checking for top limit switch and until it's hit move hook up
	//call this in init so the hook is in the right spot every time
	public void reset()
	{
		
	}

}
