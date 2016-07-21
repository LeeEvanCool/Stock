package com.stock.service;

import java.io.Serializable;

import com.stock.entity.Book;

@SuppressWarnings("hiding")
public interface BookService<Book, Long extends Serializable> {
	/**
	 * 保存
	 * @param book void
	 */
	void save(Book book);
}
