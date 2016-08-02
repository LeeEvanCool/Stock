package com.stock.dao;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@SuppressWarnings("hiding")
public interface StockDataSantaiHoldingDao<StockDataSantaiHolding, Long extends Serializable> {
	
	List<Object[]> findList(Date startDate, Date endDate);
	
}
