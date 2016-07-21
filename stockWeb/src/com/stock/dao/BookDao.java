package com.stock.dao;

import java.io.Serializable;

import com.stock.entity.Book;

public interface BookDao<T, ID extends Serializable> {

	/**
	 * 保存
	 * @param book void
	 */
	void save(Book book);
	
}
