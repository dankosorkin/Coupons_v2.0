package core.services.impl;

import org.springframework.beans.factory.annotation.Autowired;

import core.repositories.CompanyRepository;
import core.repositories.CouponRepository;
import core.repositories.CustomerRepository;
import core.services.ClientService;

public abstract class ClientServiceImpl implements ClientService {

	@Autowired
	protected CompanyRepository companyRepository;
	@Autowired
	protected CouponRepository couponRepository;
	@Autowired
	protected CustomerRepository customerRepository;

}
