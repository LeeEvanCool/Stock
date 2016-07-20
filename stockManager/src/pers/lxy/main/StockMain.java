package pers.lxy.main;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Timer;
import java.util.TimerTask;

public class StockMain {
	
	/** 基本请求url */
	public static final String GETURL = "http://hq.sinajs.cn/list=";
	/** 编号 */
	public static final String STOCKCODE = "sz002312";
	/** 表名 */
	public static final String STOCKTABLE = "xx_stock_data_santai_holding";
	/** day表名 */
	public static final String STOCKTABLEDAY = "xx_stock_day_data";
	public static void main(String[] args) {
		if(args == null || args.length <= 0){
			System.out.println("方法带参数：1、实时数据。2、当天数据");
			return;
		}
		String str = args[0];
		if(str == null || str.isEmpty()){
			System.out.println("参数不正确");
			return;
		}
		if(str.equals("1")){
			Timer timer = new Timer();
			timer.schedule(new TimerTask() {
				@Override
				public void run() {
					try {
						getStockDate();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}, 1000, 5000);
		}else if(str.equals("2")){
			getStockDayDate();
		}else{
			System.out.println("参数不正确");
		}
	}
	private static void getStockDayDate() {
		String url = GETURL + STOCKCODE;
		try {
			URL getUrl = new URL(url);
			HttpURLConnection conn = (HttpURLConnection) getUrl.openConnection();
			conn.connect();
			BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "gbk"));
			String lines;
			StringBuffer result = new StringBuffer();
			while ((lines = reader.readLine()) != null) {
				result.append(lines);
			}
			// System.out.println(result);
			int startIndex = result.indexOf("\"") + 1;
			int endIndex = result.lastIndexOf("\"");
			String str = result.substring(startIndex, endIndex);
			System.out.println(str);
			storeStockDayData(str);
			reader.close();
			conn.disconnect();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private static void getStockDate() {
		String url = GETURL + STOCKCODE;
		try {
			URL getUrl = new URL(url);
			HttpURLConnection conn = (HttpURLConnection) getUrl.openConnection();
			conn.connect();
			BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "gbk"));
			String lines;
			StringBuffer result = new StringBuffer();
			while ((lines = reader.readLine()) != null) {
				result.append(lines);
			}
			// System.out.println(result);
			int startIndex = result.indexOf("\"") + 1;
			int endIndex = result.lastIndexOf("\"");
			String str = result.substring(startIndex, endIndex);
			System.out.println(str);
			storeStockData(str);
			reader.close();
			conn.disconnect();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void storeStockData(String result) throws Exception {
		if (result != null && !result.isEmpty()) {
			
			String[] split = result.split(",");
			String stock_value_date = split[30];
			String stock_value_time = split[31];
			double stock_value = Double.parseDouble(split[3]);
			
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/stock";
			String username = "root";
			String password = "";
			Connection conn = DriverManager.getConnection(url, username, password);
			String sql = "insert into "
					+ STOCKTABLE
					+ " (create_datetime, stock_value_date, stock_value_time, stock_value) values (?, ?, ?, ?)";
			PreparedStatement prepareStatement = conn.prepareStatement(sql);
			
			java.util.Date utilDate = new java.util.Date();
			java.sql.Timestamp sqlDate = new java.sql.Timestamp(utilDate.getTime());
			prepareStatement.setTimestamp(1, sqlDate);
			prepareStatement.setString(2, stock_value_date);
			prepareStatement.setString(3, stock_value_time);
			prepareStatement.setDouble(4, stock_value);
			
			prepareStatement.execute();
			
			if (prepareStatement != null) {
				prepareStatement.close();
			}
			if (conn != null) {
				conn.close();
			}
		}
	}
	private static void storeStockDayData(String result) throws Exception {
		if (result != null && !result.isEmpty()) {
			
			String[] split = result.split(",");
			String stock_value_date = split[30];
			String stock_value_time = split[31];
			String stock_code = STOCKCODE;
			String stock_name = split[0];
			double today_open_value = Double.parseDouble(split[1]);
			double yesterday_close_value = Double.parseDouble(split[2]);
			double today_high_value = Double.parseDouble(split[4]);
			double today_low_value = Double.parseDouble(split[5]);
			
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/stock";
			String username = "root";
			String password = "";
			Connection conn = DriverManager.getConnection(url, username, password);
			String sql = "insert into "
					+ STOCKTABLEDAY
					+ " (create_datetime, stock_value_date, stock_value_time, stock_code, stock_name, today_open_value, yesterday_close_value, today_high_value, today_low_value) values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement prepareStatement = conn.prepareStatement(sql);
			
			java.util.Date utilDate = new java.util.Date();
			java.sql.Timestamp sqlDate = new java.sql.Timestamp(utilDate.getTime());
			prepareStatement.setTimestamp(1, sqlDate);
			prepareStatement.setString(2, stock_value_date);
			prepareStatement.setString(3, stock_value_time);
			prepareStatement.setString(4, stock_code);
			prepareStatement.setString(5, stock_name);
			prepareStatement.setDouble(6, today_open_value);
			prepareStatement.setDouble(7, yesterday_close_value);
			prepareStatement.setDouble(8, today_high_value);
			prepareStatement.setDouble(9, today_low_value);
			
			prepareStatement.execute();
			
			if (prepareStatement != null) {
				prepareStatement.close();
			}
			if (conn != null) {
				conn.close();
			}
		}
	}
}
