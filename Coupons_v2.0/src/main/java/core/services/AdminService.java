package core.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import core.entities.Company;

@Service
@Transactional
public class AdminService extends ClientService {

	@Override
	public void login(String email, String password) {
		// TODO Auto-generated method stub

	}

	public void addCompany(Company company) {
		companyRepository.save(company);
	}

}
