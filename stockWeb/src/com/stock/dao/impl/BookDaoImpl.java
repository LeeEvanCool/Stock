package com.stock.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.stock.dao.BookDao;
import com.stock.entity.Book;
@SuppressWarnings("rawtypes")
@Repository("bookDaoImpl")
public class BookDaoImpl extends HibernateDaoSupport implements BookDao<BookDao, Long> {
	@Autowired
	@Qualifier("sessionFactory")
	public void setSuperSessionFactory(SessionFactory factory){
		super.setSessionFactory(factory);
	}
	@Override
	public void save(Book book){
		this.getHibernateTemplate().save(book);
	}
}
