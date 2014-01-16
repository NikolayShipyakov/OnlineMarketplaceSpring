package com.epam.marketplace.dao;


import com.epam.marketplace.beans.CategoryTransfer;

import java.util.ArrayList;

/**
 * Describe category DAO
 * 
 * @author Nikolay_Shipyakov
 *
 */
public interface CategoryDAO {
	/**
	 * Get all categorys
	 * 
	 * @return list categorys
	 */
	ArrayList<CategoryTransfer> getAllCategorys();
}
