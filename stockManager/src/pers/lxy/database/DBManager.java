package pers.lxy.database;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;

import pers.lxy.utils.DBResourceUtils;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * 
 * <p>
 * TODO C3P0
 * </p>
 *
 * @author lxy
 * @date 2016年8月26日
 */
public class DBManager {
	private static final DBManager instance = new DBManager();
	private static ComboPooledDataSource cpds = new ComboPooledDataSource(true);
	static {
		cpds.setDataSourceName("mydatasource");
		cpds.setJdbcUrl(DBResourceUtils.get("c3p0.jdbcUrl"));
		try {
			cpds.setDriverClass(DBResourceUtils.get("c3p0.driverClass"));
		} catch (PropertyVetoException e) {
			e.printStackTrace();
		}
		cpds.setUser(DBResourceUtils.get("c3p0.user"));
		cpds.setPassword(DBResourceUtils.get("c3p0.password"));
		cpds.setMaxPoolSize(Integer.valueOf(DBResourceUtils.get("c3p0.maxPoolSize")));
		cpds.setMinPoolSize(Integer.valueOf(DBResourceUtils.get("c3p0.minPoolSize")));
		cpds.setAcquireIncrement(Integer.valueOf(DBResourceUtils.get("c3p0.acquireIncrement")));
		cpds.setInitialPoolSize(Integer.valueOf(DBResourceUtils.get("c3p0.initialPoolSize")));
		cpds.setMaxIdleTime(Integer.valueOf(DBResourceUtils.get("c3p0.maxIdleTime")));
	}
	
	private DBManager() {
	}
	
	public static DBManager getInstance() {
		return instance;
	}
	
	public static Connection getConnection() {
		try {
			return cpds.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
