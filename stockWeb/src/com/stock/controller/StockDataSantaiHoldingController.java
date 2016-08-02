package com.stock.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;

import com.stock.service.StockDataSantaiHoldingService;

@Controller("stockDataSantaiHoldingController")
public class StockDataSantaiHoldingController {

	@SuppressWarnings("rawtypes")
	@Resource(name = "stockDataSantaiHoldingServiceImpl")
	private StockDataSantaiHoldingService stockDataSantaiHoldingService;
	
}
