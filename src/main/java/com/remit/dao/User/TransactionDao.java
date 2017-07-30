
package com.remit.dao.User;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TransactionDao {

    public String getDate() {

	return date;
    }

    public void setDate(String date) {

	this.date = date;
    }

    public String getDescription() {

	return description;
    }

    public void setDescription(String description) {

	this.description = description;
    }

    public String getAmount() {

	return amount;
    }

    public void setAmount(String amount) {

	this.amount = amount;
    }

    public String getCurrency() {

	return currency;
    }

    public void setCurrency(String currency) {

	this.currency = currency;
    }

    public String getStatus() {

	return status;
    }

    public void setStatus(String status) {

	this.status = status;
    }

    public String getBalance() {

	return balance;
    }

    public void setBalance(String balance) {

	this.balance = balance;
    }

    public String getReason() {

	return reason;
    }

    public void setReason(String reason) {

	this.reason = reason;
    }

    private String date;

    private String userID;

    public String getUserID() {

	return userID;
    }

    public void setUserID(String userID) {

	this.userID = userID;
    }

    private String id;

    public TransactionDao() {

	super();
	id = UUID.randomUUID().toString();
    }

    public String getId() {

	return id;
    }

    public void setId(String id) {

	this.id = id;
    }

    private String description;

    private String amount;

    private String currency;

    private String status;

    private String balance;

    private String reason;

}
