package com.epam.marketplace.dao.oracledao;


import com.epam.marketplace.beans.BanTransfer;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Describe BanDAO for db Oracle
 * @author Nikolay_Shipyakov
 *
 */
public class OracleBanDAO implements com.epam.marketplace.dao.BanDAO {
	private static final String COL_USER_ID = "User_Id";
	private static final String COL_FULL_NAME = "Full_Name";
	private static final String COL_SELLER = "Seller";
	private static final String ADD_TO_BAN = "INSERT INTO BANS VALUES(?, ?)";
    private static final String SELECT_USERS = "SELECT * From(" +
    		"SELECT * FROM BANS_VIEW WHERE seller = ? " +
    		"UNION ALL " +
    		"SELECT * FROM BANS_VIEW WHERE USER_ID NOT IN " +
    		"(SELECT USER_ID FROM BANS_VIEW WHERE user_id!=? and seller = ?))" +
    		"WHERE USER_ID!=?";
	private static final String DEL_BAN =
			"DELETE FROM Bans WHERE Seller=? AND Bidder=?";
	private static final String IS_BAN =
			"SELECT * FROM BANS_VIEW WHERE Seller = ? AND User_Id = ?";
	private DataSource source;

	/**
	 * Constructor for this class
	 * 
	 * @param source data source
	 */
	public OracleBanDAO(DataSource source) {
		this.source = source;
	}

	/**
	 * {@inheritDoc}
	 */
	public ArrayList<BanTransfer> getUsers(int seller) {
		ArrayList<BanTransfer> users = new ArrayList<BanTransfer>();
		Connection connection = null;
		PreparedStatement prep = null;
		try {
			connection = source.getConnection();
			prep = connection.prepareStatement(SELECT_USERS);
			prep.setInt(1, seller);
			prep.setInt(2, seller);
			prep.setInt(3, seller);
			prep.setInt(4, seller);
			ResultSet rs = prep.executeQuery();
			while (rs.next()) {
				int id = rs.getInt(COL_USER_ID);
				String fullName = rs.getString(COL_FULL_NAME);
				int sellerId = rs.getInt(COL_SELLER);
				boolean isBan;
				if (sellerId == seller) {
					isBan = true;
				} else {
					isBan = false;
				}
				BanTransfer user = new BanTransfer();
				user.setId(id);
				user.setFullName(fullName);
				user.setBan(isBan);
				users.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (prep != null) {
				try {
					prep.close();
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
		return users;
	}

	/**
	 * {@inheritDoc}
	 */
	public void banUser(int seller, int bidder) {
		Connection connection = null;
		PreparedStatement prep = null;
		try {
			connection = source.getConnection();
			prep = connection.prepareStatement(ADD_TO_BAN);
			prep.setInt(1, seller);
			prep.setInt(2, bidder);
			prep.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (prep != null) {
				try {
					prep.close();
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

	}

	/**
	 * {@inheritDoc}
	 */
	public void unBanUser(int seller, int bidder) {
		Connection connection = null;
		PreparedStatement prep = null;
		try {
			connection = source.getConnection();
			prep = connection.prepareStatement(DEL_BAN);
			prep.setInt(1, seller);
			prep.setInt(2, bidder);
			prep.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (prep != null) {
				try {
					prep.close();
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
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean isBan(int seller, int bidder) {
		boolean result = false;
		Connection connection = null;
		PreparedStatement prep = null;
		try {
			connection = source.getConnection();
			prep = connection.prepareStatement(IS_BAN);
			prep.setInt(1, seller);
			prep.setInt(2, bidder);
			ResultSet rs = prep.executeQuery();
			if (rs.next()) {
				result = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (prep != null) {
				try {
					prep.close();
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
		return result;
	}
}
