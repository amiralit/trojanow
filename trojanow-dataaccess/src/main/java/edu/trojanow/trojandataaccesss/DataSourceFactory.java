package edu.trojanow.trojandataaccesss;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;

public class DataSourceFactory {
	private static final String DRIVER_CLASS_NAME = "org.sqlite.JDBC";
	
	private static BasicDataSource DATA_SOURCE = null;
	
	private static final String CONNECTION_STRING = "jdbc:sqlite:/Users/Amirali/Documents/cs587/trojanow/trojanow-db/trojanow.db";
	
	public static synchronized DataSource getDataSource(){
		if (DATA_SOURCE == null){
			DATA_SOURCE = new BasicDataSource();
			DATA_SOURCE.setDriverClassName(DRIVER_CLASS_NAME);
			DATA_SOURCE.setUrl(CONNECTION_STRING);
		}
		
		return DATA_SOURCE;
	}

}
