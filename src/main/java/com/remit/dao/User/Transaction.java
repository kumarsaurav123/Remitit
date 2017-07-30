
package com.remit.dao.User;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Transaction implements Serializable {

    // date, description, amount, currency

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private String date;

    private String description;

    private String amount;

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

    private String currency;

}
