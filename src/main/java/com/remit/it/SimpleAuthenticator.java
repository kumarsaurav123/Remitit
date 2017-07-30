
package com.remit.it;

import io.dropwizard.auth.AuthenticationException;
import io.dropwizard.auth.Authenticator;
import io.dropwizard.auth.basic.BasicCredentials;

import com.google.common.base.Optional;

public class SimpleAuthenticator implements Authenticator<BasicCredentials, SimplePrincipal> {

    @Override
    public Optional<SimplePrincipal> authenticate(BasicCredentials credentials) throws AuthenticationException {

	// Note: this is horrible authentication. Normally we'd use some
	// service to identify the password from the user name.
	if (!"pass".equals(credentials.getPassword())) {
	    throw new AuthenticationException("Boo Hooo!");
	}

	// from some user service get the roles for this user
	// I am explicitly setting it just for simplicity
	SimplePrincipal prince = new SimplePrincipal(credentials.getUsername());
	prince.getRoles().add(Roles.USER);

	return Optional.fromNullable(prince);
    }
}