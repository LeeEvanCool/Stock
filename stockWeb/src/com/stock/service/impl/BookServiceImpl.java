package com.stock.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.stock.dao.BookDao;
import com.stock.entity.Book;
import com.stock.service.BookService;
@Service("bookServiceImpl")
public class BookServiceImpl implements BookService<Book, Long> {
	@SuppressWarnings("rawtypes")
	@Resource(name = "bookDaoImpl")
	private BookDao bookDao;

	@Transactional
	public void save(Book book){
		bookDao.save(book);
	}
}
