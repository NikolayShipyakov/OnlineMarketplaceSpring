package com.epam.marketplace.beans;

import java.io.Serializable;

/**
 * Transfer object for Bid
 * 
 * @author Nikolay_Shipyakov
 * 
 */
@SuppressWarnings("serial")
public class BidTransfer implements Serializable {
	private int bidId;
	private int bidderId;
	private int sellerId;
	private int itemId;
	private double bid;

	/**
	 * Get bid id
	 * 
	 * @return bid id
	 */
	public int getBidId() {
		return bidId;
	}

	/**
	 * Set bid id
	 * 
	 * @param bidId bid id
	 */
	public void setBidId(int bidId) {
		this.bidId = bidId;
	}

	/**
	 * Get bidder id
	 * 
	 * @return bidder id
	 */
	public int getBidderId() {
		return bidderId;
	}

	/**
	 * Set bidder id
	 * 
	 * @param bidderId bidder id
	 */
	public void setBidderId(int bidderId) {
		this.bidderId = bidderId;
	}

	/**
	 * Get item id
	 * 
	 * @return item id
	 */
	public int getItemId() {
		return itemId;
	}

	/**
	 * Set item id
	 * 
	 * @param itemId item id
	 */
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	/**
	 * Get bid
	 * 
	 * @return bid
	 */
	public double getBid() {
		return bid;
	}

	/**
	 * Set bid
	 * 
	 * @param bid bid
	 */
	public void setBid(double bid) {
		this.bid = bid;
	}

	/**
	 * Set all field
	 * 
	 * @param bidderId bidder is
	 * @param itemId item id
	 * @param bid bid
	 */
	public void setAllFields(int bidderId, int itemId, double bid) {
		this.bidderId = bidderId;
		this.itemId = itemId;
		this.bid = bid;
	}

	/**
	 * Get seller id
	 * 
	 * @return if
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
}
