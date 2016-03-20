package drive;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.RobotDrive;

public class MotorManager 
{
	/* ayylmao
	 * dank memes
	 * Copy Pasta
	 */
    
    
	private CANTalon leftMotorFront;
	private CANTalon leftMotorBack;
	private CANTalon rightMotorFront;
	private CANTalon rightMotorBack;
	private CANTalon leftTake;
	private CANTalon rightTake;
	
	//We are assuming this is a CAN
	private CANTalon tiltMotorShoot;
	
	//^Lol, same xD
	private CANTalon tiltMotorArmBase;
	
	//^ditto
	private CANTalon tiltMotorJoint;
	
	//The hook
	private CANTalon captainHook;
	private CANTalon ticTocCroc;
	
	RobotDrive roboDrive;
	
	public void init()
	{
		leftMotorFront = new CANTalon(13);//blaze it
		leftMotorFront.set(0);
		
		leftMotorBack = new CANTalon(12);//blaze it
		leftMotorBack.set(0);
		
		rightMotorFront = new CANTalon(1);//leet
		rightMotorFront.set(0);
		
		rightMotorBack = new CANTalon(0);//leet
		rightMotorBack.set(0);
		
		tiltMotorShoot = new CANTalon(2);//us
		tiltMotorShoot.set(0);
		
		tiltMotorArmBase = new CANTalon(8);//Bob Ross
		tiltMotorArmBase.set(0);
		
		tiltMotorJoint = new CANTalon(9);//not 9/11
		tiltMotorJoint.set(0);
		
		leftTake = new CANTalon(7);//not quite 9000
		leftTake.set(0);
		
		rightTake = new CANTalon(6);//over 9000
		rightTake.set(0);
		
		captainHook = new CANTalon(14);//What is a pirate's favorite letter?
		captainHook.set(0);			  //"It's p because without it he'd be irate" 
																//Wang 2016
		
		ticTocCroc = new CANTalon(3);//He's coming for Hook...
		ticTocCroc.set(0);
		
		roboDrive = new RobotDrive(rightMotorBack, leftMotorBack);
	}
	
	public void tankDrive(double leftValue, double rightValue)//HOW DO I GET MEMES?!??!?
	{	
		double leftPower = -leftValue;
		double rightPower = rightValue;
		
		leftMotorFront.set(leftPower);
		leftMotorBack.set(leftPower);
		rightMotorFront.set(rightPower);
		rightMotorBack.set(rightPower);
		
		//leftTake.enableBrakeMode(false);
		//leftTake.set(leftPower);
		//tiltMotorJoint.set(leftValue);
		//rightTake.set(rightPower);
		//tiltMotorArmBase.set(leftPower);
		
		//rightTake.set(.6);
		//tiltMotorShoot.set(rightPower);
		//System.out.println("Left Power: " + leftPower);
		//System.out.println("Right Power: " + rightPower);
	}
	
	public void tiltShoot(double tiltValue)
	{
		tiltMotorShoot.set(tiltValue);
	}
	
	public void tiltArm(double tiltValue)
	{
		tiltMotorArmBase.set(tiltValue);
		
	}
	
	public void tiltJoint(double tiltValue)
	{
		tiltMotorJoint.set(tiltValue);
	}
	
	public void spinShooterWheels(double leftWheel, double rightWheel)
	{
		leftTake.set(leftWheel);
		rightTake.set(rightWheel);
	}
	
	public void moveHook(double speed)
	{
		captainHook.set(speed);
		ticTocCroc.set(-speed);
	}
	
	
}