package org.usfirst.frc.team4213.metallib.util;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public enum SmartDashStore implements KVStore {
	
	INSTANCE;
	
	@Override
	public double getDouble(String key) {
		return SmartDashboard.getDouble(key,0);
	}

	@Override
	public int getInt(String key) {
		return SmartDashboard.getInt(key,0);
	}

	@Override
	public String getString(String key) {
		return SmartDashboard.getString(key, "");
	}

	@Override
	public boolean getBool(String key) {
		return SmartDashboard.getBoolean(key,false);
	}


}
