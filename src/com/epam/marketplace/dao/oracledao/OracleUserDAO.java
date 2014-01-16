package com.epam.marketplace.dao.oracledao;

import com.epam.marketplace.beans.UserTransfer;
import com.epam.marketplace.dao.UserDAO;
import javax.sql.DataSource;
import java.sql.*;

/**
 * Describe UserDAO for Oracle
 * 
 * @author Nikolay_Shipyakov
 * 
 */
public class OracleUserDAO implements UserDAO {
	private static final String FULL_NAME = "Full_Name";
	private static final String BILLING_ADRESS = "Billing_Adress";
	private static final String LOGIN = "Login";
	private static final String PASSWORD = "Password";
	private static final String USER_ID = "User_Id";
	private static final String MAIL = "Mail";
	private static final String DELETE_USER_QUERY =
			"DELETE FROM Users WHERE User_Id = ?";
	private static final String ADD_USER_QUERY =
			"{? = call AddUser(?, ?, ?, ?, ?)}";
	private static final String SELECT_USER_ON_LOGIN =
			"SELECT * FROM USERS WHERE Login=?";
	private static final String SELECT_USER_ON_ID =
			"SELECT * FROM USERS WHERE User_Id=?";
	private DataSource source;

	/**
	 * Constructor for this class
	 * 
	 * @param source data source
	 */
	public OracleUserDAO(DataSource source) {
		this.source = source;
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean deleteUser(int id) {
		boolean result = false;
		Connection connection = null;
		try {
			connection = source.getConnection();
			PreparedStatement stat =
					connection.prepareStatement(DELETE_USER_QUERY);
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
	public boolean insertUser(UserTransfer insertedUser) {
		Connection connection = null;
		boolean result = false;
		try {
			connection = source.getConnection();
			CallableStatement stat = connection.prepareCall(ADD_USER_QUERY);
			stat.registerOutParameter(1, java.sql.Types.INTEGER);
			stat.setString(2, insertedUser.getFullName());
			stat.setString(3, insertedUser.getAddress());
			stat.setString(4, insertedUser.getLogin());
			stat.setString(5, insertedUser.getPassword());
			stat.setString(6, insertedUser.getMail());
			stat.execute();
			int id = stat.getInt(1);
			if (id > 0) {
				result = true;
				insertedUser.setId(id);
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
	public boolean isExistUser(UserTransfer user) {
		boolean result = false;
		Connection connection = null;
		try {
			connection = source.getConnection();
			PreparedStatement stat =
					connection.prepareStatement(SELECT_USER_ON_LOGIN);
			stat.setString(1, user.getLogin());
			ResultSet rs = stat.executeQuery();
			if (rs.next()) {
				String password = rs.getString(PASSWORD);
				if (password.equals(user.getPassword())) {
					int userId = rs.getInt(USER_ID);
					String login = rs.getString(LOGIN);
					String address = rs.getString(BILLING_ADRESS);
					String fullName = rs.getString(FULL_NAME);
					String mail = rs.getString(MAIL);
					user.setAllField(fullName, address, login, password, mail);
					user.setId(userId);
					result = true;
				}
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
	public UserTransfer selectUser(int id) {
		UserTransfer user = null;
		Connection connection = null;
		try {
			connection = source.getConnection();
			PreparedStatement stat =
					connection.prepareStatement(SELECT_USER_ON_ID);
			stat.setInt(1, id);
			ResultSet rs = stat.executeQuery();
			if (rs.next()) {
				String login = rs.getString(LOGIN);
				String password = rs.getString(PASSWORD);
				String address = rs.getString(BILLING_ADRESS);
				String fullName = rs.getString(FULL_NAME);
				String mail = rs.getString(MAIL);
				user = new UserTransfer();
				user.setAllField(fullName, address, login, password, mail);
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
		return user;
	}
}
