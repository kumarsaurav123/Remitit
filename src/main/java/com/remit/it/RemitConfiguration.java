
package com.remit.it;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.meltmedia.dropwizard.mongo.MongoConfiguration;

import io.dropwizard.Configuration;
import io.federecio.dropwizard.swagger.SwaggerBundleConfiguration;

public class RemitConfiguration extends Configuration {

    @JsonProperty("swagger")
    private SwaggerBundleConfiguration swaggerBundleConfiguration;

    public SwaggerBundleConfiguration getSwaggerBundleConfiguration() {

	return swaggerBundleConfiguration;
    }

    public void setSwaggerBundleConfiguration(SwaggerBundleConfiguration swaggerBundleConfiguration) {

	this.swaggerBundleConfiguration = swaggerBundleConfiguration;
    }

    @JsonProperty
    private MongoConfiguration mongo;

    public MongoConfiguration getMongo() {

	return mongo;
    }

    public void setMongo(MongoConfiguration mongo) {

	this.mongo = mongo;
    }

}
