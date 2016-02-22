/**
 * 
 */
package controllers;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShooterSubSystem
{

	public ShooterSubSystem()
	{

		/*
		 * This is a lookup table for shooter subsystem. It contains Distance
		 * From Tower (meters) Distance From Tower (Feet) Velocity (m/s) Angle
		 * (degrees) 6.096074128 20 38 35
		 * 
		 * The values have been previously calculated using a spreadsheet found
		 * at http://www.chiefdelphi.com/media/papers/3188
		 * 
		 * Specifically, the rotational velocity of the shooter wheels and angle
		 * of the shooting platform (carriage) were calculated in 0.25ft
		 * increments from 2 through 20 feet.
		 * 
		 * Each section below is broken up into the same 0.25ft increments.
		 * 
		 * All that is needed is to provide a distance and the specific velocity
		 * and angle for that distance will be returned.
		 * 
		 * To insert data used as aiming parameters, specify the following:
		 * initialParms.add(new AimParameters(19.75, 38.0, 35.0)); where
		 * 
		 * 19.75 is the distance to the tower/target in feet 38.0 is the
		 * calculated velocity of the shooter wheels in meters/second 35.0 is
		 * the calculated tilt angle of the shooter carriage
		 * 
		 * It makes no difference whether the data inserted is unique or
		 * identical to previous values at 19.XX ft. If the user requests the
		 * data at 19.3 feet, the values at 19.25 will be returned.
		 */

		// This is the alternate implementation of the lookup table. It is
		// stored in an ArrayList, which
		// is simply an array. The index into the array is calculated and the
		// values returned.

		// Create the ArrayList of AimParameters
		aimParmsArrayMap = new ArrayList<AimParameters>();

		// Store distance in feet, wheel velocity, and tilt angle into the
		// initial parameters variable

		aimParmsArrayMap.add(new AimParameters(2.0, 30.0, 75.0));
		aimParmsArrayMap.add(new AimParameters(2.25, 30.0, 75.0));
		aimParmsArrayMap.add(new AimParameters(2.5, 24.0, 75.0));
		aimParmsArrayMap.add(new AimParameters(2.75, 24.0, 75.0));

		aimParmsArrayMap.add(new AimParameters(3.0, 22.0, 75.0));
		aimParmsArrayMap.add(new AimParameters(3.25, 22.0, 75.0));
		aimParmsArrayMap.add(new AimParameters(3.5, 22.0, 75.0));
		aimParmsArrayMap.add(new AimParameters(3.75, 22.0, 75.0));

		aimParmsArrayMap.add(new AimParameters(4.0, 22.0, 75.0));
		aimParmsArrayMap.add(new AimParameters(4.25, 23.0, 70.0));
		aimParmsArrayMap.add(new AimParameters(4.5, 23.0, 70.0));
		aimParmsArrayMap.add(new AimParameters(4.75, 23.0, 70.0));

		aimParmsArrayMap.add(new AimParameters(5.0, 23.0, 70.0));
		aimParmsArrayMap.add(new AimParameters(5.25, 23.0, 70.0));
		aimParmsArrayMap.add(new AimParameters(5.5, 23.0, 70.0));
		aimParmsArrayMap.add(new AimParameters(5.75, 23.0, 70.0));

		aimParmsArrayMap.add(new AimParameters(6.0, 24.0, 64.0));
		aimParmsArrayMap.add(new AimParameters(6.25, 24.0, 64.0));
		aimParmsArrayMap.add(new AimParameters(6.5, 24.0, 64.0));
		aimParmsArrayMap.add(new AimParameters(6.75, 24.0, 64.0));

		aimParmsArrayMap.add(new AimParameters(7.0, 24.0, 64.0));
		aimParmsArrayMap.add(new AimParameters(7.25, 24.0, 64.0));
		aimParmsArrayMap.add(new AimParameters(7.5, 24.0, 64.0));
		aimParmsArrayMap.add(new AimParameters(7.75, 24.0, 64.0));

		aimParmsArrayMap.add(new AimParameters(8.0, 26.0, 55.0));
		aimParmsArrayMap.add(new AimParameters(8.25, 26.0, 55.0));
		aimParmsArrayMap.add(new AimParameters(8.5, 26.0, 55.0));
		aimParmsArrayMap.add(new AimParameters(8.75, 26.0, 55.0));

		aimParmsArrayMap.add(new AimParameters(9.0, 26.0, 55.0));
		aimParmsArrayMap.add(new AimParameters(9.25, 26.0, 55.0));
		aimParmsArrayMap.add(new AimParameters(9.5, 26.0, 55.0));
		aimParmsArrayMap.add(new AimParameters(9.75, 26.0, 55.0));

		aimParmsArrayMap.add(new AimParameters(10.0, 27.0, 52.0));
		aimParmsArrayMap.add(new AimParameters(10.25, 27.0, 52.0));
		aimParmsArrayMap.add(new AimParameters(10.5, 27.0, 52.0));
		aimParmsArrayMap.add(new AimParameters(10.75, 27.0, 52.0));

		aimParmsArrayMap.add(new AimParameters(11.0, 29.0, 48.0));
		aimParmsArrayMap.add(new AimParameters(11.25, 29.0, 48.0));
		aimParmsArrayMap.add(new AimParameters(11.5, 29.0, 48.0));
		aimParmsArrayMap.add(new AimParameters(11.75, 29.0, 48.0));

		aimParmsArrayMap.add(new AimParameters(12.0, 31.0, 44.0));
		aimParmsArrayMap.add(new AimParameters(12.25, 31.0, 44.0));
		aimParmsArrayMap.add(new AimParameters(12.5, 31.0, 44.0));
		aimParmsArrayMap.add(new AimParameters(12.75, 31.0, 44.0));

		aimParmsArrayMap.add(new AimParameters(13.0, 31.0, 44.0));
		aimParmsArrayMap.add(new AimParameters(13.25, 31.0, 44.0));
		aimParmsArrayMap.add(new AimParameters(13.5, 31.0, 44.0));
		aimParmsArrayMap.add(new AimParameters(13.75, 31.0, 44.0));

		aimParmsArrayMap.add(new AimParameters(14.0, 33.0, 41.0));
		aimParmsArrayMap.add(new AimParameters(14.25, 33.0, 41.0));
		aimParmsArrayMap.add(new AimParameters(14.5, 33.0, 41.0));
		aimParmsArrayMap.add(new AimParameters(14.75, 33.0, 41.0));

		aimParmsArrayMap.add(new AimParameters(15.0, 33.0, 41.0));
		aimParmsArrayMap.add(new AimParameters(15.25, 33.0, 41.0));
		aimParmsArrayMap.add(new AimParameters(15.5, 33.0, 41.0));
		aimParmsArrayMap.add(new AimParameters(15.75, 33.0, 41.0));

		aimParmsArrayMap.add(new AimParameters(16.0, 33.0, 41.0));
		aimParmsArrayMap.add(new AimParameters(16.25, 36.0, 37.0));
		aimParmsArrayMap.add(new AimParameters(16.5, 36.0, 37.0));
		aimParmsArrayMap.add(new AimParameters(16.75, 36.0, 37.0));

		aimParmsArrayMap.add(new AimParameters(17.0, 36.0, 37.0));
		aimParmsArrayMap.add(new AimParameters(17.25, 36.0, 37.0));
		aimParmsArrayMap.add(new AimParameters(17.5, 36.0, 37.0));
		aimParmsArrayMap.add(new AimParameters(17.75, 36.0, 37.0));

		aimParmsArrayMap.add(new AimParameters(18.0, 38.0, 35.0));
		aimParmsArrayMap.add(new AimParameters(18.25, 38.0, 35.0));
		aimParmsArrayMap.add(new AimParameters(18.5, 38.0, 35.0));
		aimParmsArrayMap.add(new AimParameters(18.75, 38.0, 35.0));

		aimParmsArrayMap.add(new AimParameters(19.0, 38.0, 35.0));
		aimParmsArrayMap.add(new AimParameters(19.25, 38.0, 35.0));
		aimParmsArrayMap.add(new AimParameters(19.5, 38.0, 35.0));
		aimParmsArrayMap.add(new AimParameters(19.75, 38.0, 35.0));

		aimParmsArrayMap.add(new AimParameters(20.0, 38.0, 35.0));

	}

	/**
	 * Print the Aim Parameters Array
	 * 
	 */
	public void printAimParamsArray()
	{

		for (AimParameters aP : aimParmsArrayMap)
		{
			System.out.println(aP);
		}
	}

	/**
	 * Calculate an index into the shooter lookup table. Note if the number of
	 * divisions per foot or the minimum distance changes, the constants
	 * MIN_DIST and DIVS_PER_FOOT must change accordingly.
	 * 
	 * @param Double
	 *            requestedDistance - the linear distance to the target, not
	 *            considering height
	 * @return AimParameter object containing the shooter wheel velocity and
	 *         carriage tilt angle
	 */
	public AimParameters getAimParmFromArray(Double requestedDistance)
	{

		Double index = Math.floor((requestedDistance - MIN_DIST)
				* DIVS_PER_FOOT);
		return aimParmsArrayMap.get(index.intValue());
	}

        /** Update the shooter parameters map at a specific distance. This will allow the shooter to be
	 * updated without stopping and reloading the robot.
	 * @param distance - the distance in the map to be updated
	 * @param velocity - velocity to be updated
	 * @param angle - angle to be updated
	 * @return aP - the AimParameter at the index that was updated
	 */
	public AimParameters updateArrayMap(Double distance, Double velocity,
			Double angle)
	{
		// First calculate the index into the array list at the requested distance
		Double index = Math.floor((distance - MIN_DIST) * DIVS_PER_FOOT);
		
		// Create a temporary worker variable
		AimParameters aP = new AimParameters();

		// Set it with distance, velocity, and angle at the calculated index.
		// We cannot simply update the array with the .set method using the distance passed in 
		// because it may not fall on the required 0.25 foot distance increments.
		aP = aimParmsArrayMap.get(index.intValue());
		
		// Do not overwrite distance, only angle and velocity
		aP.setCarriageTiltAngle(angle);
		aP.setWheelRotationVelocity(velocity);
		
		// Now update the map with the new values
		aimParmsArrayMap.set(index.intValue(), aP);

		return aP;
	}

	public void adjustVelocity(double k)
	{
		for(int i = 0; i < aimParmsArrayMap.size(); i++)
		{	
			AimParameters aP = new AimParameters(aimParmsArrayMap.get(i).getDistanceFt(), 
												aimParmsArrayMap.get(i).getWheelRotationVelocity() * k, 
												aimParmsArrayMap.get(i).getCarriageTiltAngle() );
			aimParmsArrayMap.set(i, aP);
		}
	}
	
	public void storeShooterParams()
	{
		try {
    		f = new File("/home/lvuser/ShooterParameters.txt");
    		if(!f.exists()){
    			f.createNewFile();
    		}
			fw = new FileWriter(f);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	bw = new BufferedWriter(fw);
    	
    	try {
     		for (AimParameters aP : aimParmsArrayMap) {
     			
    			bw.write(aP.toString() + "\n");
    		}
    		
			bw.close();
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// Create the ArrayList of AimParameters for the lookup table
	private ArrayList<AimParameters> aimParmsArrayMap;
	private static final int MIN_DIST = 2;
	private static final int DIVS_PER_FOOT = 4;
	private File f;
	private BufferedWriter bw;
	private FileWriter fw;
	
	
	public int arrayLength()
	{
		return aimParmsArrayMap.size();
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{

		ShooterSubSystem shooter = new ShooterSubSystem();

		// shooter.updateArrayMap(16.5, 3.0, 4.0);
		shooter.printAimParamsArray();
		//shooter.correctVelocity(10);
		//shooter.printAimParamsArray();
	}

}
