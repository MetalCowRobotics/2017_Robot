package org.usfirst.frc.team4213.metallib.util;

public interface KVStore {
	public double getDouble(String key);
	public int getInt(String key);
	public String getString(String key);
	public boolean getBool(String key);
}
