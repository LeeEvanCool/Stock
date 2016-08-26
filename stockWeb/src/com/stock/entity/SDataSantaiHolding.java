package com.stock.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class SDataSantaiHolding implements Serializable{
	private static final long serialVersionUID = 7920073118481970221L;
	
	private String name;
	private List<String> xdata = new ArrayList<String>();
	private List<Double> data = new ArrayList<Double>();
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<String> getXdata() {
		return xdata;
	}
	public void setXdata(List<String> xdata) {
		this.xdata = xdata;
	}
	
	public List<Double> getData() {
		return data;
	}
	public void setData(List<Double> data) {
		this.data = data;
	}
	public static SDataSantaiHolding convert(List<Object[]> stockDataSantaiHoldings){
		SDataSantaiHolding sDataSantaiHolding = new SDataSantaiHolding();
		sDataSantaiHolding.setName("三泰控股(002312)");
		List<String> xdateR = new ArrayList<String>(stockDataSantaiHoldings.size());
		List<Double> dateR = new ArrayList<Double>(stockDataSantaiHoldings.size());
		for (Object[] objects : stockDataSantaiHoldings) {
			String stockValueDate = (String) objects[0];
			String stockValueTime = (String) objects[1];
			BigDecimal stockValue = (BigDecimal) objects[2];
			xdateR.add(stockValueDate +" "+ stockValueTime);
			dateR.add(stockValue.doubleValue());
		}
		sDataSantaiHolding.setXdata(xdateR);
		sDataSantaiHolding.setData(dateR);
		return sDataSantaiHolding;
	}
}
