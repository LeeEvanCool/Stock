
package com.stock.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "xx_stock_data_santai_holding")
public class StockDataSantaiHolding {
	/** 编号*/
	private Long id;
	/** 创建时间*/
	private Date createDatetime;
	/** 数据日期*/
	private String stockValueDate;
	/** 数据时间*/
	private String stockValueTime;
	/** 值*/
	private BigDecimal stockValue;
	/**
	 * 
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
	 * 
	 * 
	 * @return Date
	 */
	@Column(name = "create_datetime")
	public Date getCreateDatetime() {
		return createDatetime;
	}
	/**
	 * 
	 * 
	 * @param createDatetime void
	 */
	public void setCreateDatetime(Date createDatetime) {
		this.createDatetime = createDatetime;
	}
	/**
	 * 
	 * 
	 * @return String
	 */
	@Column(name = "stock_value_date")
	public String getStockValueDate() {
		return stockValueDate;
	}
	/**
	 * 
	 * 
	 * @param stockValueDate void
	 */
	public void setStockValueDate(String stockValueDate) {
		this.stockValueDate = stockValueDate;
	}
	/**
	 * 
	 * 
	 * @return String
	 */
	@Column(name = "stock_value_time")
	public String getStockValueTime() {
		return stockValueTime;
	}
	/**
	 * 
	 * 
	 * @param stockValueTime void
	 */
	public void setStockValueTime(String stockValueTime) {
		this.stockValueTime = stockValueTime;
	}
	/**
	 * 
	 * 
	 * @return BigDecimal
	 */
	@Column(name = "stock_value")
	public BigDecimal getStockValue() {
		return stockValue;
	}
	/**
	 * 
	 * 
	 * @param stockValue void
	 */
	public void setStockValue(BigDecimal stockValue) {
		this.stockValue = stockValue;
	}
}
