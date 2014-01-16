package com.epam.marketplace.dao.oracledao;

import com.epam.marketplace.dao.*;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Describe DAO factory for database Oracle
 * 
 * @author Nikolay_Shipyakov
 * 
 */
public class OracleDAOFactory extends DAOFactory {

	/**
	 * {@inheritDoc}
	 */
	public CategoryDAO getCategoryDAO() {
		return new OracleCategoryDAO(source);
	}

	/**
	 * {@inheritDoc}
	 */
	public void setSource(DataSource source) {
		OracleDAOFactory.source = source;
	}
	
	/**
	 * Return connection
	 * 
	 * @return connection connection
	 */
	public Connection getConnection() {
		Connection connection;
		try {
			connection = source.getConnection();
		} catch (SQLException e) {
			connection = null;
		}
		return connection;
	}

	/**
	 * {@inheritDoc}
	 */
	public BidDAO getBidDAO() {
		return new OracleBidDAO(source);
	}

	/**
	 * {@inheritDoc}
	 */
	public GoodsDAO getGoodsDAO() {
		return new OracleGoodsDAO(source);
	}

	/**
	 * {@inheritDoc}
	 */
	public UserDAO getUserDAO() {
		return new OracleUserDAO(source);
	}

	/**
	 * {@inheritDoc}
	 */
	public BanDAO getBanDAO() {
		return new OracleBanDAO(source);
	}
}
