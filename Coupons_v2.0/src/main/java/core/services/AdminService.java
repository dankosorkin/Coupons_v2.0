package core.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import core.entities.Company;
import core.exceprtions.CouponSystemException;

@Service
@Transactional
public class AdminService extends ClientService {

	private String email = "admin";
	private String password = "admin1234";

	@Override
	public boolean login(String email, String password) {
		return this.email == email && this.password == password;
	}

	public void addCompany(Company company) throws CouponSystemException {
		if (companyRepository.findByName(company.getName()) == null
				&& companyRepository.findByEmail(company.getEmail()) == null) {
			companyRepository.save(company);
		} else {
			throw new CouponSystemException("[x] OPERATION FAILED >>> add company: already exists");
		}
	}

	public boolean updateCompany(Company company) throws CouponSystemException {
		Company companyDB = companyRepository.findByName(company.getName());
		if (companyDB != null) {
			companyDB.setEmail(company.getEmail());
			companyDB.setPassword(company.getPassword());
			companyRepository.save(companyDB);
			return true;
		} else {
			throw new CouponSystemException("[X] OPERATION FAILED >>> update company: not found");
		}
	}

	// customer purchases?
	public Company deleteCompany(Integer id) throws CouponSystemException {
		Optional<Company> companyDB = companyRepository.findById(id);
		if (companyDB.isPresent()) {
			companyRepository.delete(companyDB.get());
			return companyDB.get();
		} else {
			throw new CouponSystemException("[X] OPERATION FAILED >>> delete company: not found");
		}
	}

	public Company getOneCompany(Integer id) throws CouponSystemException {
		Optional<Company> companyDB = companyRepository.findById(id);
		if (companyDB.isPresent())
			return companyDB.get();
		else
			throw new CouponSystemException();
	}

	public List<Company> getAllComapnies() throws CouponSystemException {
		List<Company> companies = companyRepository.findAll();
		if (companies != null)
			return companies;
		else
			throw new CouponSystemException("[X] OPERATION FAILED >>> get all companies: empty list");
	}

}
