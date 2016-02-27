package drive;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class EncoderManager 
{
	
	private Encoder encoder;
	//SRXMagEncoder is the shooter wheels encoder
	private final double ENCODER_PULSES_PER_REV; //7
	private final double MOTOR_TO_GEAR_RATIO;//343
	private final double DEGREES_PER_GEAR_REV;//360
	private final double MOTOR_REVS_PER_ENCODER_REV;//1
	private static double DEGREES_PER_ENCODER_PULSE; // 0.1499375260308204914618908788005
	
	private double encoderAngle;
	
	PID pid;
	//Shooter wheels are 2 inch radius
	
	public EncoderManager(int portOne, int portTwo, double pulsesPerRev, double motorGearRatio, 
							double degreesPerGear, double motorRevPerEnc)
	{
		ENCODER_PULSES_PER_REV = pulsesPerRev;
		MOTOR_TO_GEAR_RATIO = motorGearRatio;
		DEGREES_PER_GEAR_REV = degreesPerGear;
		MOTOR_REVS_PER_ENCODER_REV = motorRevPerEnc;
		
		DEGREES_PER_ENCODER_PULSE = (MOTOR_REVS_PER_ENCODER_REV/ENCODER_PULSES_PER_REV) * 
				(MOTOR_REVS_PER_ENCODER_REV/MOTOR_TO_GEAR_RATIO) *
				(DEGREES_PER_GEAR_REV/MOTOR_REVS_PER_ENCODER_REV);
		
		encoder = new Encoder(portOne, portTwo);
		pid = new PID();
	}
	
	public int getEncoderValues()
	{
		//Not sure which one we should use.
		//Returns raw value from the encoder.
		return encoder.getRaw();
		//Returns the current count from the encoder.
		//return encoder.get();
	}

	public double getEncoderDistance()
	{
		return encoder.getDistance();
	}

	public int getEncoderCount()
	{
		return encoder.get();
	}

	public double getEncoderRate()
	{
		return encoder.getRate();
	}public void getDistancePerPulse(double DistancePerPulse)
	{
		encoder.setDistancePerPulse(0.5);
	}
	
	public double getEncoderAngularSpeed()
	{
		double PulsePerRotation = 720;
		double Rate = encoder.getRate();
		double DistancePerPulse = 0.5;
		double AngularSpeed = PulsePerRotation * Rate * DistancePerPulse;
		return AngularSpeed;
	}
	

	public void resetEncoder()
	{
		encoder.reset();
	}
	
	public double getEncoderAngle()
	{
		encoderAngle = encoder.get() * DEGREES_PER_ENCODER_PULSE;
		
		return encoderAngle;
	}
	
	public double setShooterAngle(double requestedAngle, SensorManager sensors)
	{
		 //return pid.UsePIDAngle(sensors, requestedAngle);
		SmartDashboard.putNumber("Sensor Angle", sensors.encoder.getEncoderAngle());
		if(Math.abs(requestedAngle - sensors.encoder.getEncoderAngle()) > 5)
		{
			if(requestedAngle > sensors.encoder.getEncoderAngle())
			{
				return 0.5;			//plz test numbers
		 	}
			else if(requestedAngle < sensors.encoder.getEncoderAngle())
		 	{
				return -0.5;			 //plz test numbers
		 	}
		}
		else
		{
			return 0;
		}
		
		return 0;
	}
}
