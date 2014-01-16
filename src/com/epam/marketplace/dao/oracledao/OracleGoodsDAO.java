package com.epam.marketplace.dao.oracledao;

import com.epam.marketplace.beans.Goods;
import com.epam.marketplace.beans.GoodsForForm;
import com.epam.marketplace.beans.GoodsTransfer;
import com.epam.marketplace.dao.GoodsDAO;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * GoodsDAO for database Oracle
 * 
 * @author Nikolay_Shipyakov
 * 
 */
public class OracleGoodsDAO implements GoodsDAO {
	private static final String ELEMENT_FOR_STRING_SEARCH = "%";
	private static final String BUI_IT_NOW_SEARCH = " BUY_IT_NOW=1";
	private static final String CONDITION_WHERE = " WHERE ";
	private static final String CONN_FOR_CONDITION = " AND ";
	private static final int MS_IN_HOURS = 3600000;
	private static final String COL_ITEM_ID = "Item_Id";
	private static final String COL_SELLER_ID = "Seller_Id";
	private static final String COL_TITLE = "Title";
	private static final String COL_DESCRIPTION = "Description";
	private static final String COL_START_PRICE = "Start_Price";
	private static final String COL_TIME_LEFT = "Time_Left";
	private static final String COL_DATE = "Start_Bidding_Date";
	private static final String BUY_IT_NOW = "Buy_It_Now";
	private static final String BID_INCREMENT = "Bid_Increment";
	private static final String BID = "Bid";
	private static final String SELLER = "Seller";
	private static final String BIDDER = "Bidder";
	private static final String SELLER_LOGIN = "Seller_Login";
	private static final String COL_CATEGORY_NAME = "Category_Name";
	private static final String COL_CATEGORY_ID = "Category_Id";
	private static final String COL_COUNT = "Count";
	private static final String SELECT_ITEM_PATTERN =
			"SELECT %s, %s, %s, %s, %s, %s, "
					+ "%s, %s, "
					+ "%s  "
					+ "FROM Items ";
	private static String SELECT_ITEM =
			String.format(SELECT_ITEM_PATTERN, COL_ITEM_ID, COL_SELLER_ID,
				COL_TITLE, COL_DESCRIPTION, COL_START_PRICE, COL_TIME_LEFT,
				COL_DATE, BUY_IT_NOW, BID_INCREMENT);
	private static String SELECT_ITEM_ON_ID = SELECT_ITEM + "WHERE Item_Id=?";
	private static String SELECT_ITEM_ON_SELLER =
			SELECT_ITEM + "WHERE Seller_Id=?";
	private static final String SELECT_ITEMS_ON_TITLE =
			SELECT_ITEM + "WHERE upper(Title) LIKE '%'||upper(?)||'%'";
	private static final String SELECT_ITEMS_ON_DESCRIPTION =
			SELECT_ITEM + "WHERE upper(Description) LIKE '%'||upper(?)||'%'";
	private static final String INSERT_ITEM =
			"BEGIN INSERT INTO Items"
					+ "(Seller_Id, Title, Description, Start_Price, Time_Left,"
					+ "Start_Bidding_Date, Buy_It_Now, Bid_Increment, Category) "
					+ "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?) RETURNING Item_Id INTO ?; END;";
	private static final String UPDATE_ITEM =
			"UPDATE ITEMS SET %s WHERE Item_Id = ?";
	private static final String DELETE_ITEM_ON_ID =
			"DELETE FROM Items WHERE Item_Id = ?";
	private static final String ALL_COUNT_ITEMS =
			"SELECT COUNT(*) \"Count\" FROM GOODS_WITH_MAX_BID ";
	private static final String CATEGORY_COUNTS =
			"SELECT Count(*) Count FROM goods_with_max_bid WHERE category_id in "
					+ "( Select Id FROM categorys CONNECT BY PRIOR id= parent "
					+ "START WITH id=?)";
	private static final String SELECT_ITEMS_ON_CATEGORY =
			"SELECT * FROM (SELECT rownum n, item_id, title, description, seller_id, "
					+ "seller, seller_login, start_price, bid_increment, bid, "
					+ "start_bidding_date, time_left, buy_it_now, category_id,"
					+ "category_name, bidder_id, bidder_login, bidder "
					+ "FROM( SELECT * FROM goods_with_max_bid WHERE category_id in ( Select Id "
					+ "FROM categorys CONNECT BY PRIOR id= parent START WITH id=?) "
					+ "Order By %s %s))WHERE n BETWEEN ? AND ?";
	private static final String SELECT_ITEMS_ON_INTERVAL_ON_PARAMETERS =
			"Select * FROM (Select Rownum as \"n\", Item_Id, Title, Description, "
					+ "Seller_Id, Seller, Seller_Login,Start_Price, Bid_Increment,"
					+ "Bid, Start_Bidding_Date, Time_Left, Buy_It_Now, Bidder, "
					+ "Category_Name, Category_Id "
					+ "FROM (SELECT * FROM goods_with_max_bid Order By %s %s)) "
					+ "WHERE (\"n\" BETWEEN %d AND %d) ";
	private static final String SEARCH_ITEMS =
			"Select * FROM (Select Rownum as \"n\", Item_Id, Title, Description, "
					+ "Seller_Id, Seller, Seller_Login,Start_Price, Bid_Increment,"
					+ "Bid, Start_Bidding_Date, Time_Left,Buy_It_Now, Bidder, "
					+ "Category_Name, Category_Id FROM (SELECT * "
					+ "FROM goods_with_max_bid %s Order By %s %s)) "
					+ "WHERE (\"n\" BETWEEN %d AND %d)";
	private static String SELECT_ITEM_INFO =
			"Select Title, Description, "
					+ "Seller, Start_Price, Bidder,Bid FROM goods "
					+ "WHERE (item_id=?) AND (Bid != 0) ORDER BY Bid";
	private static final String SELECT_EXTENDS_ITEMS =
			"SELECT * FROM goods_with_max_bid";
	private static final String SELECT_EXTEND_ITEM_ON_ID =
			"SELECT * FROM goods_with_max_bid WHERE Item_Id=?";
	private static final String CONDITION_SEARCH = "upper(%s) LIKE upper(?)";
	private static final String TYPE_SEARCH = "WHERE " + CONDITION_SEARCH;
	private static final String SEARCH_MY_ITEMS =
			"WHERE (Seller_Login = ?) OR (Bidder_Login = ?)";
	private static final String SEARCH_ITEMS_ON_INTERVAL_ON_TITLE = "Title";
	private static final String SEARCH_ITEMS_ON_INTERVAL_ON_DESCRIPTION =
			"Description";
	private static final String TYPE_SORT_TITLE = "title";
	private static final String TYPE_SORT_BEST_OFFER = "bid";
	private static final String TYPE_SORT_ASC = "ASC";
	private static final String TYPE_SORT_DESC = "DESC";
	private static final String UPDATE_ALL_FIELDS =
			"Title = ?, Description = ?,Start_Price = ?, Bid_Increment = ?,"
					+ "Time_Left = ?, Buy_It_Now = ?, Category = ?";
	private static final String UPDATE_PART_FIELDS =
			"Description = ?, Bid_Increment = ?, Time_Left = ?";
	private static final String CONDITION_ON_COUNT_BIDDER =
			"(SELECT Count(Bid) FROM Bids WHERE bids.item_id= goods_with_max_bid.item_id)=?";
	private DataSource source;

