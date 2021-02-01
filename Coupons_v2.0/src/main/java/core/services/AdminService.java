package core.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import core.entities.Company;

@Service
@Transactional
public class AdminService extends ClientService {

	private String email = "admin";
	private String password = "admin1234";

	@Override
	public boolean login(String email, String password) {
		if (this.email == email && this.password == password)
			return true;
		return false;

	}

	public void addCompany(Company company) {
		companyRepository.save(company);
	}

}
