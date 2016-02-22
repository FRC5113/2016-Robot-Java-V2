package auton;

import controllers.JoystickController;
import drive.MotorManager;
import drive.SensorManager;
import drive.EncoderManager;
																				// lol have fun with this
public class Drawbridge extends DefenseFrame									// it's a boi 
{																				// yeah das duh one
	private int caseSelector;													//      |
	private boolean selectorCheck = false;										//      |
	private double speed1 = 0.5;//test numbers									// 		|
	private double speed2 = 0.05;		//test numbers							//		V
	private double speed3 = 0.5; //test numbers
	private double jointdegrees1 = 20.0;  //test numbers
	private double jointdegrees2 = 10.0;//test numbers
	private double basedegrees1 = 30.0;//test numbers
	private double basedegrees2 = 20.0; //test numbers
	private double shooterangle = 10.0; //test numbers

	private double time;
	
	MotorManager dr;
	JoystickController joy;
	EncoderManager encoder;
	
	@Override
	public void update(SensorManager sensors)
	{
		if(sensors.getGyroZAngle() <= 5 && !selectorCheck)
			caseSelector = 1;
		
		switch(caseSelector)
		{

		case 1: //Approach the defense          ______
			controller.forward(speed1);    //   |     |
			//									V     | 
			if(sensors.getSonicRangeInches() == 6) //How far away from the drawbridge to stop
			{
				controller.stop();
				caseSelector = 2;
				//time = System.currentTimeMillis();
			} 
			
				break;
			
			case 2: // Manage arm movement and prepare to cross - steps 3-7 on sheet
				//Lower arm
				//Move backward
				//Lower arm further
				//Lower shooter
				//Raise arm
				//Move forward
			
			if (joy.getTiltArm() == basedegrees1)
				dr.tiltArm(0);
			else if (joy.getTiltArm() < basedegrees1)
				dr.tiltArm(0.5);  //plz test numbers
			else if (joy.getTiltArm() > basedegrees1)
				dr.tiltArm(-0.5);  //plz test numbers, idk if positve or negative
			
			if (joy.getTiltJoint() == jointdegrees1)
				dr.tiltJoint(0);
			else if (joy.getTiltJoint() < jointdegrees1)
				dr.tiltJoint(0.5);  //plz test numbers
			else if (joy.getTiltJoint() > jointdegrees1)
				dr.tiltJoint(-0.5);  //plz test numbers, idk if positve or negative
			
			if (joy.getTiltArm() == basedegrees1 && joy.getTiltJoint() == jointdegrees1)
			{
				dr.tiltArm(0);
				dr.tiltJoint(0);
				time = System.currentTimeMillis();
				caseSelector = 3;
			}
				
			break;
		
		case 3:
			
		controller.forward(-speed2);
		
		if (joy.getTiltArm() == basedegrees2)
			dr.tiltArm(0);
		else if (joy.getTiltArm() < basedegrees2)
			dr.tiltArm(0.5);  //plz test numbers
		else if (joy.getTiltArm() > basedegrees2)
			dr.tiltArm(-0.5);  //plz test numbers, idk if positve or negative
		
		if (joy.getTiltJoint() == jointdegrees2)
			dr.tiltJoint(0);
		else if (joy.getTiltJoint() < jointdegrees2)
			dr.tiltJoint(0.5);  //plz test numbers
		else if (joy.getTiltJoint() > jointdegrees2)
			dr.tiltJoint(-0.5);  //plz test numbers, idk if positve or negative
		
		if (joy.getTiltArm() == basedegrees2 && joy.getTiltJoint() == jointdegrees2 && System.currentTimeMillis() - time >= 3000)
		{
			dr.tiltArm(0);
			dr.tiltJoint(0);
			controller.stop();
			caseSelector = 4;
		}
		break;
			
		case 4:
			if (joy.getTiltArm() == 180-basedegrees2)
				dr.tiltArm(0);
			else if (joy.getTiltArm() < 180-basedegrees2)
				dr.tiltArm(0.5);  //plz test numbers
			else if (joy.getTiltArm() > 180-basedegrees2)
				dr.tiltArm(-0.5);  //plz test numbers, idk if positve or negative
			
			if (joy.getTiltJoint() == 180-jointdegrees2)
				dr.tiltJoint(0);
			else if (joy.getTiltJoint() < 180-jointdegrees2)
				dr.tiltJoint(0.5);  //plz test numbers
			else if (joy.getTiltJoint() > 180-jointdegrees2)
				dr.tiltJoint(-0.5);  //plz test numbers, idk if positve or negative
			
			if (joy.getTiltArm() == 180-basedegrees2 && joy.getTiltJoint() == 180-jointdegrees2)
			{
				dr.tiltArm(0);
				dr.tiltJoint(0);
			}
			
			encoder.setShooterAngle(shooterangle);
			
			if (joy.getTiltArm() == 180-basedegrees2 && joy.getTiltJoint() == 180-jointdegrees2 && encoder.getEncoderAngle() == shooterangle)
			{
				caseSelector=5;
			}
			
				break;
			
			
		case 5:
			controller.forward(speed3);
			
			if (sensors.getGyroZAngle() > 5)
			{
				caseSelector = 6;
			}
			
		case 6: 
			encoder.setShooterAngle(180-shooterangle);
			if (sensors.getGyroZAngle() < -5 && encoder.getEncoderAngle()==180-shooterangle)
			{
				caseSelector = 7;
			}
			break;
		
		case 7:
			controller.forward(speed3);
			if (sensors.getGyroZAngle() == 0)
			{
				caseSelector = 8;
			}
			break;
			
		case 8: // Stop

			
				controller.stop();
				System.out.println("Done!");
				break;
		}
	}
}
