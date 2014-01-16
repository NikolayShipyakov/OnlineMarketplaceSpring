package com.epam.marketplace.beans;

import java.io.Serializable;

/**
 * Describe category transfer object
 * 
 * @author Nikolay_Shipyakov
 * 
 */
@SuppressWarnings("serial")
public class CategoryTransfer implements Serializable {
	private int id;
	private String name;
	private String fullName;
	private int parentId;

	/**
	 * Get id category
	 * 
	 * @return id
	 */
	public int getId() {
		return id;
	}

	/**
	 * Set category id
	 * 
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Get category name
	 * 
	 * @return category name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Set category name
	 * 
	 * @param name category name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Get parent id
	 * 
	 * @return id
	 */
	public int getParentId() {
		return parentId;
	}

	/**
	 * Set parent id
	 * 
	 * @param parentId parent id
	 */
	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

	/**
	 * Get full name
	 * 
	 * @return full name
	 */
	public String getFullName() {
		return fullName;
	}

	/**
	 * Set full name
	 * 
	 * @param fullName ���� ����
	 */
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

}
