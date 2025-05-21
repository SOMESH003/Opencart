package testCases;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

public class TC003_LoginDDT extends BaseClass {
	
	
	@Test(dataProvider = "LoginData",dataProviderClass = DataProviders.class, groups="Datadriven")
	public void verify_loginDDT(String email, String pwd, String exp) throws InterruptedException {
		
		logger.info("***** starting TC003_LoginDDT *****");
		
		try {
			// HomePage
			HomePage hp = new HomePage(driver);
			hp.clickMyAccount();
			hp.clickLogin();
			
			// Login
			LoginPage lp = new LoginPage(driver);
			lp.setEmail(email);
			lp.setPassword(pwd);
			lp.clickLogin();
			
			// MyAccount
			MyAccountPage macc = new MyAccountPage(driver);
			boolean targetPage = macc.isMyAccountPageExists();
			
			System.out.println(targetPage);
			if(exp.equalsIgnoreCase("Valid"))  {
				
				if(targetPage==true) {
					macc.clickLogout();
					Assert.assertTrue(true);
					System.out.println("---> Pass");
				}
				else {
					Assert.assertTrue(false);
					System.out.println("---> fail");
				}
			}
			else if(exp.equalsIgnoreCase("Invalid")) {
				if(targetPage==true) {
					macc.clickLogout();
					Assert.assertTrue(false);
					System.out.println("---> Fail");
				}
				else {
					Assert.assertTrue(true);
					System.out.println("---> Pass");
//					Assert.fail();
				}
			}
		}catch (Exception e) {
			Assert.fail();
		}
		
		logger.info("***** Finished TC003_LoginDDT *****");
		
	}

}
