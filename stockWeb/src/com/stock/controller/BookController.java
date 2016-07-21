package com.stock.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.stock.entity.Book;
import com.stock.service.BookService;

@Controller("bookController")
@RequestMapping("/test")
public class BookController {

	@Resource(name = "bookServiceImpl")
	private BookService bookService;

	@RequestMapping("saveBook.action")
	public void save(){
		Book book = new Book();
		book.setName("java编程思想");
		book.setPrice(23.5d);
		book.setSn("201607210354");
		
		bookService.save(book);
	}
}
