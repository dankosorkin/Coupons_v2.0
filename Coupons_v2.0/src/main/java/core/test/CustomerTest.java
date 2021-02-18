package core.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import core.exceptions.CouponSystemException;
import core.login.ClientType;
import core.login.LoginManager;
import core.services.CustomerService;

@Component
public class CustomerTest {

	private static final String email = "or@mail";
	private static final String password = "or1234";

	@Autowired
	private LoginManager manager;

	private CustomerService service;

	public void login() throws CouponSystemException {
		service = (CustomerService) manager.login(email, password, ClientType.CUSTOMER);
		System.out.println("===== Customer test ======");
	}

	public void testAll() {
		try {
			login();
		} catch (CouponSystemException e) {
			e.printStackTrace();
		}
	}

}
