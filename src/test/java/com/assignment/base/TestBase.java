package com.assignment.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.junit.Assert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.github.bonigarcia.wdm.WebDriverManager;

public class TestBase {

	public static WebDriver driver;
	public static Properties prop;
	public static String timeStamp;
	public static String updatedReportFile;
	public static String browser="";
	public static String url="";
	public static DesiredCapabilities caps = new DesiredCapabilities();


	public TestBase() {
		Properties prop = new Properties();
		try {
			 FileInputStream ip=new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\resources\\config.properties");
			prop.load(ip);
			browser=prop.getProperty("browser");
			url=prop.getProperty("url");
		} catch (FileNotFoundException e) {
			// TODO: handle exception
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * 
	 * This method is to launch web browser
	 * 
	 * @return
	 * @return
	 * @return
	 * @throws InterruptedException
	 */
	// initialization
	public static void initialization() {
		if (browser.equalsIgnoreCase("Ch")) {
			WebDriverManager.chromedriver().setup();
			ChromeOptions options = new ChromeOptions();
			// option.addArguments("--headless");
			options.addArguments("start-maximized");
			caps.setCapability(ChromeOptions.CAPABILITY, options);
			driver = new ChromeDriver(options);
		} else if (browser.equalsIgnoreCase("ff")) {
			WebDriverManager.firefoxdriver().setup();
			FirefoxOptions options = new FirefoxOptions();
			options.addArguments("-private");
			caps.setCapability("moz:firefoxOptions", options);
			driver = new FirefoxDriver(options);
		}
		driver.manage().deleteAllCookies();
		driver.get(url);
		driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
	}

	/**
	 * Method used to return web driver object
	 * 
	 * @return
	 */
	public static WebDriver getDriver() {

		return driver;
	}

	/**
	 * Method used to input value
	 * 
	 * @param locatorKey
	 * @param data
	 */
	public void input(WebElement locatorKey, String data, WebDriver driver) {
		 waitForVisibilityOf(locatorKey, driver);
		locatorKey.sendKeys(data);
	}

	/**
	 * Method used to click on web element
	 * 
	 * @param toclick
	 */
	public void click(WebElement toclick, WebDriver driver) {

		waitForVisibilityOf(toclick, driver);
		toclick.click();
	}

	public void click(List<WebElement> ele, WebDriver driver, String sTxt) {
		waitForVisibilityOf(ele, driver, sTxt);
			for (WebElement e : ele) {
				System.out.println(e.getText());
				if (e.getText().contains(sTxt)) {
					e.click();
					break;
				}
			}
	}

	/**
	 * Method used to hover the mouse
	 * 
	 * @param mainmodulename
	 * @param framename
	 * @throws Exception
	 */
	public static void mouseOverElement(WebElement mainmodulename, String framename) throws Exception {
			Actions action = new Actions(driver);
			driver.switchTo().frame(framename);
			action.moveToElement(mainmodulename).build().perform();
	}

	/**
	 * Method used to scroll the web page
	 * 
	 * @param ele
	 */
	public static void scrollToView(WebElement ele) {
		JavascriptExecutor je = (JavascriptExecutor) driver;
		je.executeScript("arguments[0].scrollIntoView(true);", ele);

	}

	/**
	 * Method used to scroll till the end of web page
	 * 
	 * @param ele
	 */
	public static void scrollToEnd(List<WebElement> ele) {
		JavascriptExecutor je = (JavascriptExecutor) driver;
		int i = 1;
		while (isElementPresent(ele) == false) {
			System.out.println("Scroll : " + i);
			je.executeScript("window.scrollTo(0, document.body.scrollHeight)");
			i++;
		}
	}

	/**
	 * This methid will use to scroll to particular element
	 * 
	 * @param ele
	 */
	public static void scrollToElement(WebDriver driver, WebElement ele) {
		JavascriptExecutor je = (JavascriptExecutor) driver;
		je.executeScript("arguments[0].scrollIntoView();", ele);
	}

	/**
	 * Method used to check element present or not
	 * 
	 * @param ele
	 * @return
	 */
	public static boolean isElementPresent(List<WebElement> ele) {

		if (ele.size() == 0)
			return false;
		else
			return true;
	}

	/**
	 * Method used to get text
	 * 
	 * @param mobileElement
	 * @return
	 */
	public String getText(WebElement ele) {
		return ele.getText();
	}

	/**
	 * Method used to wait for availability of element
	 * 
	 * @param ele
	 * @param driver
	 */
	public void waitForVisibilityOf(WebElement ele, WebDriver driver) {
		WebDriverWait webDriverWait = new WebDriverWait(driver, 70);
		webDriverWait.until(ExpectedConditions.visibilityOf(ele));
	}

	public void waitForVisibilityOf(List<WebElement> ele, WebDriver driver, String sTxt) {
		WebDriverWait webDriverWait = new WebDriverWait(driver, 70);
		for (WebElement e : ele) {
			if (e.getText().equalsIgnoreCase(sTxt)) {
				webDriverWait.until(ExpectedConditions.visibilityOf(e));
				break;
			}
		}
	}


	public void closeBrowser() {
		Set<String> allWindows = driver.getWindowHandles();
		for (String window : allWindows) {
			driver.switchTo().window(window);
			driver.close();
		}
	}

	/**
	 * Method used to verify or assert field value
	 * 
	 * @param expectedError
	 * @param element
	 * @throws Throwable
	 */
	public void verifyValue(WebElement element, String expectedValue) throws Throwable {
		String actualValue = getText(element);
		if (actualValue.equalsIgnoreCase(expectedValue)) {
			Assert.assertTrue(actualValue.equalsIgnoreCase(expectedValue));
		} else {
			Assert.fail("Actual value " + element.getText() + " is not equal to expected value " + expectedValue);
		}

	}

	/**
	 * Method used to verify values
	 * 
	 * @param actualValue
	 * @param expectedValue
	 * @throws Throwable
	 */
	public void verifyValue(String actualValue, String expectedValue) throws Throwable {
		if (actualValue.equalsIgnoreCase(expectedValue)) {
			Assert.assertTrue(actualValue.equalsIgnoreCase(expectedValue));
		} else {
			Assert.fail("Actual value " + actualValue + " is not equal to expected value " + expectedValue);
		}
	}

	/**
	 * 
	 * @return
	 */
	public String getCurrentUrl() {
		return driver.getCurrentUrl();
	}

	/**
	 * 
	 * @param driver
	 * @return
	 */
	public String getTitle(WebDriver driver) {
		return driver.getTitle();
	}

	/**
	 * This method will create the list of object.
	 * @param webEle
	 * @param splitString
	 * @return
	 */
	public List<String> listBasedOnWebElements(List<WebElement> webEle, String splitString) {
		List<String> actLocList = new LinkedList<String>();
		for (WebElement ele : webEle) {
			String[] e = ele.getText().split(splitString);
			String actloc = "";
			for (String loc : e) {
				actloc = actloc + loc.toLowerCase() + " ";
			}

			actLocList.add(actloc.trim());
		}
		return actLocList;
	}
	
	public void fnVerifyUrl(WebElement ele, WebDriver driver,String expectedTitle) {
		waitForVisibilityOf(ele, driver);
		String actualTitle = getTitle(driver);
			if(actualTitle.contains(expectedTitle)) {
				Assert.assertTrue(actualTitle.contains(expectedTitle));
			}else {
				Assert.fail("User is unable to redirect to about us page");
			}
	}
	
	public void selectOptionByValue(WebElement webEle, String val) {
		Select dropDownEle=new Select(webEle);
		dropDownEle.selectByVisibleText(val);
	}
	
}
