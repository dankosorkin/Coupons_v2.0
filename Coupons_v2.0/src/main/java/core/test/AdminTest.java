package core.test;

import org.springframework.beans.factory.annotation.Autowired;

import core.exceprtions.CouponSystemException;
import core.login.ClientType;
import core.login.LoginManager;
import core.services.AdminService;

public class AdminTest {

	@Autowired
	private LoginManager manager;
	private AdminService service;

	public AdminTest() {
		try {
			this.service = (AdminService) manager.login("admin", "admin1234", ClientType.ADMINISTRATOR);
		} catch (CouponSystemException e) {
			e.printStackTrace();
		}
	}

	public void adminTest() {

	}

}
