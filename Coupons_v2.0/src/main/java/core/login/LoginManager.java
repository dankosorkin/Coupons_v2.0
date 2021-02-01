package core.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import core.exceprtions.CouponSystemException;
import core.services.AdminService;
import core.services.ClientService;
import core.services.CompanyService;
import core.services.CustomerService;

@Component
public class LoginManager {

	@Autowired
	private AdminService adminService;
	@Autowired
	private CompanyService companyService;
	@Autowired
	private CustomerService customerService;

	public LoginManager() {
	}

	public ClientService login(String email, String password, ClientType client) throws CouponSystemException {

		switch (client) {
		case ADMINISTRATOR:
			return adminService;
		case COMPANY:
			return companyService;
		case CUSTOMER:
			return customerService;
		default:
			throw new CouponSystemException("Client type doesnt recongnized");
		}

	}
}
