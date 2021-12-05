package com.assignment.stepdef;



import com.assignment.pages.HotelBookingFormPage;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;


public class MainSteps {
	
	private HotelBookingFormPage hotelBookingFormPage;
	
	@Given("^I am at the hotel booking form page$")
	public void i_am_at_the_hotel_booking_form_page() throws Throwable {
		hotelBookingFormPage=new HotelBookingFormPage();
	}

	@Given("^Fill \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\"$")
	public void fill(String arg1, String arg2, String arg3, String arg4, String arg5, String arg6) throws Throwable {
		hotelBookingFormPage.fnFillBookingFormDetails(arg1, arg2, arg3, arg4, arg5, arg6);
	}

	@When("^I click on save button$")
	public void i_click_on_save_button() throws Throwable {
		hotelBookingFormPage.clickSaveBtn();
	}

	@Then("^user \"([^\"]*)\"and \"([^\"]*)\" should be added successfully$")
	public void user_and_should_be_added_successfully(String arg1, String arg2) throws Throwable {
		hotelBookingFormPage.verifyValueInTable(0, arg1);
		hotelBookingFormPage.verifyValueInTable(1, arg2);
	}
	
	
	@Given("^Delete the user details \"([^\"]*)\"$")
	public void delete_the_user_details(String arg1) throws Throwable {
		hotelBookingFormPage.deleteUserDetails(arg1);
	}

	@Then("^user \"([^\"]*)\" should be deleted successfully$")
	public void user_should_be_deleted_successfully(String arg1) throws Throwable {
		hotelBookingFormPage.verifyUserDeleted(0, arg1);
	}
	

}
