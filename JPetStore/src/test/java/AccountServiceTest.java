import static org.junit.Assert.assertNotNull;

import org.junit.BeforeClass;
import org.junit.Test;

import com.ibatis.jpetstore.domain.Account;
import com.ibatis.jpetstore.service.AccountService;

public class AccountServiceTest {
	
	static AccountService accountService;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		accountService = new AccountService();
	}

	@Test
	public void testGetAccountString() {
		assertNotNull(accountService.getAccount("j2ee"));
	}

	@Test
	public void testGetAccountStringString() {
		assertNotNull(accountService.getAccount("j2ee", "j2ee"));
	}

/*	@Test
	public void testInsertAccount() {
		Account account = new Account();
		account.setFirstName("abc");
		account.setLastName("xyz");
		accountService.insertAccount(account);
	}*/

	@Test
	public void testUpdateAccount() {
		Account account = new Account();
		accountService.updateAccount(account);
	}

	@Test
	public void testGetUsernameList() {
		assertNotNull(accountService.getUsernameList());
	}

}
