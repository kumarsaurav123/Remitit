
package com.remit.it.helper;

import com.remit.it.RemitConfiguration;

public final class ConfigurationHelper {

    private RemitConfiguration config;

    public ConfigurationHelper(RemitConfiguration configuration) {

	// TODO Auto-generated constructor stub
    }

    public RemitConfiguration getConfig() {

	return config;
    }

    public void setConfig(RemitConfiguration config) {

	this.config = config;
    }

    public static void setInstance(RemitConfiguration configuration) {

	if (helper == null) {
	    helper = new ConfigurationHelper(configuration);
	}
    }

    public static ConfigurationHelper getInstance() {

	return helper;
    }

    public static ConfigurationHelper getHelper() {

	return helper;
    }

    public static void setHelper(ConfigurationHelper helper) {

	ConfigurationHelper.helper = helper;
    }

    private static ConfigurationHelper helper;

}
