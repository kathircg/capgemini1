import java.math.BigDecimal;

import junit.framework.Assert;

import org.junit.Test;


public class TestClass {
	
	//Creating a Account object and checking object creation succeeds
	@Test
	public void testCreateValidAccount(){
		WalletService service =new WalletServiceImpl();
		BigDecimal balance= new BigDecimal(34099);
		Account account= new Account("Smith", "9797979797",balance);
		Assert.assertNotNull(account);
	}
	
	//Create a valid account object and check if account id is auto generated
	@Test
	public void testCreateAccount(){
		WalletService service =new WalletServiceImpl();
		
		BigDecimal balance= new BigDecimal(34099);
		Account account= new Account("Smith", "9797979797",balance);
		
		int accId =service.addAccount(account);
		Assert.assertNotNull(accId);
	}
	
	//Create a account with invalid name - a null object should be returned
	@Test
	public void testCreateAccountForNullName(){
		WalletService service =new WalletServiceImpl();
		BigDecimal balance= new BigDecimal(34099);
		Account account= new Account(null, "9797979797",balance);
		Assert.assertNull(account);
	}
	
	//Create a account with invalid mobile number - a null object should be returned
	@Test
	public void testCreateAccountForNullMobileNo(){
		WalletService service =new WalletServiceImpl();
		BigDecimal balance= new BigDecimal(34099);
		Account account= new Account("Smith", null,balance);
		Assert.assertNull(account);
	}
	
	//Create a account with invalid account balance - a null object should be returned
	@Test
	public void testCreateAccountForNegativeAmount(){
		WalletService service =new WalletServiceImpl();
		BigDecimal balance= new BigDecimal(-34099);
		Customer customer =service.createAccount("Smith", "9797979797",balance);
		Assert.assertNull(customer);
	}
	
	//Create a account with invalid phone number - a null object should be returned
	@Test
	public void testCreateAccountForInvalidMobileNo(){
		WalletService service =new WalletServiceImpl();
		BigDecimal balance= new BigDecimal(34099);
		Customer customer =service.createAccount("Smith", "977",balance);
		Assert.assertNull(customer);
	}
	
	//Create a account and check if the account balance is correct
	@Test
	public void testAccountBalance(){
		WalletService service =new WalletServiceImpl();
		BigDecimal balance= new BigDecimal(56565);
		
		//Create an account object
        Account account= new Account("Smith", "9797979797",balance);
        //Get the account id of the newly created account
		int accId =service.addAccount(account);
		//fetch the account balance for that account Id
		BigDecimal result =service.getBalance(accId);
		//Check the correct value of account Balance
		Assert.assertEquals(balance,result);
	}

	//To test if fund transfer fails with invalid account Id's
	@Test
	public void testFundTransferForNullData(){
		WalletService service =new WalletServiceImpl();
		BigDecimal amount= new BigDecimal(56565);
		
	    Boolean status=service.fundTransfer(null, null, amount);
		Assert.assertFalse(status);
		
	    Boolean status=service.fundTransfer(12345, null, amount);
	    Assert.assertFalse(status);
		
	    Boolean status=service.fundTransfer(null, 12345, amount);
	    Assert.assertFalse(status);
	}
	
	//To test if fund transfer succeeds with valid data
	@Test
	public void testFundTransferForValidData(){
		WalletService service =new WalletServiceImpl();
        BigDecimal amount= new BigDecimal(56565);
		
	    Boolean status=service.fundTransfer(12345,53989, amount);
		Assert.assertTrue(status);
	}
	
	//To test if fund transfer fails for invalid transfer amount
	@Test
	public void testFundTransferForInvalidAmount(){
		WalletService service =new WalletServiceImpl();
		BigDecimal bg= new BigDecimal(-34620.99);
		
		 Boolean status=service.fundTransfer(12345,53989, bg);
		 Assert.assertTrue(status);
	}
	
	
}

