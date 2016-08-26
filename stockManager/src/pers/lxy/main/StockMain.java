package pers.lxy.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import pers.lxy.database.DBManager;

public class StockMain {
	
	/** 基本请求url */
	public static final String GETURL = "http://hq.sinajs.cn/list=";
	/** 表名 */
	public static final String STOCKTABLEDAY = "xx_stock_day_data";
	/** 数据 */
	public static final String STOCKCODETABLE = "xx_stock_code_table_tmp";
	
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
		final List<Map<String, String>> stack = getStackValue();
		if(str.equals("1")){
			Timer timer = new Timer();
			timer.schedule(new TimerTask() {
				@Override
				public void run() {
					try {
						getStockDate(stack);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}, 1000, 5000);
		}else if(str.equals("2")){
			getStockDayDate(stack);
		}else{
			System.out.println("参数不正确");
		}
	}
	/**
	 * 获取股票信息
	 * 
	 * @return List<Map<String,String>>
	 */
	private static List<Map<String, String>> getStackValue(){
		Connection conn = null;
		PreparedStatement prepareStatement = null;
		ResultSet resultSet = null;
		List<Map<String, String>> result = new ArrayList<Map<String, String>>();
		try {
			conn = DBManager.getConnection();
			String sql = "select stock_code, stock_name, stock_table from " + STOCKCODETABLE + " where is_enable = '1' ";
			prepareStatement = conn.prepareStatement(sql);
			resultSet = prepareStatement.executeQuery();
			if(resultSet != null){
				while(resultSet.next()){
					String stockCode = resultSet.getString("stock_code");
					String stockName = resultSet.getString("stock_name");
					String stockTable = resultSet.getString("stock_table");
					if(stockCode != null && stockName != null && stockTable != null){
						Map<String, String> map = new HashMap<String, String>();
						map.put("stockCode", stockCode);
						map.put("stockName", stockName);
						map.put("stockTable", stockTable);
						result.add(map);
					}
				}
				return result;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (resultSet != null) {
					resultSet.close();
					resultSet = null;
				}
				if (prepareStatement != null) {
					prepareStatement.close();
					prepareStatement = null;
				}
				if (conn != null) {
					conn.close();
					conn = null;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	private static void getStockDayDate(List<Map<String, String>> stack) {
		if (stack != null && stack.size() > 0) {
			HttpURLConnection conn = null;
			BufferedReader reader = null;
			for (Map<String, String> stockMap : stack) {
				String url = GETURL + stockMap.get("stockCode");
				try {
					URL getUrl = new URL(url);
					conn = (HttpURLConnection) getUrl.openConnection();
					conn.connect();
					reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "gbk"));
					String lines;
					StringBuffer result = new StringBuffer();
					while ((lines = reader.readLine()) != null) {
						result.append(lines);
					}
					int startIndex = result.indexOf("\"") + 1;
					int endIndex = result.lastIndexOf("\"");
					String str = result.substring(startIndex, endIndex);
					System.out.println(str);
					storeStockDayData(str, stockMap.get("stockCode"));
					reader.close();
					conn.disconnect();
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					try {
						if (reader != null) {
							reader.close();
							reader = null;
						}
						if (conn != null) {
							conn.disconnect();
							conn = null;
						}
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
	private static void getStockDate(List<Map<String, String>> stack) {
		if (stack != null && stack.size() > 0) {
			HttpURLConnection conn = null;
			BufferedReader reader = null;
			for (Map<String, String> stockMap : stack) {
				String url = GETURL + stockMap.get("stockCode");
				try {
					URL getUrl = new URL(url);
					conn = (HttpURLConnection) getUrl.openConnection();
					conn.connect();
					reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "gbk"));
					String lines;
					StringBuffer result = new StringBuffer();
					while ((lines = reader.readLine()) != null) {
						result.append(lines);
					}
					int startIndex = result.indexOf("\"") + 1;
					int endIndex = result.lastIndexOf("\"");
					String str = result.substring(startIndex, endIndex);
					System.out.println(str);
					storeStockData(str, stockMap.get("stockTable"));
					reader.close();
					conn.disconnect();
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					try {
						if (reader != null) {
							reader.close();
							reader = null;
						}
						if (conn != null) {
							conn.disconnect();
							conn = null;
						}
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
	
	private static void storeStockData(String result, String stockTable){
		if (result != null && !result.isEmpty()) {
			String[] split = result.split(",");
			String stock_value_date = split[30];
			String stock_value_time = split[31];
			double stock_value = Double.parseDouble(split[3]);
			Connection conn = null;
			PreparedStatement prepareStatement = null;
			try {
				conn = DBManager.getConnection();
				String sql = "insert into "
						+ stockTable
						+ " (create_datetime, stock_value_date, stock_value_time, stock_value) values (?, ?, ?, ?)";
				prepareStatement = conn.prepareStatement(sql);
				
				java.util.Date utilDate = new java.util.Date();
				java.sql.Timestamp sqlDate = new java.sql.Timestamp(utilDate.getTime());
				prepareStatement.setTimestamp(1, sqlDate);
				prepareStatement.setString(2, stock_value_date);
				prepareStatement.setString(3, stock_value_time);
				prepareStatement.setDouble(4, stock_value);
				
				prepareStatement.execute();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					if (prepareStatement != null) {
						prepareStatement.close();
						prepareStatement = null;
					}
					if (conn != null) {
						conn.close();
						conn = null;
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
	private static void storeStockDayData(String result, String stockCode){
		if (result != null && !result.isEmpty()) {
			
			String[] split = result.split(",");
			String stock_value_date = split[30];
			String stock_value_time = split[31];
			String stock_code = stockCode;
			String stock_name = split[0];
			double today_open_value = Double.parseDouble(split[1]);
			double yesterday_close_value = Double.parseDouble(split[2]);
			double today_high_value = Double.parseDouble(split[4]);
			double today_low_value = Double.parseDouble(split[5]);
			Connection conn = null;
			PreparedStatement prepareStatement = null;
			try {
				conn = DBManager.getConnection();
				String sql = "insert into "
						+ STOCKTABLEDAY
						+ " (create_datetime, stock_value_date, stock_value_time, stock_code, stock_name, today_open_value, yesterday_close_value, today_high_value, today_low_value) values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
				prepareStatement = conn.prepareStatement(sql);
				
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
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					if (prepareStatement != null) {
						prepareStatement.close();
						prepareStatement = null;
					}
					if (conn != null) {
						conn.close();
						conn = null;
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
