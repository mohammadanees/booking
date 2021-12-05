package com.assignment.stepdef;

import com.assignment.base.TestBase;

import cucumber.api.java.After;

public class Hook extends TestBase{
	
	@After
	public void tearDown() {
		driver.quit();
	}

}
