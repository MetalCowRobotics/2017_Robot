package org.usfirst.frc.team4213.metallib.controllers;

import edu.wpi.first.wpilibj.Joystick;

public abstract class CowGamepad extends Joystick {

	boolean[] previousStates;
	boolean[] toggleStates;
	
	public CowGamepad(int port) {
		super(port);
		previousStates = new boolean[20];
		toggleStates   = new boolean[20];
		
		for (short i = 0; i < 20; i++){
			previousStates[i] = false;
			toggleStates[i]   = false;
		}
	}

	public abstract boolean getButton(int n);

	public abstract double getLY();

	public abstract double getLX();

	public abstract double getRY();

	public abstract double getRX();

	public double getDpadX() {
		switch (getPOV()) {
		case -1:
			return 0;
		case 0:
		case 360:
			return 0;
		case 45:
		case 90:
		case 135:
			return 1;
		case 180:
			return 0;
		case 225:
		case 270:
		case 315:
			return -1;
		}
		return 0;
	}

	public double getDpadY() {
		switch (getPOV()) {
		case -1:
			return 0;
		case 315:
		case 0:
		case 45:
			return 1;
		case 90:
			return 0;
		case 135:
		case 180:
		case 225:
			return -1;
		case 270:
			return 0;
		}
		return 0;
	}

	public double getTankY() {
		return (getLY() + getRY()) / 2;
	}

	public double getTankOmega() {
		return (getLX() - getRY()) / 2;
	}

	/**
	 * Determine the top speed threshold Bumper buttons on the controller will
	 * limit the speed to the CRAWL value Trigger buttons on the controller will
	 * limit the speed to the SPRINT value Otherwise it will allow the robot a
	 * speed up to Normal max.
	 *
	 * @param topSpeedNormal
	 *            value double 0 to 1
	 * @param topSpeedCrawl
	 *            value double 0 to 1
	 * @param topSpeedSprint
	 *            value double 0 to 1
	 * @return topSpeedCurrent value double 0 to 1
	 */
	public double getThrottle(double topSpeedNormal, double topSpeedCrawl, double topSpeedSprint) {
		if (getButton(GamepadButton.RT) /* || getButton(GamepadButton.LT)*/)
			return topSpeedCrawl; // front-bottom triggers
		else if (getButton(GamepadButton.RB) /*|| getButton(GamepadButton.LB)*/)
			return topSpeedSprint; // fromt-bumper buttons
		else
			return topSpeedNormal;
	}

	// IDEA: Timeout on the rumble
	public void rumbleLeft(float amt) {
	}

	public void rumbleRight(float amt) {
	}
	
	public boolean getHeadingPadPressed() {
		return getRawButton(1) || getRawButton(2) || getRawButton(3) || getRawButton(4);
	}
	
	public double getHeadingPadDirection() {
		float x = 0, y = 0;
		if (getRawButton(1)) y -= 1;
		if (getRawButton(2)) x += 1;
		if (getRawButton(3)) x -= 1;
		if (getRawButton(4)) y += 1;
		return Math.toDegrees(Math.atan2(x, y));
	}
	
	public boolean getButtonTripped(int n) {
		if (getButton(n)) {
			if (previousStates[n]) {
				previousStates[n] = true;
				return false;
			} else {
				previousStates[n] = true;
				return true;
			}
			
		} else {
			previousStates[n] = false;
			return false;
		}
	}
	
	public boolean getButtonReleased(int n) {
		if (!getButton(n)) {
			if (previousStates[n]) {
				previousStates[n] = false;
				return true;
			} else {
				previousStates[n] = false;
				return false;
			}
			
		} else {
			previousStates[n] = true;
			return false;
		}
	}
	
	public boolean getButtonToggled(int n) {
		if (!getButton(n)) {
			previousStates[n] = false;
		} else if(previousStates[n]) {
			previousStates[n] = true;
		} else {
			previousStates[n] = true;
			toggleStates[n] = !toggleStates[n];
		}
		return toggleStates[n];
	}
}
