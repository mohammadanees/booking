package com.assignment.pages;


import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.assignment.base.TestBase;



public class HotelBookingFormPage extends TestBase {
	
	@FindBy(id="firstname")
	WebElement firstNameEle;
	
	@FindBy(id="lastname")
	WebElement lastNameEle;
	
	@FindBy(id="totalprice")
	WebElement totalPiceEle;
	
	@FindBy(id="depositpaid")
	WebElement isDepositEle;
	
	@FindBy(id="checkin")
	WebElement checkInEle;
	
	@FindBy(id="checkout")
	WebElement checkOutEle;
	
	@FindBy(xpath = "//*[@value=' Save ']")
	WebElement saveBtnEle;
	
	@FindBy(xpath = "//*[@value='Delete']")
	WebElement deleteBtnEle;
	
	@FindBy(xpath = "//*[@id='bookings']/div/div/p")
	List<WebElement> bookingTable;
	
	@FindBy(xpath = "//*[@value='Delete']")
	List<WebElement> deleteBtnColl;
	
	WebDriver driver;
	
	public HotelBookingFormPage() {
		initialization();
		this.driver=getDriver();
		PageFactory.initElements(driver, this);
	}
	
	public void fillFirstName(String firstNameValue) {
		input(firstNameEle, firstNameValue, driver);
		
	}
	
	public void fillLastName(String lastNameValue) {
		input(lastNameEle, lastNameValue, driver);
		
	}
	
	public void fillTotalPrice(String totalPriceVal) {
		input(totalPiceEle, totalPriceVal, driver);
	}
	
	public void selectIsDeposit(String isDepositVal) {
		selectOptionByValue(isDepositEle, isDepositVal);	
		}
	
	public void fillCheckInDate(String checkInDateVal) {
		input(checkInEle, checkInDateVal, driver);
	}
	
	public void fillCheckOutDate(String checkOutDateVal) {
		input(checkOutEle, checkOutDateVal, driver);
	}
	
	public void clickSaveBtn() {
		click(saveBtnEle, driver);
	}
	
	public void clickDeleteBtn() {
		click(deleteBtnEle, driver);
	}
	
	public void fnFillBookingFormDetails(String firstNameVal, String lastNameVal, String totalPriceVal, String isDepositVal, String checkInDateVal, String checkOutDateVal) throws InterruptedException {
		waitForVisibilityOf(firstNameEle, driver);
		fillFirstName(firstNameVal);
		fillLastName(lastNameVal);
		fillTotalPrice(totalPriceVal);
		selectIsDeposit(isDepositVal);
		fillCheckInDate(checkInDateVal);
		fillCheckOutDate(checkOutDateVal);
	}
	
	public void verifyVal(int initIteration, String expectedValue) {
		try {
		waitForVisibilityOf(firstNameEle, driver);
		Thread.sleep(15000);
		int len=bookingTable.size();
		String actVal="";
		int cnt=0;
		for(int i=initIteration;i<=len;i=i+6) {
			actVal=bookingTable.get(i).getText();
			if(actVal.equalsIgnoreCase(expectedValue)) {
				cnt=cnt+1;
				break;
			}  
		}
		
		if (cnt>0) {
			Assert.assertTrue("User details have been added successfully", actVal.equalsIgnoreCase(expectedValue));
		}else {
			Assert.assertTrue("User details have not been added successfully", actVal.equalsIgnoreCase(expectedValue));
		}
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public void verifyValueInTable(int initIteration, String expectedValue) {
		
		verifyVal(initIteration, expectedValue);
	}
	

	
	public void deleteUserDetails(String str) {
		waitForVisibilityOf(firstNameEle, driver);
		int len=bookingTable.size();
		String actVal="";
		int j=0;
		for(int i=0;i<len;i=i+6) {
			j=j+1;
			actVal=bookingTable.get(i).getText();
			if(actVal.equalsIgnoreCase(str)) {
				//deleteBtnColl.get(j).click();
				driver.findElement(By.xpath("(//*[@value='Delete'])["+j+"]")).click();
				break;
			}  
		}
	}
	
	public void verifyUserDeleted(int initIteration, String expectedValue) {
		try {
			waitForVisibilityOf(firstNameEle, driver);
			Thread.sleep(15000);
			int len=bookingTable.size();
			String actVal="";
			int cnt=0;
			for(int i=initIteration;i<len;i=i+6) {
				actVal=bookingTable.get(i).getText();
				if(actVal.equalsIgnoreCase(expectedValue)) {
					cnt=cnt+1;
					break;
				}  
			}
			
			if (cnt==0) {
				Assert.assertTrue("User details have been deleted successfully", !actVal.equalsIgnoreCase(expectedValue));
			}else {
				Assert.assertTrue("User details have not been deleted successfully", actVal.equalsIgnoreCase(expectedValue));
			}
			
			} catch (Exception e) {
				e.printStackTrace();
			}
	}

}
