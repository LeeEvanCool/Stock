package com.stock.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.stock.dao.StockDataSantaiHoldingDao;
import com.stock.entity.StockDataSantaiHolding;
import com.stock.service.StockDataSantaiHoldingService;
@Service("stockDataSantaiHoldingServiceImpl")
public class StockDataSantaiHoldingServiceImpl implements StockDataSantaiHoldingService<StockDataSantaiHolding, Long> {
	@SuppressWarnings("rawtypes")
	@Resource(name = "stockDataSantaiHoldingDaoImpl")
	private StockDataSantaiHoldingDao stockDataSantaiHoldingDao;

	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> findList(Date startDate, Date endDate) {
		return stockDataSantaiHoldingDao.findList(startDate, endDate);
	}
	
}
