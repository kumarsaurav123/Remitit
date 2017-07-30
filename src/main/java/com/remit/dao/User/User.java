
package com.remit.dao.User;

import java.io.Serializable;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class User implements Serializable {

    @Override
    public String toString() {

	return "User [tokenID=" + tokenID + ", currency=" + currency + ", balance=" + balance + ", getTokenID()=" + getTokenID() + ", getCurrency()=" + getCurrency() + ", getBalance()="
		+ getBalance() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
    }

    public User() {

	super();
	this.tokenID = UUID.randomUUID().toString();
	this.currency = "INR";
	this.balance = "10000";
    }

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private String tokenID;

    private String currency;

    public String getTokenID() {

	return tokenID;
    }

    public void setTokenID(String tokenID) {

	this.tokenID = tokenID;
    }

    public String getCurrency() {

	return currency;
    }

    public void setCurrency(String currency) {

	this.currency = currency;
    }

    public String getBalance() {

	return balance;
    }

    public void setBalance(String balance) {

	this.balance = balance;
    }

    private String balance;

}
