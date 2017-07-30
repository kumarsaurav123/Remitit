
package com.remit.it;

import io.dropwizard.Application;
import io.dropwizard.auth.basic.BasicAuthProvider;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

import com.meltmedia.dropwizard.mongo.MongoBundle;
import com.mongodb.MongoClient;
import com.remit.dao.User.User;
import com.remit.it.helper.ConfigurationHelper;
import com.remit.it.utils.DBUtils;
import com.remit.resources.UserResource;
import com.remit.service.UserBusinessService;

/**
 * Hello world!
 *
 */
public class RemitApplication extends Application<RemitConfiguration> {

    private MongoBundle<RemitConfiguration> mongoBundle;

    private UserBusinessService userBusinessService;

    public UserBusinessService getUserBusinessService() {

	return userBusinessService;
    }

    public void setUserBusinessService(UserBusinessService userBusinessService) {

	this.userBusinessService = userBusinessService;
    }

    public static void main(String[] args) {

	try {
	    new RemitApplication().run(args);
	} catch (Exception e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}

    }

    @Override
    public void initialize(Bootstrap<RemitConfiguration> bootstrap) {

	// TODO Auto-generated method stub
	// SwaggerBundle<RemitConfiguration> swaggerBundle = new
	// SwaggerBundle<RemitConfiguration>() {
	//
	// @Override
	// protected SwaggerBundleConfiguration
	// getSwaggerBundleConfiguration(RemitConfiguration ekoConfiguration) {
	//
	// // this would be the preferred way to set up swagger,
	// // you can also construct the object here programtically if you
	// // want
	// return ekoConfiguration.getSwaggerBundleConfiguration();
	// }
	// };
	// com.wordnik.swagger.util.Json.mapper().setPropertyNamingStrategy(PropertyNamingStrategy.CAMEL_CASE_TO_LOWER_CASE_WITH_UNDERSCORES);
	// bootstrap.addBundle(swaggerBundle);
	mongoBundle = MongoBundle.<RemitConfiguration> builder().withConfiguration(RemitConfiguration::getMongo).build();
	bootstrap.addBundle(mongoBundle);
    }

    @Override
    public void run(RemitConfiguration configuration, Environment environment) throws Exception {

	final Morphia morphia = new Morphia();
	morphia.mapPackage("com.remit.it.domain");
	ConfigurationHelper.setInstance(configuration);
	Datastore datastore = morphia.createDatastore(new MongoClient(), configuration.getMongo().getDatabase());
	datastore.ensureIndexes();
	DBUtils.setDatastore(datastore);
	DBUtils.setMongoDB(mongoBundle.getDB());
	setUserBusinessService(new UserBusinessService(mongoBundle.getDB(), configuration));
	environment.jersey().register(new UserResource());
	environment.jersey().register(new BasicAuthProvider<SimplePrincipal>(new SimpleAuthenticator(), "SUPER SECRET STUFF"));
    }
}