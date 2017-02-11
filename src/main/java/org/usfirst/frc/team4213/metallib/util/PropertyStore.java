package org.usfirst.frc.team4213.metallib.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public enum PropertyStore implements KVStore {
	INSTANCE;
	
	private PropertyStore (){
		loadProperties();
	}
	
    private void loadProperties() {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("config.properties");

        if(null != inputStream) {
            Properties properties = new Properties();
            try {
                properties.load(inputStream);
                System.setProperties(properties);
            } catch (IOException e) {
                e.printStackTrace();
                System.exit(-1);
            }
        }
    }

	@Override
	public String getString(String key) {
		return System.getProperty(key);
	}
    
    @Override
	public double getDouble(String key) {
		return Double.parseDouble(getString(key));
	}

	@Override
	public int getInt(String key) {
		return Integer.parseInt(getString(key));
	}

	@Override
	public boolean getBool(String key) {
		return Boolean.parseBoolean(getString(key));
	}
    
    
}
