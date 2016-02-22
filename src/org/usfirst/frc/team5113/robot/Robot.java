
package org.usfirst.frc.team5113.robot;

import controllers.Arm;
import controllers.JoystickController;
import controllers.Shooter;
import controllers.ShooterSubSystem;
import drive.EncoderManager;
import drive.MotorManager;
import drive.SensorManager;
import edu.wpi.first.wpilibj.IterativeRobot;
//Auto-added, not sure if we actually need them... but whatever
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */

//This is the main part of the code. It is where the robot looks first
public class Robot extends IterativeRobot 
{
	//This was auto-generated. Not sure if we need it
    final String defaultAuto = "Default";
    final String customAuto = "My Auto";
    String autoSelected;
    SendableChooser chooser;
    
    
	private MotorManager motorManagers;// this gives us access to the Drive class
	private SensorManager sensors;
	private JoystickController controller;
	private Shooter shoot;
	private Arm arm;
	private ShooterSubSystem SSS;
	


	
	//ShooterSubSystem shooter = new ShooterSubSystem();
	
	
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() 
    {       
        controller = new JoystickController();
        controller.init();
        motorManagers = new MotorManager();
        motorManagers.init();
        shoot = new Shooter();
        shoot.init();
        arm = new Arm();
        arm.init();
        sensors = new SensorManager();
        sensors.init();
        SSS = new ShooterSubSystem();
    }
    
	/**
	 * This autonomous (along with the chooser code above) shows how to select between different autonomous modes
	 * using the dashboard. The sendable chooser code works with the Java SmartDashboard. If you prefer the LabVIEW
	 * Dashboard, remove all of the chooser code and uncomment the getString line to get the auto name from the text box
	 * below the Gyro
	 *
	 * You can add additional auto modes by adding additional comparisons to the switch structure below with additional strings.
	 * If using the SendableChooser make sure to add them to the chooser code above as well.
	 */
    public void autonomousInit() 
    {
    	sensors.encoder.resetEncoder();
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() 
    {
    	
    }

    public void teleopInit()
    {

    }
    
    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic()
    {
    	//The order of the update methods is important. Besides making a nice slope of periods, the motors and sensors have to update first
        controller.update(motorManagers);
        sensors.update();
        shoot.update(motorManagers, controller, sensors, SSS);
        arm.update(motorManagers, controller);
        //Test comment
        //Test comment 2
        

        
        	
		//System.out.println("Encoder Raw: " + sensors.getEncoderValues());
		//System.out.println("Encoder Count: " + sensors.getEncoderCount());
		//System.out.println("Encoder Rate of Rotation: " + sensors.getEncoderRate());
		//System.out.println("Encoder Distance: " + sensors.getEncoderDistance());
		//System.out.println("Degrees per Second: " + sensors.getEncoderAngularSpeed());
		
		//System.out.println("StringPot: " + sensors.getStringPot());
		//System.out.println("Ultrasonic Range Finder (Inches): " + sensors.getSonicRangeInches());
		//System.out.println("Servo: " + shoot.pusher.getAngle());
		
		sensors.resetGyroAngles(controller);
		
		//System.out.println("Gyro XY: " + sensors.getGyroXYAngle());
		//System.out.println("Gyro Z: " + sensors.getGyroZAngle()); 
		
		
        //System.out.println("wheel Angle: " + sensors.encoder.getEncoderAngle());

        
		//SmartDashboard.putNumber("Gyro XY", sensors.getGyroXYAngle());
		//SmartDashboard.putNumber("Gyro Z", sensors.getGyroZAngle());
		//SmartDashboard.putNumber("Distance", sensors.getSonicRangeInches());
		

    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() 
    {
    
    }
    
}
