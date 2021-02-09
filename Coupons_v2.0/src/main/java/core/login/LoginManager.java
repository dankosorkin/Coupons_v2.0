package core.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import core.exceptions.CouponSystemException;
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
			if (adminService.login(email, password))
				return adminService;
		case COMPANY:
			if (companyService.login(email, password))
				return companyService;
		case CUSTOMER:
			if (customerService.login(email, password))
				return customerService;
		default:
			throw new CouponSystemException("Client type doesnt recongnized");
		}

	}
}
