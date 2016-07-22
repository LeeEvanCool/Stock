package com.stock.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.stock.entity.Book;
import com.stock.service.BookService;

@Controller("bookController")
public class BookController {

	@Resource(name = "bookServiceImpl")
	private BookService bookService;

	@RequestMapping(value = "/saveBook", method = RequestMethod.POST)
	public String save(Book book){
		System.out.println(book.getName());
		System.out.println(book.getSn());
		System.out.println(book.getPrice());
		bookService.save(book);
		return "redirect:/jsp/bookTest.jsp";
	}
}
