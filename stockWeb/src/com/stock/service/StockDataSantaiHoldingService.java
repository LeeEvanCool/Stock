package com.stock.service;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@SuppressWarnings("hiding")
public interface StockDataSantaiHoldingService<StockDataSantaiHolding, Long extends Serializable> {

	List<Object[]> findList(Date startDate, Date endDate);
}
