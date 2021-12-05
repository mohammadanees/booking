package com.assignment.runner;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;



@RunWith(Cucumber.class)
@CucumberOptions(
features = {"src/test/resources/features/HotelBookingForm.feature"},
glue = {"com.assignment.stepdef"},
dryRun = false,
plugin = {"json:target/cucumber.json"})

public class RunCukesTest {
	


}
