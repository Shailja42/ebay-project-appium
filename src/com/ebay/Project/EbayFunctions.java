package com.ebay.Project;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Random;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.touch.TouchActions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;

import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

public class EbayFunctions {

	WebDriverWait waitVar = null;
	AndroidDriver driver;

	@BeforeTest
	public void setUp() throws MalformedURLException {
		// desired capabilities

		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("BROWSER_NAME", "Android");
		capabilities.setCapability("VERSION", "8.0");
		capabilities.setCapability("UDID", "BQUI8900");
		capabilities.setCapability("deviceName", "Samsung Galaxy S4 Device");
		capabilities.setCapability("platformName", "Android");
		capabilities.setCapability("app",
				new File("C:/Users/akumar94/Downloads/shailja/eBay - Buy, Sell & Save Money-5.8.0.15.apk"));
		driver = new AndroidDriver(new URL("http://0.0.0.0:4723/wd/hub"), capabilities);

		// This package name app
		capabilities.setCapability("appPackage", "com.ebay.mobile.activities");

		// Creating RemoteWebDriver instance and to connect with Appium server
		// It will launch the ebay App in Android Device using the configurations
		// specified in Desired Capabilities

	}

	@Test
		 public void Login()
		 {
//Login in app

		// locate the Text on the calculator MobileBy using MobileBy.name()
		driver.findElement(MobileBy.id("button_sign_in")).click();
		driver.findElement(MobileBy.id("user_id")).sendKeys("shailjagautam13@gmail.com");
		driver.findElement(MobileBy.id("pswd")).sendKeys("mageik@j#i7");
		driver.findElement(MobileBy.id("submit_btn")).click();
		waitVar.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("search_box")));

		 }

@Test
public void addCart() throws InterruptedException
{
	//add in cart

	// locate the Text on the calculator by using By.name()
	// Hide keyboard if visible
	if(driver.findElements(MobileBy.className("UIAKeyboard")).size()!=0)

	{
		driver.hideKeyboard();
	}

	driver.findElement(By.id("search_box")).sendKeys("tv 65 inch");
	// For randomly click an element in list
	driver.findElement(By.id("search_box")).sendKeys("tv stand");
	List<WebElement> elementList = driver
			.findElements(By.id("text"));System.out.println("Total elements : "+elementList.size());

	Random rand = new Random();
	int index = rand.nextInt(elementList.size() - 1);

	elementList.get(index).click();
	// driver.findElement(By.xpath(xpathExpression))


	// Get and print current screen orientation.
	System.out.println("Current screen orientation Is : "+driver.getOrientation());System.out.println("Changing screen Orientation to LANDSCAPE.");
	// Changing screen Orientation to LANDSCAPE.
	driver.rotate(ScreenOrientation.LANDSCAPE);
	// Get and print screen orientation after changing It.
	System.out.println("Now screen orientation Is : "+driver.getOrientation());Thread.sleep(5000);

	// Hiding keyboard if visible
	driver.findElement(By.id("textview_item_title")).click();

	MobileElement element = (MobileElement) driver.findElementById("textview_item_name");
	String elName = element.getText();
	MobileElement element1 = (MobileElement) driver.findElementById("converted_prices_textview");
	String elCost = element1.getText();

	// Scroll function
	TouchActions action = new TouchActions(driver);action.scroll(element,10,100).perform();

	driver.findElement(By.xpath("//android.widget.TextView[@text='Watch']"));

	MobileElement element_cart = (MobileElement) driver.findElementById("item_title");
	String elName_cart = element_cart.getText();
	MobileElement element_price = (MobileElement) driver.findElementById("converted_prices_textview");
	String elPrice_cart = element_price.getText();

	if(elName.equals(elName_cart)&&(elCost.equals(elPrice_cart)))
	{
		System.out.println("Matched");
	}else
	{
		System.out.println("not matched");
	}
}

@Test
void tearDown()
{
	// close the app
	driver.quit();

}}
