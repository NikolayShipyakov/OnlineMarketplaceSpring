package com.epam.marketplace.dao;

import com.epam.marketplace.dao.oracledao.OracleDAOFactory;

import javax.sql.DataSource;

/**
 * Describe abstract factory which create DAO objects
 * 
 * @author Nikolay_Shipyakov
 * 
 */
public abstract class DAOFactory {
	protected static DataSource source;

	/**
	 * Return DAO factory
	 * 
	 * @return factory
	 */
	public static DAOFactory getDaoFactory() {
		return new OracleDAOFactory();
	}

	/**
	 * Set data source
	 * 
	 * @param source data source
	 */
	public abstract void setSource(DataSource source);

	/**
	 * Return DAO for goods
	 * 
	 * @return GoodsDAO
	 */
	public abstract GoodsDAO getGoodsDAO();

	/**
	 * Return DAO for user
	 * 
	 * @return UserDAO
	 */
	public abstract UserDAO getUserDAO();

	/**
	 * Return DAO for bid
	 * 
	 * @return BidDAO
	 */
	public abstract BidDAO getBidDAO();

	/**
	 * Return DAO for ban
	 * 
	 * @return BanDAO
	 */
	public abstract BanDAO getBanDAO();

	/**
	 * Return DAO for categorys
	 * 
	 * @return CategoryDAO
	 */
	public abstract CategoryDAO getCategoryDAO();
}
