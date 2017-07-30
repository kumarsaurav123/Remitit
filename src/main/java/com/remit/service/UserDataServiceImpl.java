
package com.remit.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.WriteResult;
import com.remit.dao.User.TransactionDao;
import com.remit.dao.User.User;
import com.remit.it.utils.DBUtils;

public class UserDataServiceImpl {

    public static final ObjectMapper OBJECT_MAPPER = new ObjectMapper().setPropertyNamingStrategy(PropertyNamingStrategy.CAMEL_CASE_TO_LOWER_CASE_WITH_UNDERSCORES);
    static {
    }

    public int insertUser(User user) {

	Map<String, Object> map = OBJECT_MAPPER.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false).convertValue(user, Map.class);
	BasicDBObject dbObject = new BasicDBObject(map);

	WriteResult updateResult = DBUtils.getMongoDB().getCollection("USER").insert(dbObject);
	if (updateResult.getN() == 0) {
	    return 0;
	} else if (updateResult.getError() == null) {
	    return 1;
	} else {
	    return -1;
	}
    }

    public User getUserbyTokenId(String tokenID) {

	User user = null;
	DBObject userobj = getMongoDB().getCollection("USER").findOne(new BasicDBObject().append("token_id", tokenID));

	if (userobj != null) {
	    user = OBJECT_MAPPER.convertValue(userobj, User.class);
	}
	return user;
    }

    public List<TransactionDao> getTransactionHistory(String tokenID) {

	TransactionDao transaction = null;
	List<TransactionDao> transactionslist = new ArrayList<TransactionDao>();
	DBCursor transactions = getMongoDB().getCollection("TransactionHistory").find(new BasicDBObject().append("user_id", tokenID));
	Iterator<DBObject> itr = transactions.iterator();
	while (itr.hasNext()) {
	    DBObject transactionobj = itr.next();
	    if (transactionobj != null) {
		transaction = OBJECT_MAPPER.convertValue(transactionobj, TransactionDao.class);
		transactionslist.add(transaction);
	    }
	}

	return transactionslist;
    }

    private DB getMongoDB() {

	// TODO Auto-generated method stub
	// return null;
	return DBUtils.getMongoDB();
    }

    public void updateUserBalance(String tokenID, User user) {

	// Map<String, Object> map =
	// OBJECT_MAPPER.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS,
	// false).convertValue(user, Map.class);
	BasicDBObject dbObject = new BasicDBObject();
	dbObject.put("balance", user.getBalance());
	BasicDBObject updateDbObject = new BasicDBObject();
	BasicDBObject searchQuery = new BasicDBObject().append("token_id", tokenID);
	getMongoDB().getCollection("USER").update(searchQuery, dbObject);

    }

    public int insertTransaction(TransactionDao transactionDao) {

	Map<String, Object> map = OBJECT_MAPPER.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false).convertValue(transactionDao, Map.class);
	BasicDBObject dbObject = new BasicDBObject(map);

	WriteResult updateResult = DBUtils.getMongoDB().getCollection("TransactionHistory").insert(dbObject);
	if (updateResult.getN() == 0) {
	    return 0;
	} else if (updateResult.getError() == null) {
	    return 1;
	} else {
	    return -1;
	}
    }
}
