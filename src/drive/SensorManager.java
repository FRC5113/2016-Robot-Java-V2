package drive;

import controllers.JoystickController;
import edu.wpi.first.wpilibj.ADXL345_I2C;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.BuiltInAccelerometer;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.I2C.Port;
import edu.wpi.first.wpilibj.interfaces.Accelerometer.Range;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class SensorManager
{
	//adding the gyroscope and accelerometer into single class

	//	ADXL345_I2C accel;
	
	/* 1 Geared rev = 360 degrees
	 * 343 Motor revs = 1 geared rev
	 * 1 encoder rev = 1 motor rev
	 * 1 encoder rev = 7 pulses
	 */
	
	AnalogGyro gyroXY;
	AnalogGyro gyroZ;
	
	public EncoderManager encoder;
	
	private AnalogInput stringPot;
	private AnalogInput sonicRange;//SonicRAGE <---- If I ever somehow get partnered with Twitch, I am so making that a sub-emote.
	////private AnalogInput proximity;

	public void init()
	{
		//accelerometer
		/*accel = new ADXL345_I2C(null, null, 0);
		System.out.println("Test for toString of Accelerometer: " + accel.toString());
		System.out.println("Test for toString(NO toString) of Accelerometer: " + accel);
		 */
		//gyroscope

		
		//gyroXY = new AnalogGyro(0);
		//gyroXY.initGyro();
		//System.out.println("Gyro XY is now initiated\t" + gyroXY.getAngle());

		gyroZ = new AnalogGyro(1);
		gyroZ.initGyro();
		System.out.println("Gyro Z is now initiated\t" + gyroZ.getAngle());
		
		//gyroXY.calibrate();
		gyroZ.calibrate();
		System.out.println("The Gyro's are now calibrated.");
				 
		//encoder = new EncoderManager(0,1,7,343,360,1);


		stringPot = new AnalogInput(3);

		sonicRange = new AnalogInput(200);//sonicRage
		////proximity = new AnalogInput(2);
		encoder = new EncoderManager(0,1,7,343,360,1);

	}

	public void update()
	{
		//gyroXY.updateTable();
		gyroZ.updateTable();
		//SmartDashboard.putBoolean("Ball In", getBallIn());
	}
	
	public double getGyroVoltage(AnalogGyro gyro)
	{
		// v = read the voltage from the gyro
		//
		return 5;
	}

	public double getStringPot()
	{
		return stringPot.getValue();
	}

	public double getSonicRangeInches()
	{
		double voltage = sonicRange.getVoltage();
		double range = ((voltage * 1024) / 5) / 2.54;

		return range;
	}

	public double getGyroXYAngle()

	{
		return gyroXY.getAngle();
	}
	
	 public double getGyroZAngle()
	 {
		 return gyroZ.getAngle();
	 }
	 
	 public void resetGyroAngles()
	 {
		   //gyroXY.reset();
		   gyroZ.reset();
	 }

	 
	 public double getMedianRangefinder()
	 {
		 //make an array for the rangefinder of some odd number of inputs
		 //replace the last position with the most recent entry, so throw out the first and add the last
		 //median value of it and return it 
		 
		 
		 
		 return 0;
	 }
	 
	 /*public boolean getBallIn()
	 {
		 double range = proximity.getValue();
		 
		 if(range > 5)
			 return false;
		 else
			 return true;
	 }*/

}
		


