package testCases;

import java.time.Duration;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;


public class TC001_AcccountRegistrationTest extends BaseClass{
	
	
	
	@Test(groups={"Regression","Master"})
	public void verify_account_registration() throws InterruptedException {
		
		logger.info("***** Starting TC001_AccountRegistrationTest *****");
		
		try {
			HomePage hp = new HomePage(driver);
			hp.clickMyAccount();
			logger.info("Clicked on MyAccount Link ");
			hp.clickRegister();
			logger.info("Clicked on Register Link ");
			
			
			AccountRegistrationPage regPage = new AccountRegistrationPage(driver);
			
			logger.info("Providing Customer details ");
			
			regPage.setFirstName(randomString().toUpperCase());
			regPage.setLastName(randomString().toUpperCase());
			regPage.setEmail(randomString() + "@gmail.com");
			regPage.setTelephone(randomNumber());
			
			String password = randomAlphaNumeric();
			System.out.println(password);
			regPage.setPassword(password);
			Thread.sleep(2000);
			regPage.setConfirmPassword(password);
			
			regPage.setPrivacyPolicy();
			regPage.clickContinue();
			
			logger.info("Validating expected message... ");
			
			String confMsg = regPage.getConfirmationMsg();
			System.out.println(confMsg);
			
			if(confMsg.equals("Your Account Has Been Created!")) {
				Assert.assertTrue(true);
			}
			else {
				logger.error("Test Failed...");
				logger.debug("Debug logs...");
				Assert.assertTrue(false);
			}
			
			//Assert.assertEquals(confMsg, "Your Account Has Been Created!ss");
		}catch (Exception e) {
			
			Assert.fail();
		}
		
		logger.info("***** Finished TC001_AccountRegistrationTest *****");
		
		
	}
}
