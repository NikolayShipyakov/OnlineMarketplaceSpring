package com.epam.marketplace.dao.oracledao;

import com.epam.marketplace.beans.CategoryTransfer;
import com.epam.marketplace.dao.CategoryDAO;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Describe category DAO fo db Oracle
 * 
 * @author Nikolay_Shipyakov
 * 
 */
public class OracleCategoryDAO implements CategoryDAO {
	private static final String SELECT_ALL_CATEGORYS =
			"SELECT ID,NAME,NVL(PARENT,0) Parent FROM CATEGORYS";
	private static final String COL_ID = "Id";
	private static final String COL_NAME = "Name";
	private static final String COL_PARENT = "Parent";
	private DataSource source;

	/**
	 * Constructor for this class
	 * 
	 * @param source data source
	 */
	public OracleCategoryDAO(DataSource source) {
		this.source = source;
	}

	/**
	 * {@inheritDoc}
	 */
	public ArrayList<CategoryTransfer> getAllCategorys() {
		Connection connection = null;
		Statement stat = null;
		ArrayList<CategoryTransfer> categorys = new ArrayList<CategoryTransfer>();
		try {
			connection = source.getConnection();
			stat = connection.createStatement();
			ResultSet rs = stat.executeQuery(SELECT_ALL_CATEGORYS);
			while (rs.next()) {
				int id = rs.getInt(COL_ID);
				String name = rs.getString(COL_NAME);
				int parent = rs.getInt(COL_PARENT);
				CategoryTransfer category = new CategoryTransfer();
				category.setId(id);
				category.setName(name);
				category.setParentId(parent);
				categorys.add(category);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (stat != null) {
				try {
					stat.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return categorys;
	}

}
