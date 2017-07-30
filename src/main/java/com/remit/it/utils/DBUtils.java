
package com.remit.it.utils;

import org.mongodb.morphia.Datastore;

import com.mongodb.DB;

public final class DBUtils {

    private static Datastore datastore;

    private static DB mongoDB;

    private DBUtils() {

    }

    public static DB getMongoDB() {

	return mongoDB;
    }

    public static void setMongoDB(DB mongoDB) {

	DBUtils.mongoDB = mongoDB;
    }

    public static void setDatastore(Datastore ds) {

	datastore = ds;
    }

    public static Datastore getDatastore() {

	return datastore;
    }

}