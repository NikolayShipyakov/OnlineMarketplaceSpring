package com.epam.marketplace.dao;

import com.epam.marketplace.beans.UserTransfer;

/**
 * Describe methods for UserDAO
 * 
 * @author Nikolay_Shipyakov
 * 
 */
public interface UserDAO {
	/**
	 * Insert user
	 * 
	 * @param insertedUser inserted user
	 * @return result operation
	 */
	boolean insertUser(UserTransfer insertedUser);

	/**
	 * Delete user
	 * 
	 * @param id user id
	 * @return result operation
	 */
	boolean deleteUser(int id);
	
	/**
	 * Inspect user
	 * 
	 * @param user user transfer object
	 * @return result
	 */
	boolean isExistUser(UserTransfer user);
	
	/**
	 * Select user
	 * 
	 * @param id id
	 * @return user
	 */
	UserTransfer selectUser(int id);
}
