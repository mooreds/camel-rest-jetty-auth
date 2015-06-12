package com.mooreds.camel.security;

import java.io.IOException;
import java.security.Principal;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.security.auth.Subject;

import org.eclipse.jetty.security.DefaultIdentityService;
import org.eclipse.jetty.security.IdentityService;
import org.eclipse.jetty.security.LoginService;
import org.eclipse.jetty.security.MappedLoginService;
import org.eclipse.jetty.server.UserIdentity;
import org.eclipse.jetty.util.security.Credential;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HardcodedLoginService implements LoginService {
    private final static Logger log = LoggerFactory.getLogger(HardcodedLoginService.class);

    private final Map users = new ConcurrentHashMap();

    // matches what is in the constraint object in the spring config
    private final String[] ACCESS_ROLE = new String[] { "rolename" };
	
    private IdentityService identityService = new DefaultIdentityService();

	@Override
	public IdentityService getIdentityService() {
		return identityService;
	}

	@Override
	public String getName() {
		return "";
	}

	@Override
	public UserIdentity login(String username, Object creds) {
		
        UserIdentity user = null;
        
        
        // HERE IS THE HARDCODING
		boolean validUser = "ralph".equals(username) && "s3cr3t".equals(creds);
		if (validUser) {
			Credential credential = (creds instanceof Credential)?(Credential)creds:Credential.getCredential(creds.toString());

		    Principal userPrincipal = new MappedLoginService.KnownUser(username,credential);
		    Subject subject = new Subject();
		    subject.getPrincipals().add(userPrincipal);
		    subject.getPrivateCredentials().add(creds);
		    subject.setReadOnly();
		    user=identityService.newUserIdentity(subject,userPrincipal, ACCESS_ROLE);
		    users.put(user.getUserPrincipal().getName(), true);
		}

	    return (user != null) ? user : null;
	}

	@Override
	public void logout(UserIdentity arg0) {
		
	}

	@Override
	public void setIdentityService(IdentityService arg0) {
	     this.identityService = arg0;
		
	}

	@Override
	public boolean validate(UserIdentity user) {
		if (users.containsKey(user.getUserPrincipal().getName()))
            return true;

        return false;	
	}
}
