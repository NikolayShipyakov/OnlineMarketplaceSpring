package com.epam.marketplace.beans;

import java.io.Serializable;
import java.util.Calendar;

/**
 * Describe goods
 * 
 * @author Nikolay_Shipyakov
 * 
 */
@SuppressWarnings("serial")
public class Goods implements Serializable {
	private static final String TIME_SEPARATOR = ":";
	private static final int COUNT_MINUTES_IN_HOUR = 60;
	private static final String FORMATE_DATE = "%d.%d.%d %d:%d";
	private int itemId;
	private String title;
	private String description;
	private int sellerId;
	private String seller;
	private double startPrice;
	private double bidIncrement;
	private double bestOffer;
	private String bidder;
	private Calendar stopDate;
	private boolean buyItNow;
	private String sellerLogin;
	private Double timeLeft;
	private int categoryId;
	private String categoryName;
	private String formattingStopDate;
	private String formattingTimeLeft;
	private boolean isSold;
	private boolean isTimeExpired;
	private boolean isSaleProceeds;
	private boolean isBan;

	/**
	 * Set format for stop date
	 * 
	 * @param formattingStopDate text view stop date
	 */
	public void setFormattingStopDate(String formattingStopDate) {
		this.formattingStopDate = formattingStopDate;
	}

	/**
	 * Return stop date in string
	 * 
	 * @return string
	 */
	public String getFormattingStopDate() {
		if (stopDate != null) {
			int year = stopDate.get(Calendar.YEAR);
			int month = stopDate.get(Calendar.MONTH) + 1;
			int day = stopDate.get(Calendar.DAY_OF_MONTH);
			int hour = stopDate.get(Calendar.HOUR_OF_DAY);
			int min = stopDate.get(Calendar.MINUTE);
			formattingStopDate =
					String.format(FORMATE_DATE, year, month, day, hour, min);
		} else {
			formattingStopDate = "";
		}
		return formattingStopDate;
	}

	/**
	 * Return seller login
	 * 
	 * @return seller login
	 */
	public String getSellerLogin() {
		return sellerLogin;
	}

	/**
	 * Set seller login
	 * 
	 * @param sellerLogin login
	 */
	public void setSellerLogin(String sellerLogin) {
		this.sellerLogin = sellerLogin;
	}

	/**
	 * Return buy it now
	 * 
	 * @return boolean result
	 */
	public boolean getBuyItNow() {
		return buyItNow;
	}

	/**
	 * Set buy it now
	 * 
	 * @param buyItNow buy it now
	 */
	public void setBuyItNow(boolean buyItNow) {
		this.buyItNow = buyItNow;
	}

	/**
	 * Return item id
	 * 
	 * @return id
	 */
	public int getItemId() {
		return itemId;
	}

	/**
	 * Set item id
	 * 
	 * @param itemId id
	 */
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	/**
	 * Return title
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
	 * Return description
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
	 * Return seller
	 * 
	 * @return seller
	 */
	public String getSeller() {
		return seller;
	}

	/**
	 * Set seller
	 * 
	 * @param seller seller
	 */
	public void setSeller(String seller) {
		this.seller = seller;
	}

	/**
	 * Return start price
	 * 
	 * @return price
	 */
	public double getStartPrice() {
		return startPrice;
	}

	/**
	 * Set start price
	 * 
	 * @param startPrice price
	 */
	public void setStartPrice(double startPrice) {
		this.startPrice = startPrice;
	}

	/**
	 * Get bid increment
	 * 
	 * @return bid increment
	 */
	public double getBidIncrement() {
		return bidIncrement;
	}

	/**
	 * Set bid increment
	 * 
	 * @param bidIncrement bid increment
	 */
	public void setBidIncrement(double bidIncrement) {
		this.bidIncrement = bidIncrement;
	}

	/**
	 * Get best offer
	 * 
	 * @return best offer
	 */
	public double getBestOffer() {
		return bestOffer;
	}

	/**
	 * Set best offer
	 * 
	 * @param bestOffer best offer
	 */
	public void setBestOffer(double bestOffer) {
		this.bestOffer = bestOffer;
	}

	/**
	 * Return bidder
	 * 
	 * @return bidder
	 */
	public String getBidder() {
		return bidder;
	}

	/**
	 * Set bidder
	 * 
	 * @param bidder bidder
	 */
	public void setBidder(String bidder) {
		this.bidder = bidder;
	}

	/**
	 * Get stop date
	 * 
	 * @return stop date
	 */
	public Calendar getStopDate() {
		return stopDate;
	}

	/**
	 * Set stop date
	 * 
	 * @param stopDate stop date
	 */
	public void setStopDate(Calendar stopDate) {
		this.stopDate = stopDate;
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

	/**
	 * Get is time expired
	 * 
	 * @return boolean result
	 */
	public boolean getIsTimeExpired() {
		return isTimeExpired;
	}

	/**
	 * Set is time expired
	 * 
	 * @param isTimeExpired boolean value
	 */
	public void setIsTimeExpired(boolean isTimeExpired) {
		this.isTimeExpired = isTimeExpired;
	}

	/**
	 * Get is sale proceeds
	 * 
	 * @return boolean result
	 */
	public boolean getIsSaleProceeds() {
		return isSaleProceeds;
	}

	/**
	 * Set is sale proceeds
	 * 
	 * @param isSaleProceeds boolean value
	 */
	public void setIsSaleProceeds(boolean isSaleProceeds) {
		this.isSaleProceeds = isSaleProceeds;
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
	 * Get is ban
	 * 
	 * @return boolean result
	 */
	public boolean getIsBan() {
		return isBan;
	}

	/**
	 * Set is ban
	 * 
	 * @param isBan boolean value
	 */
	public void setIsBan(boolean isBan) {
		this.isBan = isBan;
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
	 * Get category id
	 * 
	 * @return category id
	 */
	public int getCategoryId() {
		return categoryId;
	}

	/**
	 * Set category id
	 * 
	 * @param categoryId category id
	 */
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	/**
	 * Get category name
	 * 
	 * @return category name
	 */
	public String getCategoryName() {
		return categoryName;
	}

	/**
	 * Set category name
	 * 
	 * @param categoryName category name
	 */
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	/**
	 * Get formatting time left
	 * 
	 * @return formatting time left
	 */
	public String getFormattingTimeLeft() {
		if (timeLeft != null && timeLeft > 0) {
			long hours = (long) Math.floor(timeLeft);
			long min = Math.round((timeLeft % 1) * COUNT_MINUTES_IN_HOUR);
			formattingTimeLeft =
					Long.toString(hours) + TIME_SEPARATOR + Long.toString(min);
		} else {
			formattingTimeLeft = "";
		}
		return formattingTimeLeft;
	}

	/**
	 * Set formatting time left
	 * 
	 * @param formattingTimeLeft time left
	 */
	public void setFormattingTimeLeft(String formattingTimeLeft) {
		this.formattingTimeLeft = formattingTimeLeft;
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
	 * Set isTimeExpired
	 * 
	 * @param isTimeExpired boolean value
	 */
	public void setTimeExpired(boolean isTimeExpired) {
		this.isTimeExpired = isTimeExpired;
	}

	/**
	 * Set isSaleProceeds
	 * 
	 * @param isSaleProceeds boolean value
	 */
	public void setSaleProceeds(boolean isSaleProceeds) {
		this.isSaleProceeds = isSaleProceeds;
	}

	/**
	 * Set isBan
	 * 
	 * @param isBan boolean value
	 */
	public void setBan(boolean isBan) {
		this.isBan = isBan;
	}
}
