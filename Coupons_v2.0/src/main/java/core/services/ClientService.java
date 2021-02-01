package core.services;

import org.springframework.beans.factory.annotation.Autowired;

import core.repositories.CompanyRepository;
import core.repositories.CouponRepository;
import core.repositories.CustomerRepository;

public abstract class ClientService {

	@Autowired
	protected CompanyRepository companyRepository;
	@Autowired
	protected CouponRepository couponRepository;
	@Autowired
	protected CustomerRepository customerRepository;

	public abstract void login(String email, String password);

}
