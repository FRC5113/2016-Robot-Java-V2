package controllers;

import drive.MotorManager;
import drive.PID;
import drive.SensorManager;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Shooter
{
	//Not sure if "DigitalInput" is the correct object
	//it is -Reed
	private DigitalInput maxAngle;
	
	public Servo pusher;//rebel
	
	private boolean intake;
	private boolean shootLow;
	private boolean tiltDown;
	private boolean tiltUp;
	
	private double lowValue;
	private double tiltValue;
	private double timer;
	private int servoDir;
	
	private boolean autoShootToggle;
	private double debounce;
	private double debounce2;
	private double debounce3;
	
	private double distance;
	private double index;
	private double angle;
	private double velocity;
	private double pulsesPerSecond;
	private AimParameters whereToShoot;
	private PID pid;
	private double tiltSpeed;
	
	private double driverSpinWheelsSpeed;
	private double shooterMultiplier;
	private double tiltMultiplier;
	
	private double desiredAngle;
	
	private Relay pusher2;


	
	//instantiate objects with obviously fake ports
	//Done
	public void init()
	{
		//maxAngle = new DigitalInput(2);//fake
		
		pusher = new Servo(0);//real
		pusher.setAngle(179);
		
		servoDir = 1;
		
    	autoShootToggle = false; 
    	debounce = -5000;
    	debounce2 = -2000;
    	
    	pid = new PID();
    	pid.init();
    	
    	driverSpinWheelsSpeed = .8;
    	shooterMultiplier = 1;
    	
    	distance = 15;
    	desiredAngle = 0;
    	
    	pusher2 = new Relay(0);
    	
    	
    	
	}
	
	//It requires dr for motor access, monitor for stick access and sensors for sensor access (Especially for auto-shoot)
	public void update(MotorManager dr, JoystickController monitor, SensorManager sensors, ShooterSubSystem SSS)
	{
		
		
		if(monitor.getEStop())
		{
			if(!autoShootToggle)
				manualDriverShootAndAim(dr, monitor);
		}
		else
		{
			manualTilt(dr, monitor, sensors);
			manualWheels(dr, monitor);
		}
		//Shooter Plan B just in case everything goes 
		
		
		//Calling autoshoot
        // if "A" is pressed & it has been at least 5 seconds since last time "A" has been pressed
        if(monitor.getActivateAutoShoot() && System.currentTimeMillis() - debounce > 5000) 
        {
        	debounce = System.currentTimeMillis();
        	autoShootToggle = !autoShootToggle;
        }
        
        if(autoShootToggle)
        {
        	if(monitor.getTiltUpShoot() > 0.5 && System.currentTimeMillis() - debounce2 > 2000)
        	{
        		debounce2 = System.currentTimeMillis();
        		shooterMultiplier += .1;
        	}
        	else if(monitor.getTiltDownShoot() > 0.5 && System.currentTimeMillis() - debounce2 > 2000)
        	{
        		debounce2 = System.currentTimeMillis();
        		shooterMultiplier -= .1;
        	}
        }
        
        
        
        if(autoShootToggle)
        	autoShoot(SSS, sensors, dr, pid, monitor);
		
		
		// speed = pid.UsePID(sensors, 750);
		//System.out.println("PID: " + speed);
		//dr.tankDrive(.4, .4);
		
		/*s e r v o 
		  e 
		  r
		  v
		  o		  */
		
    	
        //pusher2.set(Relay.Value.kOn);
    	
		switch(servoDir)
		{
			case 1:
				
				if(monitor.getServo() || monitor.getDriverServoShoot())//RIP rumble 2016
				{
					pusher.setAngle(0);
					//pusher2.set(Relay.Value.kOn);
					
					servoDir = 2;
					timer = System.currentTimeMillis();
				}
			
				break;
				
			case 2:
				if(pusher.getAngle() < 5 && System.currentTimeMillis() - timer > 750)
				{
					pusher.setAngle(179);
					//pusher2.set(Relay.Value.kOff);
					servoDir = 1;
				}
				
				break;
		}
		
	}
	
	//Get value of buttons for the intake and the shoot low
	//then set an appropriate value for the respective motor(s)
	public void manualWheels(MotorManager dr, JoystickController monitor)
	{
		if(monitor.getIntake())
		{
			dr.spinShooterWheels(-.99, .99);
		}
		else if(monitor.getShootLow())
		{
			dr.spinShooterWheels(.7,  -.7);
		}
		else
			dr.spinShooterWheels(0, 0);
	}
	
	public void autoShoot(ShooterSubSystem SSS, SensorManager sensors, MotorManager dr, PID pid, JoystickController driverStick)//Now is the time to do this. 
	{
		if(driverStick.getAnyTestValue() && System.currentTimeMillis() - debounce3 > 1000) 
        {
        	debounce3 = System.currentTimeMillis();
        	distance = driverStick.testValue(distance, 1);
        	
        }
		
		SmartDashboard.putNumber("Distance", distance);
		//distance = sensors.getSonicRangeInches();
		whereToShoot = SSS.getAimParmFromArray(distance);
		
		angle = whereToShoot.getCarriageTiltAngle();
		tiltSpeed = sensors.encoder.setShooterAngle(angle, sensors);
		//System.out.println("Tilt Speed: " + tiltSpeed);
		SmartDashboard.putNumber("Tilt Speed", tiltSpeed);
		
		if(sensors.getLowerLimit())
		{
			if(tiltSpeed > 0 )
				tiltSpeed = 0;
		}
		
		dr.tiltShoot(tiltSpeed);
		
		if(driverStick.getShootLow())
		{
			if(driverStick.getTiltUpShoot() > 0.05)
        	{
				dr.spinShooterWheels(driverStick.getTiltUpShoot(),  -driverStick.getTiltUpShoot());
				//System.out.println("Wheel Speed: " + driverStick.getTiltUpShoot());
				SmartDashboard.putNumber("Wheel Speed", driverStick.getTiltUpShoot());
        	}
        		
		}
		//dr.spinShooterWheels(pid.UsePID(sensors, convertShooterSpeed(SSS, sensors)), 
				//pid.UsePID(sensors, convertShooterSpeed(SSS, sensors))); //one will be negative
	}
	
	//We assume that to tilt down we have a negative value and positive to tilt up
	//We will also experimentally figure out what value to set the motor speed to, we will use .5 for now
	public void manualTilt(MotorManager dr, JoystickController monitor, SensorManager sensors)
	{
		
		if(monitor.getTiltDownShoot() > 0.05)
		{
			//SmartDashboard.putNumber("Tilt Down Shoot", monitor.getTiltDownShoot());
			desiredAngle -= (monitor.getTiltDownShoot() * 1);
		}
		
		
		if(monitor.getTiltUpShoot() > 0.05)
		{
			desiredAngle += (monitor.getTiltUpShoot() * 1);
		}
		
		if(desiredAngle < 0 && !monitor.getDriverTiltUp() && !monitor.getDisableMaxAngle()) //SELF NOTE RIGHT HERE
			desiredAngle = 0;
		
		if(monitor.getDriverTiltDown())
		{
			sensors.resetEncoder();
			desiredAngle = 0;
		}
		
		if(desiredAngle > 65)
			desiredAngle = 65;
		
		//SmartDashboard.putNumber("Tilt Up Shoot", monitor.getTiltUpShoot());
		
		System.out.println("Desired Angle: " + desiredAngle);
		
		
		
		SmartDashboard.putBoolean("Min Angle", sensors.getLowerLimit());
		
		tiltValue = pid.UsePIDAngle(sensors, desiredAngle);
		
		if(sensors.getLowerLimit())
		{
			if(tiltValue > 0 )
				tiltValue = 0;
		}
		
		SmartDashboard.putNumber("TiltValue", tiltValue);
		System.out.println("Tilt Value: " + tiltValue);
		
		
		dr.tiltShoot(tiltValue);
	}
	
	public void manualDriverShootAndAim(MotorManager dr, JoystickController monitor)
	{

		/*
		driverSpinWheelsSpeed = monitor.getDriverSpeedWheels();
		
		if(driverSpinWheelsSpeed > .8)
			driverSpinWheelsSpeed = .8;
		*/
		
		if(monitor.getDriverTiltUp())
		{
			dr.tiltShoot(.35);
		}
		else if(monitor.getDriverTiltDown())
			{
				dr.tiltShoot(-.35);
			}
		else
			dr.tiltShoot(0);
		
		if(monitor.getDriverShoot())
		{
			dr.spinShooterWheels(-driverSpinWheelsSpeed, driverSpinWheelsSpeed);
		}
		else
		{
			dr.spinShooterWheels(0, 0);
		}
	}
	
	public double convertShooterSpeed(ShooterSubSystem SSS, SensorManager sensors)
	{
		/* wheelVelocity = (N(encoder pulses per revolution) * S(ball velocity))/(2 pi * radius)
		 * N = 7 (encoder pules per revolution)
		 * S = ball velocity
		 * R = 0.167 feet (2 inches, radius of wheel)
		 */
		
		distance = sensors.getSonicRangeInches() / 12;
		whereToShoot = SSS.getAimParmFromArray(distance);
		velocity = whereToShoot.getWheelRotationVelocity();
		
		pulsesPerSecond = (7 * velocity * shooterMultiplier) / (6.28318 * 0.167);
		
		return pulsesPerSecond;
	}
}
