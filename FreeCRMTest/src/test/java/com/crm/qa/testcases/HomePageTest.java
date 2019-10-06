package com.crm.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.crm.qa.base.TestBase;
import com.crm.qa.pages.ContactsPage;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;
import com.crm.qa.util.TestUtil;

public class HomePageTest extends TestBase {

	LoginPage loginPage;
	HomePage homePage;
	TestUtil testUtil;
	ContactsPage contactsPage;

	public HomePageTest() {
		super();
	}

	// Test cases should be independent
	// before each test case -- launch the browser
	// @test - execute the test
	// after each test close the browser

	@BeforeMethod
	public void setup() throws InterruptedException {
		initialization();
		contactsPage = new ContactsPage();
		testUtil = new TestUtil();
		loginPage = new LoginPage();
		homePage = loginPage.Login(prop.getProperty("username"), prop.getProperty("password"));
	}

	@Test(priority = 1)
	public void verifyHomePageTitleTest() throws InterruptedException {
		String homePageTitle = homePage.verifyHomePageTitle();
		System.out.println("Initialize Test 1 ");
		Thread.sleep(3000);
		Assert.assertEquals(homePageTitle, "CRMPRO", "Home Page title not match");
		System.out.println("Passed Test 1 ");
	}

	@Test(priority = 2)
	public void verifyUserNameTest() {
		testUtil.switchToFrame();
		System.out.println("Initialize Test 2 ");
		Assert.assertTrue(homePage.verifyCorrectUserName());
	}

	@Test(priority = 3)
	public void verifyContactsLinkTest() throws InterruptedException {
		testUtil.switchToFrame();
		System.out.println("");
		contactsPage = homePage.clickOnContactsLink();
	}

	@AfterMethod
	public void teardown() {
		driver.quit();
	}

}
