package com.epam.marketplace.beans;

/**
 * Describe goods for form
 * 
 * @author Nikolay_Shipyakov
 * 
 */
public class GoodsForForm {
	private String itemId;
	private String category;
	private String title;
	private String description;
	private String price;
	private String maxPrice;
	private String bidIncr;
	private String timeLeft;
	private String startDate;
	private String expireDate;
	private int bidder;
	private boolean buyItNow;
	private boolean isSold;
	private String bidderCount;

	/**
	 * Get max price
	 * 
	 * @return price
	 */
	public String getMaxPrice() {
		return maxPrice;
	}

	/**
	 * Set max price
	 * 
	 * @param maxPrice size max price
	 */
	public void setMaxPrice(String maxPrice) {
		this.maxPrice = maxPrice;
	}

	/**
	 * Get start date
	 * 
	 * @return start date
	 */
	public String getStartDate() {
		return startDate;
	}

	/**
	 * Set start date
	 * 
	 * @param startDate start date
	 */
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	/**
	 * Get expire date
	 * 
	 * @return date
	 */
	public String getExpireDate() {
		return expireDate;
	}

	/**
	 * Set expire date
	 * 
	 * @param expireDate date
	 */
	public void setExpireDate(String expireDate) {
		this.expireDate = expireDate;
	}

	/**
	 * Get bidder count
	 * 
	 * @return count bidder
	 */
	public String getBidderCount() {
		return bidderCount;
	}

	/**
	 * Set bidder count
	 * 
	 * @param bidderCount count bidder
	 */
	public void setBidderCount(String bidderCount) {
		this.bidderCount = bidderCount;
	}

	/**
	 * Set isSold
	 * 
	 * @param isSold boolean value
	 */
	public void setSold(boolean isSold) {
		this.isSold = isSold;
	}

	/**
	 * Get bidder id
	 * 
	 * @return id
	 */
	public int getBidder() {
		return bidder;
	}

	/**
	 * Set bidder id
	 * 
	 * @param bidder id
	 */
	public void setBidder(int bidder) {
		this.bidder = bidder;
	}

	/**
	 * Get item id
	 * 
	 * @return id
	 */
	public String getItemId() {
		return itemId;
	}

	/**
	 * Set item id
	 * 
	 * @param itemId id
	 */
	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	/**
	 * Get category
	 * 
	 * @return category
	 */
	public String getCategory() {
		return category;
	}

	/**
	 * Set category
	 * 
	 * @param category category
	 */
	public void setCategory(String category) {
		this.category = category;
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
	 * Get price
	 * 
	 * @return price
	 */
	public String getPrice() {
		return price;
	}

	/**
	 * Set price
	 * 
	 * @param price price
	 */
	public void setPrice(String price) {
		this.price = price;
	}

	/**
	 * Get bid increment
	 * 
	 * @return increment
	 */
	public String getBidIncr() {
		return bidIncr;
	}

	/**
	 * Set bid increment
	 * 
	 * @param bidIncr increment
	 */
	public void setBidIncr(String bidIncr) {
		this.bidIncr = bidIncr;
	}

	/**
	 * Get time left
	 * 
	 * @return time
	 */
	public String getTimeLeft() {
		return timeLeft;
	}

	/**
	 * Set time left
	 * 
	 * @param timeLeft time
	 */
	public void setTimeLeft(String timeLeft) {
		this.timeLeft = timeLeft;
	}

	/**
	 * Get buy it now
	 * 
	 * @return boolean result
	 */
	public boolean getBuyItNow() {
		return buyItNow;
	}

	/**
	 * Set buy it now
	 * 
	 * @param buyItNow boolean value
	 */
	public void setBuyItNow(boolean buyItNow) {
		this.buyItNow = buyItNow;
	}

	/**
	 * Define is sold
	 * 
	 * @return boolean result
	 */
	public boolean getIsSold() {
		return isSold;
	}

	/**
	 * Set sold
	 * 
	 * @param isSold is sold
	 */
	public void setIsSold(boolean isSold) {
		this.isSold = isSold;
	}
} 
