package com.epam.marketplace.beans;

import java.io.Serializable;
import java.util.Calendar;

/**
 * Transfer object for Goods
 * 
 * @author Nikolay_Shipyakov
 * 
 */
@SuppressWarnings("serial")
public class GoodsTransfer implements Serializable {
	private int id;
	private int sellerId;
	private String title;
	private String description;
	private Double startPrice;
	private Double timeLeft;
	private Calendar startBiddingDate;
	private boolean buyItNow;
	private Double bidIncrement;
	private int category;

	/**
	 * Get category id
	 * 
	 * @return id
	 */
	public int getCategory() {
		return category;
	}

	/**
	 * Set category id
	 * 
	 * @param category category id
	 */
	public void setCategory(int category) {
		this.category = category;
	}

	/**
	 * Get id
	 * 
	 * @return id
	 */
	public int getId() {
		return id;
	}

	/**
	 * Set id
	 * 
	 * @param id id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Get seller id
	 * 
	 * @return seller id
	 */
	public int getSellerId() {
		return sellerId;
	}

	/**
	 * Set seller id
	 * 
	 * @param sellerId seller id
	 */
	public void setSellerId(int sellerId) {
		this.sellerId = sellerId;
	}

	/**
	 * Get title
	 * 
	 * @return title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Set title
	 * 
	 * @param title title
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * Get description
	 * 
	 * @return description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Set description
	 * 
	 * @param description description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Get start price
	 * 
	 * @return start price
	 */
	public Double getStartPrice() {
		return startPrice;
	}

	/**
	 * Set start price
	 * 
	 * @param startPrice start price
	 */
	public void setStartPrice(Double startPrice) {
		this.startPrice = startPrice;
	}

	/**
	 * Get time left
	 * 
	 * @return time left
	 */
	public Double getTimeLeft() {
		return timeLeft;
	}

	/**
	 * Set time left
	 * 
	 * @param timeLeft time left
	 */
	public void setTimeLeft(Double timeLeft) {
		this.timeLeft = timeLeft;
	}

	/**
	 * Get start bidding date
	 * 
	 * @return start bidding date
	 */
	public Calendar getStartBiddingDate() {
		return startBiddingDate;
	}

	/**
	 * Set start bidding date
	 * 
	 * @param startBiddingDate start bidding date
	 */
	public void setStartBiddingDate(Calendar startBiddingDate) {
		this.startBiddingDate = startBiddingDate;
	}

	/**
	 * Get buy it now
	 * 
	 * @return logical value
	 */
	public boolean isBuyItNow() {
		return buyItNow;
	}

	/**
	 * Set buy it now
	 * 
	 * @param buyItNow logical value
	 */
	public void setBuyItNow(boolean buyItNow) {
		this.buyItNow = buyItNow;
	}

	/**
	 * Get bid increment
	 * 
	 * @return bid increment
	 */
	public Double getBidIncrement() {
		return bidIncrement;
	}

	/**
	 * Set bid increment
	 * 
	 * @param bidIncrement bid increment
	 */
	public void setBidIncrement(Double bidIncrement) {
		this.bidIncrement = bidIncrement;
	}

	/**
	 * Set all fields
	 * 
	 * @param sellerId seller id
	 * @param title title
	 * @param description description
	 * @param startPrice start price
	 * @param timeLeft time left
	 * @param startBindingDate start bidding date
	 * @param buyItNow buy it now
	 * @param bidIncrement bid increment
	 */
	public void setAllField(int sellerId, String title, String description,
			Double startPrice, Double timeLeft, Calendar startBindingDate,
			boolean buyItNow, Double bidIncrement) {
		this.sellerId = sellerId;
		this.title = title;
		this.description = description;
		this.startPrice = startPrice;
		this.timeLeft = timeLeft;
		this.startBiddingDate = startBindingDate;
		this.buyItNow = buyItNow;
		this.bidIncrement = bidIncrement;
	}
}
