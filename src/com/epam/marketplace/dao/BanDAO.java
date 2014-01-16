package com.epam.marketplace.dao;

import com.epam.marketplace.beans.BanTransfer;

import java.util.ArrayList;

/**
 * Describe ban DAO
 * 
 * @author Nikolay_Shipyakov
 *
 */
public interface BanDAO {
	
	/**
	 * Get users
	 * 
	 * @param seller seller id
	 * @return users
	 */
	ArrayList<BanTransfer> getUsers(int seller);
	
	/**
	 * Ban user
	 * 
	 * @param seller seller id
	 * @param bidder bidder id 
	 */
	void banUser(int seller, int bidder);
	
	/**
	 * Unban user
	 * 
	 * @param seller seller id
	 * @param bidder bidder id
	 */
	void unBanUser(int seller, int bidder);
	
	/**
	 * Inspection is ban
	 * 
	 * @param seller seller id
	 * @param bidder bidder id
	 * @return boolean result
	 */
	boolean isBan(int seller, int bidder);
}
