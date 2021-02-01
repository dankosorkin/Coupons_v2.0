package core.services.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import core.entities.Company;
import core.services.AdminService;

@Service
@Transactional
public class AdminServiceImpl extends ClientServiceImpl implements AdminService {

	@Override
	public void login(String email, String password) {
		// TODO Auto-generated method stub
	}

	public void addCompany(Company company) {
		companyRepository.save(company);
	}

}
