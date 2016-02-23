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
	
	public double testValueDouble;
	
	public void init() 
	{
		minAngleBase = new DigitalInput(8);//fake
		maxAngleBase = new DigitalInput(9);//fake
		
		testValueDouble = .1;
		
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
		
		if(Math.abs(tiltValueBase) < 0.05)
			tiltValueBase = 0;
	
		dr.tiltArm(tiltValueBase);
	}
	
	public void tiltJoint(MotorManager dr, JoystickController monitor)
	{
		tiltValueJoint = monitor.getTiltJoint();
		
		if(Math.abs(tiltValueJoint) < 0.05)
			tiltValueJoint = 0;
		
		dr.tiltJoint(-tiltValueJoint);
	}
	
	public void moveHook(MotorManager dr, JoystickController monitor)
	{	
		if(monitor.getHookDrop())
			dr.moveHook(-0.9);
		else if(monitor.getHookLift())
			dr.moveHook(0.9);
		else
			dr.moveHook(0);
	}
	
	//Don't put this in until we get the actual robot
	//put it into a loop checking for top limit switch and until it's hit move hook up
	//call this in init so the hook is in the right spot every time
	public void reset()
	{
		
	}

}
