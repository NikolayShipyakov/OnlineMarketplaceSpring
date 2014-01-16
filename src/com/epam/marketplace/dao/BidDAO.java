package com.epam.marketplace.dao;

import com.epam.marketplace.beans.BidTransfer;

/**
 * Describe methods for Bid DAO
 * 
 * @author Nikolay_Shipyakov
 * 
 */
public interface BidDAO {
	/**
	 * Insert new bid on goods in table bids
	 * 
	 * @param insertedBid inserted bid
	 * @return result operation
	 */
	boolean insertBid(BidTransfer insertedBid);
	
	/**
	 * Select max bid
	 * 
	 * @param goodsId goods id
	 * @return bid
	 */
	 BidTransfer selectMaxBid(int goodsId);
}