	/**
	 * Constructor for this class
	 * 
	 * @param source data source
	 */
	public OracleGoodsDAO(DataSource source) {
		this.source = source;
	}

	/**
	 * {@inheritDoc}
	 */
	public ArrayList<GoodsTransfer> selectAllGoods() {
		ArrayList<GoodsTransfer> listGoodsTransfer =
				new ArrayList<GoodsTransfer>();
		Connection connection = null;
		try {
			connection = source.getConnection();
			Statement stat = connection.createStatement();
			ResultSet rs = stat.executeQuery(SELECT_ITEM);
			listGoodsTransfer = createGoodsList(rs);
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
		return listGoodsTransfer;
	}

	/**
	 * {@inheritDoc}
	 */
	public GoodsTransfer selectGoodsOnId(int id) {
		GoodsTransfer goodsTransfer;
		Connection connection = null;
		try {
			connection = source.getConnection();
			PreparedStatement stat =
					connection.prepareStatement(SELECT_ITEM_ON_ID);
			stat.setInt(1, id);
			ResultSet rs = stat.executeQuery();
			if (rs.next()) {
				goodsTransfer = new GoodsTransfer();
				goodsTransfer.setId(id);
				int sellerId = rs.getInt(COL_SELLER_ID);
				String title = rs.getString(COL_TITLE);
				String description = rs.getString(COL_DESCRIPTION);
				double startPrice = rs.getDouble(COL_START_PRICE);
				Double timeLeft = rs.getDouble(COL_TIME_LEFT);
				Timestamp time = rs.getTimestamp(COL_DATE);
				Calendar calendar = Calendar.getInstance();
				calendar.setTimeInMillis(time.getTime());
				int buy = rs.getInt(BUY_IT_NOW);
				boolean buy_It_Now;
				if (buy == 0) {
					buy_It_Now = false;
				} else {
					buy_It_Now = true;
				}
				double incr = rs.getDouble(BID_INCREMENT);
				goodsTransfer.setAllField(sellerId, title, description,
					startPrice, timeLeft, calendar, buy_It_Now, incr);
			} else {
				goodsTransfer = null;
			}
		} catch (SQLException e) {
			goodsTransfer = null;
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
		return goodsTransfer;
	}

	/**
	 * {@inheritDoc}
	 */
	public ArrayList<GoodsTransfer> selectGoodsOnSeller(int sellerId) {
		ArrayList<GoodsTransfer> listGoodsTransfer =
				new ArrayList<GoodsTransfer>();
		Connection connection = null;
		try {
			connection = source.getConnection();
			PreparedStatement stat = 
				connection.prepareStatement(SELECT_ITEM_ON_SELLER);
			stat.setInt(1, sellerId);
			ResultSet rs = stat.executeQuery();
			listGoodsTransfer = createGoodsList(rs);
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
		return listGoodsTransfer;
	}

	/**
	 * {@inheritDoc}
	 */
	public ArrayList<GoodsTransfer> selectListGoodsOnTitle(String partOfTitle) {
		ArrayList<GoodsTransfer> listGoodsTransfer =
				new ArrayList<GoodsTransfer>();
		Connection connection = null;
		try {
			connection = source.getConnection();
			PreparedStatement stat = 
				connection.prepareStatement(SELECT_ITEMS_ON_TITLE);
			stat.setString(1, partOfTitle);
			ResultSet rs = stat.executeQuery();
			listGoodsTransfer = createGoodsList(rs);
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
		return listGoodsTransfer;
	}

	/**
	 * {@inheritDoc}
	 */
	public ArrayList<GoodsTransfer> selectListGoodsOnDescription(
			String partOfDescription) {
		ArrayList<GoodsTransfer> listGoodsTransfer = new ArrayList<GoodsTransfer>();
		Connection connection = null;
		try {
			connection = source.getConnection();
			PreparedStatement stat =
					connection.prepareStatement(SELECT_ITEMS_ON_DESCRIPTION);
			stat.setString(1, partOfDescription);
			ResultSet rs = stat.executeQuery();
			listGoodsTransfer = createGoodsList(rs);
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
		return listGoodsTransfer;
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean deleteGoodsOnId(int id) {
		boolean result = false;
		Connection connection = null;
		try {
			connection = source.getConnection();
			PreparedStatement stat = 
				connection.prepareStatement(DELETE_ITEM_ON_ID);
			stat.setInt(1, id);
			int countRows = stat.executeUpdate();
			if (countRows > 0) {
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
	public boolean insertGoods(GoodsTransfer insertedGoods) {
		Connection connection = null;
		CallableStatement stat = null;
		boolean result = false;
		try {
			connection = source.getConnection();
			stat = connection.prepareCall(INSERT_ITEM);
			stat.setInt(1, insertedGoods.getSellerId());
			stat.setString(2, insertedGoods.getTitle());
			stat.setString(3, insertedGoods.getDescription());
			stat.setDouble(4, insertedGoods.getStartPrice());
			if (insertedGoods.getTimeLeft() != null) {
				stat.setDouble(5, insertedGoods.getTimeLeft());
			} else {
				stat.setNull(5, Types.NUMERIC);
			}
			stat.setTimestamp(6, 
				new Timestamp(insertedGoods.getStartBiddingDate().getTimeInMillis()));
			int buyItNow;
			if (insertedGoods.isBuyItNow()) {
				buyItNow = 1;
			} else {
				buyItNow = 0;
			}
			stat.setInt(7, buyItNow);
			if (insertedGoods.getBidIncrement() != null) {
				stat.setDouble(8, insertedGoods.getBidIncrement());
			} else {
				stat.setNull(8, Types.NUMERIC);
			}
			stat.setInt(9, insertedGoods.getCategory());
			stat.registerOutParameter(10, Types.INTEGER);
			int rows = stat.executeUpdate();
			if (rows > 0) {
				int id = stat.getInt(10);
				insertedGoods.setId(id);
				result = true;
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
		return result;
	}

	private ArrayList<GoodsTransfer> createGoodsList(ResultSet rs)
			throws SQLException {
		ArrayList<GoodsTransfer> listGoodsTransfer =
				new ArrayList<GoodsTransfer>();
		GoodsTransfer goodsTransfer;
		while (rs.next()) {
			goodsTransfer = new GoodsTransfer();
			int id = rs.getInt(COL_ITEM_ID);
			goodsTransfer.setId(id);
			int sellerId = rs.getInt(COL_SELLER_ID);
			String title = rs.getString(COL_TITLE);
			String description = rs.getString(COL_DESCRIPTION);
			double startPrice = rs.getDouble(COL_START_PRICE);
			double timeLeft = rs.getDouble(COL_TIME_LEFT);
			Timestamp time = rs.getTimestamp(COL_DATE);
			Calendar calendar = Calendar.getInstance();
			calendar.setTimeInMillis(time.getTime());
			int buy = rs.getInt(BUY_IT_NOW);
			boolean buy_It_Now;
			if (buy == 0) {
				buy_It_Now = false;
			} else {
				buy_It_Now = true;
			}
			double incr = rs.getDouble(BID_INCREMENT);
			goodsTransfer.setAllField(sellerId, title, description, startPrice,
				timeLeft, calendar, buy_It_Now, incr);
			listGoodsTransfer.add(goodsTransfer);
		}
		return listGoodsTransfer;
	}

	/**
	 * {@inheritDoc}
	 */
	public ArrayList<Goods> selectAllItemsOnInterval(int beginInterval,
			int endInterval, int typeSort, String fieldSort) {
		String direction;
		if (typeSort == 0) {
			direction = TYPE_SORT_ASC;
		} else {
			direction = TYPE_SORT_DESC;
		}
		String typeS;
		if (TYPE_SORT_TITLE.equals(fieldSort)) {
			typeS = TYPE_SORT_TITLE;
		} else {
			typeS = TYPE_SORT_BEST_OFFER;
		}
		String query = String.format(SELECT_ITEMS_ON_INTERVAL_ON_PARAMETERS, typeS,
					direction, beginInterval, endInterval);
		ArrayList<Goods> listGoodsTransfer = new ArrayList<Goods>();
		Connection connection = null;
		try {
			connection = source.getConnection();
			Statement stat = connection.createStatement();
			ResultSet rs = stat.executeQuery(query);
			listGoodsTransfer = createListGoods(rs);

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
		return listGoodsTransfer;
	}

	/**
	 * {@inheritDoc}
	 */
	public ArrayList<Goods> selectAllItemsOnIntervalSortBestOffer(
			int beginInterval, int endInterval, int typeSort) {
		String direction;
		if (typeSort == 0) {
			direction = TYPE_SORT_ASC;
		} else {
			direction = TYPE_SORT_DESC;
		}
		String query = String.format(SELECT_ITEMS_ON_INTERVAL_ON_PARAMETERS,
							TYPE_SORT_BEST_OFFER, direction, beginInterval,
							endInterval);
		Connection connection = null;
		Statement stat = null;
		ArrayList<Goods> goods = null;
		try {
			connection = source.getConnection();
			stat = connection.createStatement();
			ResultSet rs = stat.executeQuery(query);
			goods = createListGoods(rs);

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

		return goods;
	}

	/**
	 * {@inheritDoc}
	 */
	public ArrayList<Goods> searchItems(int beginInterval, int endInterval,
			int typeSort, String parameter, String typeSearch, String nameSort) {
		ArrayList<Goods> goods = null;
		String direction;
		if (typeSort == 0) {
			direction = TYPE_SORT_ASC;
		} else {
			direction = TYPE_SORT_DESC;
		}
		String type;
		if (typeSearch.equals(SEARCH_ITEMS_ON_INTERVAL_ON_TITLE)) {
			type = SEARCH_ITEMS_ON_INTERVAL_ON_TITLE;
		} else {
			type = SEARCH_ITEMS_ON_INTERVAL_ON_DESCRIPTION;
		}
		String typeS;
		if (TYPE_SORT_TITLE.equals(nameSort)) {
			typeS = TYPE_SORT_TITLE;
		} else {
			typeS = TYPE_SORT_BEST_OFFER;
		}
		String search = String.format(TYPE_SEARCH, type);
		String query = String.format(SEARCH_ITEMS, search, typeS, direction,
					beginInterval, endInterval);
		Connection connection = null;
		PreparedStatement prep = null;
		try {
			connection = source.getConnection();
			prep = connection.prepareStatement(query);
			prep.setString(1, ELEMENT_FOR_STRING_SEARCH + parameter.toUpperCase() +
					ELEMENT_FOR_STRING_SEARCH);
			ResultSet rs = prep.executeQuery();
			goods = createListGoods(rs);
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
		return goods;
	}

	/**
	 * {@inheritDoc}
	 */
	public ArrayList<Goods> selectAllMyItems(int beginInterval,
			int endInterval, int typeSort, String nameSort, String login) {
		String direction;
		if (typeSort == 0) {
			direction = TYPE_SORT_ASC;
		} else {
			direction = TYPE_SORT_DESC;
		}
		String typeS;
		if (TYPE_SORT_TITLE.equals(nameSort)) {
			typeS = TYPE_SORT_TITLE;
		} else {
			typeS = TYPE_SORT_BEST_OFFER;
		}
		String query = String.format(SEARCH_ITEMS, SEARCH_MY_ITEMS, typeS, direction,
					beginInterval, endInterval);
		ArrayList<Goods> goods = new ArrayList<Goods>();
		Connection connection = null;
		PreparedStatement prep = null;
		try {
			connection = source.getConnection();
			prep = connection.prepareStatement(query);
			prep.setString(1, login);
			prep.setString(2, login);
			ResultSet rs = prep.executeQuery();
			goods = createListGoods(rs);
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
		return goods;
	}

	/**
	 * {@inheritDoc}
	 */
	public ArrayList<Goods> selectItemsOnCategory(int beginInterval,
			int endInterval, int typeSort, String nameSort, int category) {
		String direction;
		if (typeSort == 0) {
			direction = TYPE_SORT_ASC;
		} else {
			direction = TYPE_SORT_DESC;
		}
		String typeS;
		if (TYPE_SORT_TITLE.equals(nameSort)) {
			typeS = TYPE_SORT_TITLE;
		} else {
			typeS = TYPE_SORT_BEST_OFFER;
		}
		String query =
				String.format(SELECT_ITEMS_ON_CATEGORY, typeS, direction);
		ArrayList<Goods> goods = new ArrayList<Goods>();
		Connection connection = null;
		PreparedStatement prep = null;
		try {
			connection = source.getConnection();
			prep = connection.prepareStatement(query);
			prep.setInt(1, category);
			prep.setInt(2, beginInterval);
			prep.setInt(3, endInterval);
			ResultSet rs = prep.executeQuery();
			goods = createListGoods(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return goods;
	}

	/**
	 * {@inheritDoc}
	 */
	public Goods selectExtendGoodsOnId(int id) {
		Connection connection = null;
		PreparedStatement prep = null;
		Goods goods = null;
		try {
			connection = source.getConnection();
			prep = connection.prepareStatement(SELECT_EXTEND_ITEM_ON_ID);
			prep.setInt(1, id);
			ResultSet rs = prep.executeQuery();
			ArrayList<Goods> list = createListGoods(rs);
			if (list.size() > 0) {
				goods = list.get(0);
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
		return goods;
	}

	/**
	 * {@inheritDoc}
	 */
	public ArrayList<Goods> getItemInfo(int itemId) {
		ArrayList<Goods> goods = new ArrayList<Goods>();
		Connection connection = null;
		PreparedStatement prep = null;
		try {
			connection = source.getConnection();
			prep = connection.prepareStatement(SELECT_ITEM_INFO);
			prep.setInt(1, itemId);
			ResultSet rs = prep.executeQuery();
			while (rs.next()) {
				String title = rs.getString(COL_TITLE);
				String description = rs.getString(COL_DESCRIPTION);
				String seller = rs.getString(SELLER);
				Double price = rs.getDouble(COL_START_PRICE);
				String bidder = rs.getString(BIDDER);
				Double bid = rs.getDouble(BID);
				Goods item = new Goods();
				item.setItemId(itemId);
				item.setTitle(title);
				item.setDescription(description);
				item.setSeller(seller);
				item.setStartPrice(price);
				item.setBidder(bidder);
				item.setBestOffer(bid);
				goods.add(item);
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
		return goods;
	}

	/**
	 * {@inheritDoc}
	 */
	public int getCountAllItems() {
		Connection connection = null;
		Statement stat = null;
		int count = 0;
		try {
			connection = source.getConnection();
			stat = connection.createStatement();
			ResultSet rs = stat.executeQuery(ALL_COUNT_ITEMS);
			rs.next();
			count = rs.getInt(COL_COUNT);
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
		return count;
	}

	/**
	 * {@inheritDoc}
	 */
	public int getCountItemsFromCategory(int category) {
		Connection connection = null;
		PreparedStatement prep = null;
		int count = 0;
		try {
			connection = source.getConnection();
			prep = connection.prepareStatement(CATEGORY_COUNTS);
			prep.setInt(1, count);
			ResultSet rs = prep.executeQuery();
			rs.next();
			count = rs.getInt(COL_COUNT);
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
		return count;
	}

	/**
	 * {@inheritDoc}
	 */
	public int getCountItemsFromSearch(String parameter, String typeSearch) {
		Connection connection = null;
		PreparedStatement prep = null;
		int count = 0;
		try {
			connection = source.getConnection();
			String query = String.format(ALL_COUNT_ITEMS + TYPE_SEARCH, typeSearch);
			prep = connection.prepareStatement(query);
			prep.setString(1, ELEMENT_FOR_STRING_SEARCH + parameter.toUpperCase() +
					ELEMENT_FOR_STRING_SEARCH);
			ResultSet rs = prep.executeQuery();
			rs.next();
			count = rs.getInt(COL_COUNT);
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
		return count;
	}

	/**
	 * {@inheritDoc}
	 */
	public int getCountGetUserItems(String user) {
		Connection connection = null;
		PreparedStatement prep = null;
		int count = 0;
		try {
			connection = source.getConnection();
			String query = ALL_COUNT_ITEMS + SEARCH_MY_ITEMS;
			prep = connection.prepareStatement(query);
			prep.setString(1, user);
			prep.setString(2, user);
			ResultSet rs = prep.executeQuery();
			rs.next();
			count = rs.getInt(COL_COUNT);
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
		return count;
	}

	private ArrayList<Goods> createListGoods(ResultSet rs) throws SQLException {
		ArrayList<Goods> goods = new ArrayList<Goods>();
		while (rs.next()) {
			int itemId = rs.getInt(COL_ITEM_ID);
			String title = rs.getString(COL_TITLE);
			String description = rs.getString(COL_DESCRIPTION);
			int sellerId = rs.getInt(COL_SELLER_ID);
			String seller = rs.getString(SELLER);
			String sellerLogin = rs.getString(SELLER_LOGIN);
			double price = rs.getDouble(COL_START_PRICE);
			Double bidIncr = rs.getDouble(BID_INCREMENT);
			Double bid = rs.getDouble(BID);
			Timestamp time = rs.getTimestamp(COL_DATE);
			Calendar calendar = Calendar.getInstance();
			calendar.setTimeInMillis(time.getTime());
			Double timeLeft = rs.getDouble(COL_TIME_LEFT);
			Calendar stopDate = getStopDate(calendar, timeLeft);
			int buy = rs.getInt(BUY_IT_NOW);
			boolean buy_It_Now;
			if (buy == 0) {
				buy_It_Now = false;
			} else {
				buy_It_Now = true;
			}
			String bidder = rs.getString(BIDDER);
			int categoryId = rs.getInt(COL_CATEGORY_ID);
			String categoryName = rs.getString(COL_CATEGORY_NAME);
			Goods item = new Goods();
			item.setItemId(itemId);
			item.setTitle(title);
			item.setDescription(description);
			item.setSellerId(sellerId);
			item.setSeller(seller);
			item.setSellerLogin(sellerLogin);
			item.setStartPrice(price);
			item.setBidIncrement(bidIncr);
			item.setTimeLeft(timeLeft);
			if (bid != 0) {
				item.setBestOffer(bid);
				item.setBidder(bidder);
			}
			item.setCategoryId(categoryId);
			item.setCategoryName(categoryName);
			item.setStopDate(stopDate);
			item.setBuyItNow(buy_It_Now);
			boolean isTimeExpired = getIsTimeExpired(stopDate);
			item.setIsTimeExpired(isTimeExpired);
			boolean isSold = ((item.getBuyItNow() && item.getBidder() != null) || 
					(isTimeExpired && item.getBidder() != null));
			item.setIsSold(isSold);
			boolean isSaleProceeds = (isTimeExpired || isSold);
			item.setIsSaleProceeds(isSaleProceeds);
			goods.add(item);
		}
		return goods;
	}

	private Calendar getStopDate(Calendar startDate, double hours) {
		if (hours != 0.0) {
			int ms = (int) Math.round(hours * MS_IN_HOURS);
			startDate.add(Calendar.MILLISECOND, ms);
		} else {
			startDate = null;
		}
		return startDate;
	}

	private boolean getIsTimeExpired(Calendar stopDate) {
		boolean isTimeExpired;
		Calendar today = Calendar.getInstance();
		if (stopDate == null ||
				today.compareTo(stopDate) == -1 ||
				today.compareTo(stopDate) == 0) {
			isTimeExpired = false;
		} else {
			isTimeExpired = true;
		}
		return isTimeExpired;
	}

	/**
	 * {@inheritDoc}
	 */
	public void updateAllFieldsGoods(GoodsTransfer goods) {
		String query = String.format(UPDATE_ITEM, UPDATE_ALL_FIELDS);
		Connection connection = null;
		PreparedStatement prep = null;
		try {
			connection = source.getConnection();
			prep = connection.prepareStatement(query);
			prep.setString(1, goods.getTitle());
			prep.setString(2, goods.getDescription());
			prep.setDouble(3, goods.getStartPrice());
			if (goods.getBidIncrement() == null) {
				prep.setNull(4, Types.NUMERIC);
			} else {
				prep.setDouble(4, goods.getBidIncrement());
			}
			if (goods.getTimeLeft() == null) {
				prep.setNull(5, Types.NUMERIC);
			} else {
				prep.setDouble(5, goods.getTimeLeft());
			}
			int itNow = 0;
			if (goods.isBuyItNow()) {
				itNow = 1;
			}
			prep.setInt(6, itNow);
			prep.setInt(7, goods.getCategory());
			prep.setInt(8, goods.getId());
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
	public void updatePartFieldsGoods(GoodsTransfer goods) {
		String query = String.format(UPDATE_ITEM, UPDATE_PART_FIELDS);
		Connection connection = null;
		PreparedStatement prep = null;
		try {
			connection = source.getConnection();
			prep = connection.prepareStatement(query);
			prep.setString(1, goods.getDescription());
			prep.setDouble(2, goods.getBidIncrement());
			prep.setDouble(3, goods.getTimeLeft());
			prep.setInt(4, goods.getId());
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
	public ArrayList<Goods> advancedSearch(GoodsForForm params) {
		String uid = params.getItemId();
		String title = params.getTitle();
		String description = params.getDescription();
		String countBidder = params.getBidderCount();
		boolean buyItNow = params.getBuyItNow();
		int numberParam = 0;
		int titleParam = 0;
		int descrParam = 0;
		int idParam = 0;
		int countBidderParam = 0;
		String query;
		if (uid != null && uid != "") {
			query = SELECT_EXTEND_ITEM_ON_ID;
			numberParam++;
			idParam = numberParam;
		} else {
			query = SELECT_EXTENDS_ITEMS;
		}
		if (title != null && title != "") {
			if (numberParam > 0) {
				query += CONN_FOR_CONDITION;
			} else {
				query += CONDITION_WHERE;
			}
			query += String.format(CONDITION_SEARCH, 
				SEARCH_ITEMS_ON_INTERVAL_ON_TITLE);
			numberParam++;
			titleParam = numberParam;
		}
		if (description != null && description != "") {
			if (numberParam > 0) {
				query += CONN_FOR_CONDITION;
			} else {
				query += CONDITION_WHERE;
			}
			query += String.format(CONDITION_SEARCH,
						SEARCH_ITEMS_ON_INTERVAL_ON_DESCRIPTION);
			numberParam++;
			descrParam = numberParam;
		}
		if (buyItNow) {
			if (numberParam > 0) {
				query += CONN_FOR_CONDITION;
			} else {
				query += CONDITION_WHERE;
			}
			query += BUI_IT_NOW_SEARCH;
		}
		if (countBidder != null && countBidder != "") {
			if (numberParam > 0) {
				query += CONN_FOR_CONDITION;
			} else {
				query += CONDITION_WHERE;
			}
			query += CONDITION_ON_COUNT_BIDDER;
			numberParam++;
			countBidderParam = numberParam;
		}
		ArrayList<Goods> goods = new ArrayList<Goods>();
		try {
			Connection connection = source.getConnection();
			PreparedStatement prep = connection.prepareStatement(query);
			if (idParam > 0) {
				prep.setString(idParam, uid);
			}
			if (titleParam > 0) {
				prep.setString(titleParam, ELEMENT_FOR_STRING_SEARCH +
						title +	ELEMENT_FOR_STRING_SEARCH);
			}
			if (descrParam > 0) {
				prep.setString(descrParam, ELEMENT_FOR_STRING_SEARCH +
						description + ELEMENT_FOR_STRING_SEARCH);
			}
			if (countBidderParam > 0) {
				prep.setString(countBidderParam, countBidder);
			}
			ResultSet rs = prep.executeQuery();
			goods = createListGoods(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return goods;
	}
}
