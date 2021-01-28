package core.services;

import org.springframework.beans.factory.annotation.Autowired;

import core.exceprtions.CouponSystemException;
import core.repositories.CompanyRepository;
import core.repositories.CouponRepository;
import core.repositories.CustomerRepository;

public abstract class ClientService {

	@Autowired
	private CompanyRepository companyRepo;
	@Autowired
	private CouponRepository couponRepo;
	@Autowired
	private CustomerRepository customerRepo;

	protected abstract boolean login(String email, String password) throws CouponSystemException;

}
