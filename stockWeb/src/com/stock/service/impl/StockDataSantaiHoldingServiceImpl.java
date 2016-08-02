package com.stock.service.impl;

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
	
	
}
