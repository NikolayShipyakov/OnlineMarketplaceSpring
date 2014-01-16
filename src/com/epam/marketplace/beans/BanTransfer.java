package com.epam.marketplace.beans;

import java.io.Serializable;

/**
 * Describe ban transfer
 * 
 * @author Nikolay_Shipyakov
 * 
 */
@SuppressWarnings("serial")
public class BanTransfer implements Serializable {
	private int id;
	private String fullName;
	private boolean isBan;

	/**
	 * Get user id
	 * 
	 * @return id
	 */
	public int getId() {
		return id;
	}

	/**
	 * Set user id
	 * 
	 * @param id user id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Get user name
	 * 
	 * @return name
	 */
	public String getFullName() {
		return fullName;
	}

	/**
	 * Set name
	 * 
	 * @param fullName user name
	 */
	public void setFullName(String fullName) {
		this.fullName = fullName;
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
	 * Set ban
	 * 
	 * @param isBan boolean
	 */
	public void setBan(boolean isBan) {
		this.isBan = isBan;
	}
}
