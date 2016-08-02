package com.stock.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.stock.dao.StockDataSantaiHoldingDao;
import com.stock.entity.StockDataSantaiHolding;
@Repository("stockDataSantaiHoldingDaoImpl")
public class StockDataSantaiHoldingDaoImpl extends HibernateDaoSupport implements StockDataSantaiHoldingDao<StockDataSantaiHolding, Long> {
	@Autowired
	@Qualifier("sessionFactory")
	public void setSuperSessionFactory(SessionFactory factory){
		super.setSessionFactory(factory);
	}
	
}
