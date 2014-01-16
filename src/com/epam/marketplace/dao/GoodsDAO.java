package com.epam.marketplace.dao;

import com.epam.marketplace.beans.Goods;
import com.epam.marketplace.beans.GoodsForForm;
import com.epam.marketplace.beans.GoodsTransfer;

import java.util.ArrayList;

/**
 * Describe methods for Goods DAO
 * 
 * @author Nikolay_Shipyakov
 * 
 */
public interface GoodsDAO {
	/**
	 * Select Goods on ID
	 * 
	 * @param id DAO id
	 * @return GoodsTransfer
	 */
	GoodsTransfer selectGoodsOnId(int id);

	/**
	 * Select Goods on seller
	 * 
	 * @param sellerId seller id
	 * @return GoodsTransfer
	 */
	ArrayList<GoodsTransfer> selectGoodsOnSeller(int sellerId);

	/**
	 * Select all goods
	 * 
	 * @return list with goods
	 */
	ArrayList<GoodsTransfer> selectAllGoods();

	/**
	 * Select goods on title
	 * 
	 * @param partOfTitle part on title
	 * @return list with goods
	 */
	ArrayList<GoodsTransfer> selectListGoodsOnTitle(String partOfTitle);

	/**
	 * Select goods on description
	 * 
	 * @param partOfDescription part on description
	 * @return list with goods
	 */
	ArrayList<GoodsTransfer> selectListGoodsOnDescription(
            String partOfDescription);

	/**
	 * Delete goods on id
	 * 
	 * @param id goods id
	 * @return result operation
	 */
	boolean deleteGoodsOnId(int id);

	/**
	 * Insert goods
	 * 
	 * @param insertedGoods inserted goods
	 * @return result operation
	 */
	boolean insertGoods(GoodsTransfer insertedGoods);

	/**
	 * Select items on interval
	 * 
	 * @param beginInterval begin interval
	 * @param endInterval end interval
	 * @param typeSort type sort
	 * @param fieldSort field sort
	 * @return list with goods
	 */
	ArrayList<Goods> selectAllItemsOnInterval(int beginInterval,
                                              int endInterval, int typeSort, String fieldSort);

	/**
	 * Select items on interval on best offer
	 * 
	 * @param beginInterval begin interval
	 * @param endInterval end interval
	 * @param typeSort type sort
	 * @return list with goods
	 */
	ArrayList<Goods> selectAllItemsOnIntervalSortBestOffer(int beginInterval,
                                                           int endInterval, int typeSort);

	/**
	 * Select items on category
	 * 
	 * @param beginInterval begin interval
	 * @param endInterval end interval
	 * @param typeSort type sort
	 * @param nameSort name sort
	 * @param category id category
	 * @return list gods
	 */
	ArrayList<Goods> selectItemsOnCategory(int beginInterval, int endInterval,
                                           int typeSort, String nameSort, int category);

	/**
	 * Search items
	 * 
	 * @param beginInterval begin interval
	 * @param endInterval end interval
	 * @param typeSort type sort
	 * @param parameter parameter search
	 * @param typeSearch type search
	 * @param nameSort name sort
	 * @return list goods
	 */
	ArrayList<Goods> searchItems(int beginInterval, int endInterval,
                                 int typeSort, String parameter, String typeSearch, String nameSort);

	/**
	 * Select all my items
	 * 
	 * @param beginInterval begin interval
	 * @param endInterval end interval
	 * @param typeSort type sort
	 * @param nameSort name sort
	 * @param login login
	 * @return list goods
	 */
	ArrayList<Goods> selectAllMyItems(int beginInterval, int endInterval,
                                      int typeSort, String nameSort, String login);

	/**
	 * Select goods on id
	 * 
	 * @param id id
	 * @return goods
	 */
	Goods selectExtendGoodsOnId(int id);

	/**
	 * Get item info
	 * 
	 * @param itemId id
	 * @return list goods
	 */
	ArrayList<Goods> getItemInfo(int itemId);

	/**
	 * Update goods
	 * 
	 * @param goods goods
	 */
	void updateAllFieldsGoods(GoodsTransfer goods);

	/**
	 * Update part fields goods
	 * 
	 * @param goods goods
	 */
	void updatePartFieldsGoods(GoodsTransfer goods);

	/**
	 * Get count all items
	 * 
	 * @return count
	 */
	int getCountAllItems();

	/**
	 * Get count items from category
	 * 
	 * @param category category id
	 * @return count
	 */
	int getCountItemsFromCategory(int category);

	/**
	 * Get count items from search
	 * 
	 * @param parameter search parameter
	 * @param typeSearch type search
	 * @return count
	 */
	int getCountItemsFromSearch(String parameter, String typeSearch);

	/**
	 * Get count items for user
	 * 
	 * @param user user login
	 * @return count
	 */
	int getCountGetUserItems(String user);

	/**
	 * Advanced search
	 * 
	 * @param params parameters search
	 * @return list goods
	 */
	ArrayList<Goods> advancedSearch(GoodsForForm params);
}
