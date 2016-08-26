package com.stock.controller;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.stock.entity.SDataSantaiHolding;
import com.stock.service.StockDataSantaiHoldingService;

@Controller("stockDataSantaiHoldingController")
public class StockDataSantaiHoldingController {

	@SuppressWarnings("rawtypes")
	@Resource(name = "stockDataSantaiHoldingServiceImpl")
	private StockDataSantaiHoldingService stockDataSantaiHoldingService;
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/findList", method = RequestMethod.POST)
	public @ResponseBody SDataSantaiHolding findList(Date startDate, Date endDate){
		if(startDate == null){
			startDate = new Date();
		}
		if(endDate == null){
			endDate = new Date();
		}
		if(startDate != null){
			startDate = getActualMinimumDate(startDate);
		}
		if(endDate != null){
			endDate = getActualMaximumDate(endDate);
		}
		List<Object[]> results = stockDataSantaiHoldingService.findList(startDate, endDate);
		SDataSantaiHolding sDataSantaiHolding = SDataSantaiHolding.convert(results);
		
		return sDataSantaiHolding;
	}
	/**
	 * 获得传入日期的最小时间
	 * 
	 * @param date
	 * @return Date
	 */
	public static Date getActualMinimumDate(Date date){
		if(date == null){
			date = new Date();
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, calendar.getActualMinimum(Calendar.HOUR_OF_DAY));
		calendar.set(Calendar.MINUTE, calendar.getActualMinimum(Calendar.MINUTE));
		calendar.set(Calendar.SECOND, calendar.getActualMinimum(Calendar.SECOND));
		calendar.set(Calendar.MILLISECOND, calendar.getActualMinimum(Calendar.MILLISECOND));
		date = calendar.getTime();
		return date;
	}
	/**
	 * 获得当前传入的最大日期
	 * 
	 * @param date
	 * @return Date
	 */
	public static Date getActualMaximumDate(Date date){
		if(date == null){
			date = new Date();
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, calendar.getActualMaximum(Calendar.HOUR_OF_DAY));
		calendar.set(Calendar.MINUTE, calendar.getActualMaximum(Calendar.MINUTE));
		calendar.set(Calendar.SECOND, calendar.getActualMaximum(Calendar.SECOND));
		calendar.set(Calendar.MILLISECOND, calendar.getActualMaximum(Calendar.MILLISECOND));
		date = calendar.getTime();
		return date;
	}
}
