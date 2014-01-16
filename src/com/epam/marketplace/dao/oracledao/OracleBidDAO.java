package com.epam.marketplace.dao.oracledao;

import com.epam.marketplace.beans.BidTransfer;
import com.epam.marketplace.dao.BidDAO;

import javax.sql.DataSource;
import java.sql.*;

/**
 * Bid DAO for database Oracle
 * 
 * @author Nikolay_Shipyakov
 * 
 */
public class OracleBidDAO implements BidDAO {
	private static final String BID = "Bid";
	private static final String ITEM_ID = "Item_Id";
	private static final String BIDDER = "Bidder_Id";
	private static final String BID_ID = "Bid_Id";
	private static final String SELECT_MAX_BID =
			"SELECT *  FROM Bids WHERE Item_Id = ? AND bid IN (SELECT MAX(bid) "
					+ "FROM bids WHERE Item_Id=?)";
	private static final String ADD_BID = "{? = call AddBid(?, ?, ?)}";
	private DataSource source;

	/**
	 * Constructor for this class
	 * 
	 * @param source data source
	 */
	public OracleBidDAO(DataSource source) {
		this.source = source;
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean insertBid(BidTransfer insertedBid) {
		boolean result = false;
		Connection connection = null;
		try {
			connection = source.getConnection();
			CallableStatement stat = connection.prepareCall(ADD_BID);
			stat.registerOutParameter(1, java.sql.Types.INTEGER);
			stat.setInt(2, insertedBid.getBidderId());
			stat.setInt(3, insertedBid.getItemId());
			stat.setDouble(4, insertedBid.getBid());
			stat.executeUpdate();
			int bid_Id = stat.getInt(1);
			if (bid_Id > 0) {
				result = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
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

	/**
	 * {@inheritDoc}
	 */
	public BidTransfer selectMaxBid(int goodsId) {
		BidTransfer returnBid = null;
		Connection connection = null;
		PreparedStatement stat = null;
		try {
			connection = source.getConnection();
			stat = connection.prepareStatement(SELECT_MAX_BID);
			stat.setInt(1, goodsId);
			stat.setInt(2, goodsId);
			ResultSet rs = stat.executeQuery();
			if (rs.next()) {
				int bidId = rs.getInt(BID_ID);
				int bidderId = rs.getInt(BIDDER);
				int itemId = rs.getInt(ITEM_ID);
				double bid = rs.getDouble(BID);
				returnBid = new BidTransfer();
				returnBid.setBidId(bidId);
				returnBid.setAllFields(bidderId, itemId, bid);
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
		return returnBid;
	}
}
