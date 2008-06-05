package org.insia.eLibrary.test.service;

import org.insia.eLibrary.test.base.Integration;

public class AuthenticationServiceTest extends Integration{



	public AuthenticationServiceTest(String name) {
		super(name);
	}

	/**
	 * Test la r�cup�ration d'un User dans la base
	 */
	public void testGetUser(){
		log.info("Test la r�cup�ration d'un User dans la base");
		//AuthenticationService authenticationService = (AuthenticationService) context.getBean("authenticationService");
		//User user = authenticationService.authenticate("mick","mick");
		//assertEquals(user.getPassword(),"mick");
	}
}
