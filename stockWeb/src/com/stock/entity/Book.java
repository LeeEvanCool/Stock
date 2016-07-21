
package com.stock.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "xx_book")
public class Book {
	/** 编号*/
	private Long id;
	/** 图书编码*/
	private String sn;
	/** 书名*/
	private String name;
	/** 价格*/
	private Double price;
	/**
	 * 获取id
	 * 
	 * @return Long
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}
	/**
	 * 设置id
	 * 
	 * @param id void
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * 获取编号
	 * 
	 * @return String
	 */
	public String getSn() {
		return sn;
	}
	/**
	 * 设置编号
	 * 
	 * @param sn void
	 */
	public void setSn(String sn) {
		this.sn = sn;
	}
	/**
	 * 获取名称
	 * 
	 * @return String
	 */
	@Column(name = "book_name")
	public String getName() {
		return name;
	}
	/**
	 * 设置名称
	 * 
	 * @param name void
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取价格
	 * 
	 * @return Double
	 */
	public Double getPrice() {
		return price;
	}
	/**
	 * 设置价格
	 * 
	 * @param price void
	 */
	public void setPrice(Double price) {
		this.price = price;
	}
}
