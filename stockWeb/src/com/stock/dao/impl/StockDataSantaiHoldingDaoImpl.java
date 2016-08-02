package com.stock.dao.impl;

import java.util.Date;
import java.util.List;

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

	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> findList(Date startDate, Date endDate) {
		
		String sql = "SELECT u.stockValueDate, u.stockValueTime , min(u.stockValue) FROM StockDataSantaiHolding u WHERE u.createDatetime >= ? and u.createDatetime <= ? group by u.stockValueDate, u.stockValueTime "; 
		
		List<Object[]> result = this.getHibernateTemplate().find(sql, startDate, endDate);
		
		if(result != null && result.size() > 0){
			return result;
		}
		return null;
	}
	
}
