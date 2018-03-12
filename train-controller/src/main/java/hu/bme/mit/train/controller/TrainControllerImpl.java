package hu.bme.mit.train.controller;

import java.util.Timer;
import java.util.TimerTask;

import hu.bme.mit.train.interfaces.TrainController;

public class TrainControllerImpl implements TrainController {

	private int step = 0;
	private int referenceSpeed = 0;
	private int speedLimit = 50;
	private int reverseSpeedLimit = 0;
	
	private Timer timer = new Timer();

	@Override
	public void followSpeed() {
		if (referenceSpeed < reverseSpeedLimit) {
			referenceSpeed = reverseSpeedLimit;
		} else {
		    if(referenceSpeed+step > reverseSpeedLimit) {
                referenceSpeed += step;
            } else {
		        referenceSpeed = reverseSpeedLimit;
            }
		}

		enforceSpeedLimit();
	}

	@Override
	public int getReferenceSpeed() {
		return referenceSpeed;
	}

	@Override
	public void setSpeedLimit(int speedLimit) {
		this.speedLimit = speedLimit;
		enforceSpeedLimit();
		
	}
	
	@Override
	public void setReverseSpeedLimit(int reverseSpeedLimit) {
		this.reverseSpeedLimit = reverseSpeedLimit;
		enforceSpeedLimit();

	}

	private void enforceSpeedLimit() {
		if (referenceSpeed > speedLimit) {
			referenceSpeed = speedLimit;
		}
		else if (referenceSpeed < reverseSpeedLimit)
			referenceSpeed = reverseSpeedLimit;
	}

	@Override
	public void setJoystickPosition(int joystickPosition) {
		this.step = joystickPosition;
		
		timer.schedule(new TimerTask() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
					referenceSpeed += step;
				}
		}, 1000);
	}

}
