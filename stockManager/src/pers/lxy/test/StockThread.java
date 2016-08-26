package pers.lxy.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Timer;
import java.util.TimerTask;

import pers.lxy.database.DBManager;

/**
 * <p>TODO 类描述</p>
 *
 * @author  lxy
 * @date    2016年8月26日
 */
public class StockThread implements Runnable{
	
	public static final String GETURL = "http://hq.sinajs.cn/list=";
	
	private String stockCode;
	
	private String stockTable;
	
	public StockThread(){
		
	}
	public StockThread(String stockCode, String stockTable){
		this.stockCode = stockCode;
		this.stockTable = stockTable;
	}
	
	@Override
	public void run() {
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				try {
					getStockDate(stockCode, stockTable);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}, 1000, 5000);
	}
	private static void getStockDate(String stockCode, String stockTable) {
		if (stockCode != null && !stockCode.isEmpty() && stockTable != null && !stockTable.isEmpty()) {
			HttpURLConnection conn = null;
			BufferedReader reader = null;
			String url = GETURL + stockCode;
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
				storeStockData(str, stockTable);
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
}
