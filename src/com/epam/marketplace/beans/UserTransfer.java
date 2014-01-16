package com.epam.marketplace.beans;

import java.io.Serializable;

/**
 * Transfer object for user
 * 
 * @author Nikolay_Shipyakov
 * 
 */
@SuppressWarnings("serial")
public class UserTransfer implements Serializable {
	private int id;
	private String fullName;
	private String address;
	private String login;
	private String password;
	private String repassword;
	private String mail;
	private String phone;

    public UserTransfer(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public UserTransfer() {
    }

    /**
	 * Set id
	 * 
	 * @param id id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Get id
	 * 
	 * @return id
	 */
	public int getId() {
		return id;
	}

	/**
	 * Get full name
	 * 
	 * @return full name
	 */
	public String getFullName() {
		return fullName;
	}

	/**
	 * Set full name
	 * 
	 * @param fullName full name
	 */
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	/**
	 * Get address
	 * 
	 * @return address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * Set address
	 * 
	 * @param address address
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * Get login
	 * 
	 * @return login
	 */
	public String getLogin() {
		return login;
	}

	/**
	 * Set login
	 * 
	 * @param login login
	 */
	public void setLogin(String login) {
		this.login = login;
	}

	/**
	 * Get password
	 * 
	 * @return password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Set password
	 * 
	 * @param password password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Set all fields
	 * 
	 * @param fullName full name
	 * @param address address
	 * @param login login
	 * @param password password
	 * @param mail mail
	 */
	public void setAllField(String fullName, String address, String login,
			String password, String mail) {
		this.fullName = fullName;
		this.address = address;
		this.login = login;
		this.password = password;
		this.mail = mail;
	}

	/**
	 * Get mail
	 * 
	 * @return mail
	 */
	public String getMail() {
		return mail;
	}

	/**
	 * Set mail
	 * 
	 * @param mail mail
	 */
	public void setMail(String mail) {
		this.mail = mail;
	}

	/**
	 * Get repassword
	 * 
	 * @return password
	 */
	public String getRepassword() {
		return repassword;
	}

	/**
	 * Set repassword
	 * 
	 * @param repassword password
	 */
	public void setRepassword(String repassword) {
		this.repassword = repassword;
	}

	/**
	 * Get phone
	 * 
	 * @return number
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * Set phone
	 * 
	 * @param phone number
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}
}
