
package com.remit.service;

import com.mongodb.DB;
import com.remit.it.RemitConfiguration;

public class UserBusinessService {

    private final DB mongoDB;

    public UserBusinessService(DB mongoDB, RemitConfiguration remitConfiguration) {

	super();
	this.mongoDB = mongoDB;
	this.remitConfiguration = remitConfiguration;
    }

    public DB getMongoDB() {

	return mongoDB;
    }

    public RemitConfiguration getRemitConfiguration() {

	return remitConfiguration;
    }

    private final RemitConfiguration remitConfiguration;
}
