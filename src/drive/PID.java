package drive;

public class PID
{
	
	double PIDSpeed;
	double PIDAngle;
	long PIDTime;
	double PIDError;
    double PIDI;
    double PIDintegral;
    double PIDKi;
    double PIDKd;
    double PIDKp;
    double PIDErrorPrev;
    
    double Scurr;
    long Tcurr;
    double Ecurr;

    double dt;
    double output;
    double PIDCurrent;
    double Derivative;
    double SpeedCurrent;
    
    double deltaAngle;
    double maxSpeed;
    double PIDKpAngle;

    
    public void init()
    {
		PIDTime = System.currentTimeMillis () - 1000;
		PIDError = 0;
		dt = 0;
	    output = 0;
	    PIDCurrent = 0;
	    Derivative = 0;
	    SpeedCurrent = 0;
	    Scurr = 0;
	    Ecurr = 0;
	    Tcurr = 0;
	    PIDKd = 0;
	    PIDKp = .0001;
	    PIDErrorPrev = 0;
	    PIDI = 0;
	    PIDintegral = 0;
	    PIDSpeed = 0;
	    PIDAngle = 0;
	    PIDKi = PIDKp / 200;
	    
	    deltaAngle = 5;
	    maxSpeed = .5;
	    PIDKpAngle  = maxSpeed / deltaAngle;
    }
    
	public double UsePID(SensorManager sensors, double desiredSpeed)
	{
		Scurr = sensors.encoder.getEncoderRate();
		System.out.println("encoder rate: " + Scurr);
		Tcurr= System.currentTimeMillis();
		
		if (Tcurr - PIDTime > 50)
		{
					
		Ecurr = desiredSpeed - PIDSpeed;
		dt = (double)(Tcurr - PIDTime) / 1000;
		PIDintegral = PIDintegral + (Ecurr * (dt));
		//Derivative = ((Ecurr- PIDError) / dt);
		output = (PIDKp * Ecurr) + (PIDKi * PIDintegral) + (PIDKd * Derivative);
		//System.out.println("PIDKp: " + PIDKp);
		//System.out.println("Ecurr" + Ecurr);
		//System.out.println("output" + output);
		//System.out.println("dt: " + dt);
		
		PIDError = Ecurr;
		PIDTime = Tcurr;
		PIDSpeed = Scurr;
			
		/*if (output > 0.01)
			output = 0.01;
		if (output < -0.01)
			output = -0.01;*/
		
		//System.out.println("ayy lmao");
		//System.out.println("");
		
		
		PIDI = PIDI + output;
		
		//System.out.println("Output Total: " + PIDI);
		
		if(PIDI > .99)
			PIDI = .99;
		
		}
		
		return PIDI;
	}
	
	public double UsePIDAngle(SensorManager sensors, double desiredAngle)
	{
		Scurr = sensors.encoder.getEncoderAngle();
		//System.out.println("encoder rate: " + Scurr);
		Tcurr= System.currentTimeMillis();
		
		if (Tcurr - PIDTime > 50)
		{
					
		Ecurr = desiredAngle - PIDAngle;
		dt = (double)(Tcurr - PIDTime) / 1000;
		//PIDintegral = PIDintegral + (Ecurr * (dt));
		//Derivative = ((Ecurr- PIDError) / dt);
		output = (PIDKpAngle * Ecurr) + (PIDKi * PIDintegral) + (PIDKd * Derivative);
		//System.out.println("PIDKp: " + PIDKp);
		//System.out.println("Ecurr" + Ecurr);
		//System.out.println("output" + output);
		//System.out.println("dt: " + dt);
		
		PIDError = Ecurr;
		PIDTime = Tcurr;
		PIDAngle = Scurr;
			
		/*if (output > 0.01)
			output = 0.01;
		if (output < -0.01)
			output = -0.01;*/
		
		//System.out.println("ayy lmao");
		//System.out.println("");
		
		
		PIDI = PIDI + output;
		
		//System.out.println("Output Total: " + PIDI);
		
		if(PIDI > maxSpeed)
			PIDI = maxSpeed;
		
		if(PIDI < -maxSpeed)
			PIDI = -maxSpeed;
		
		}
		
		return PIDI;
	}
}
