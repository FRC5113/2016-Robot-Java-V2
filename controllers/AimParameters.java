package controllers;

/**
 * 
 */


/**
 * @author aricco
 *
 */
public class AimParameters {

	public AimParameters(){};
	public AimParameters (Double distanceFt, Double wheelRotationVelocity, Double carriageTiltAngle) {
		this.distanceFt = distanceFt;
		this.wheelRotationVelocity = wheelRotationVelocity;
		this.carriangTiltAngle = carriageTiltAngle;
		this.convertAndSetDistance(distanceFt);
	}
	
	public Double getWheelRotationVelocity() {
		return wheelRotationVelocity;
	}
	public void setWheelRotationVelocity(Double wheelRotationVelocity) {
		this.wheelRotationVelocity = wheelRotationVelocity;
	}
	public Double getCarriageTiltAngle() {
		return carriangTiltAngle;
	}
	public void setCarriageTiltAngle(Double carriageTiltAngle) {
		this.carriangTiltAngle = carriageTiltAngle;
	}
	
	public Double getDistanceFt() {
		return distanceFt;
	}

	public void setDistanceFt(Double distanceFt) {
		this.distanceFt = distanceFt;
	}

	public Double getDistanceM() {
		return distanceM;
	}

	@Override
	public String toString() {
		return "AimParameters [distanceFt=" + distanceFt  
				+ ", wheelRotationVelocity=" + wheelRotationVelocity 
				+ ", carriangTiltAngle=" + carriangTiltAngle 
				+ ", distanceM=" + distanceM
				+ "]" ;
	}
	

	/** This method is declared private so that users do not accidently set the distance in meters to feet.
	 * It is called internally by the constructor. 
	 * @param distanceFt - the distance to the target in feet. It will be converted to meters and stored in the distanceM class variable.
	 */
	private void convertAndSetDistance(Double distanceFt) {
		this.distanceM = distanceFt / 3.2808;
	}

	private Double distanceFt;
	private Double distanceM;
	private Double wheelRotationVelocity;
	private Double carriangTiltAngle;
}
