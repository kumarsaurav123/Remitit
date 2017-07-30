
package com.remit.resources;

import io.dropwizard.auth.Auth;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.remit.dao.User.Transaction;
import com.remit.dao.User.TransactionDao;
import com.remit.dao.User.User;
import com.remit.it.SimplePrincipal;
import com.remit.it.utils.IdMutexProvider;
import com.remit.it.utils.IdMutexProvider.Mutex;
import com.remit.service.UserDataServiceImpl;
import com.sun.jersey.core.util.Base64;
import com.wordnik.swagger.annotations.ApiOperation;

@Path("/")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserResource.class);

    @GET
    @ApiOperation("Endpoint to get login token ")
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getToken() {

	// ResourceBundle resourceBundle = ResourceBundle.getBundle("messages",
	// LocaleUtils.toLocale(locale.trim()));
	LOGGER.info("***************************************");
	com.remit.dao.User.User user = new User();
	user.setBalance("10000");
	new UserDataServiceImpl().insertUser(user);
	String token = user.getTokenID() + ":" + "pass";
	byte[] encodedBytes = Base64.encode(token);
	String encodedString = new String(encodedBytes);
	System.out.println("encodedBytes " + new String(encodedBytes));
	return Response.ok().entity(encodedString).build();
    }

    @GET
    @ApiOperation("Endpoint to get login ")
    @Path("/balance")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBalance(@Auth SimplePrincipal simplePrincipal) {

	Mutex mutex = IdMutexProvider.getMutexx(simplePrincipal.getUsername());
	User user = null;
	synchronized (mutex) {
	    // ResourceBundle resourceBundle =
	    // ResourceBundle.getBundle("messages",
	    // LocaleUtils.toLocale(locale.trim()));
	    System.out.println("inside balance");
	    user = new UserDataServiceImpl().getUserbyTokenId(simplePrincipal.getUsername());
	    System.out.println(user);
	    LOGGER.info("***************************************");
	}
	return Response.ok().entity(user).build();
    }

    @GET
    @ApiOperation("Endpoint to get transacton history ")
    @Path("/transactions")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTransactions(@Auth SimplePrincipal simplePrincipal) {

	// ResourceBundle resourceBundle = ResourceBundle.getBundle("messages",
	// LocaleUtils.toLocale(locale.trim()));
	LOGGER.info("***************************************");
	List<TransactionDao> transactions = new UserDataServiceImpl().getTransactionHistory(simplePrincipal.getUsername());
	return Response.ok().entity(transactions).build();
    }

    @POST
    @ApiOperation("Endpoint to spend some money ")
    @Path("/spend")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response spend(@Auth SimplePrincipal simplePrincipal, Transaction transaction) {

	Mutex mutex = IdMutexProvider.getMutexx(simplePrincipal.getUsername());
	User user = null;
	TransactionDao transactionDao = null;
	synchronized (mutex) {
	    user = new UserDataServiceImpl().getUserbyTokenId(simplePrincipal.getUsername());
	    transactionDao = new TransactionDao();
	    transactionDao.setAmount(transaction.getAmount());
	    transactionDao.setCurrency(transaction.getCurrency());
	    transactionDao.setUserID(user.getTokenID());
	    transactionDao.setDate(transaction.getDate());

	    transactionDao.setDescription(transaction.getDescription());
	    if (Double.parseDouble(user.getBalance()) < Double.parseDouble(transaction.getAmount())) {
		transactionDao.setStatus("Failed");
		transactionDao.setReason("Insufficient balance");
	    } else {
		String balance = String.valueOf(Double.parseDouble(user.getBalance()) - Double.parseDouble(transaction.getAmount()));
		user.setBalance(balance);
		transactionDao.setBalance(balance);
		transactionDao.setStatus("Success");
		transactionDao.setReason("N/A");
		new UserDataServiceImpl().insertTransaction(transactionDao);
		new UserDataServiceImpl().updateUserBalance(simplePrincipal.getUsername(), user);

	    }
	    // ResourceBundle resourceBundle =
	    // ResourceBundle.getBundle("messages",
	    // LocaleUtils.toLocale(locale.trim()));
	    LOGGER.info("***************************************");
	}
	return Response.ok().entity(transactionDao).build();
    }
}
