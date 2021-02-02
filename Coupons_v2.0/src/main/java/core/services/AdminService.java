package core.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import core.entities.Company;
import core.entities.Customer;
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
		if (companyRepository.findByNameAndEmail(company.getName(), company.getEmail()) == null) {
			companyRepository.save(company);
		} else {
			throw new CouponSystemException("[x] OPERATION FAILED >>> add company: already exists");
		}
	}

	public boolean updateCompany(Company company) throws CouponSystemException {
		Optional<Company> opt = companyRepository.findById(company.getId());
		if (opt.isPresent()) {
			Company companyDB = opt.get();
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
		Optional<Company> opt = companyRepository.findById(id);
		if (opt.isPresent()) {
			companyRepository.delete(opt.get());
			return opt.get();
		} else {
			throw new CouponSystemException("[X] OPERATION FAILED >>> delete company: not found");
		}
	}

	public Company getOneCompany(Integer id) throws CouponSystemException {
		Optional<Company> opt = companyRepository.findById(id);
		if (opt.isPresent())
			return opt.get();
		else
			throw new CouponSystemException("[X] OPERATION FAILED >>> get company: not found");
	}

	public List<Company> getAllComapnies() throws CouponSystemException {
		List<Company> companies = companyRepository.findAll();
		if (companies != null)
			return companies;
		else
			throw new CouponSystemException("[X] OPERATION FAILED >>> get all companies: empty list");
	}

	public void addCustomer(Customer customer) throws CouponSystemException {
		if (customerRepository.findByEmail(customer.getEmail()) == null) {
			customerRepository.save(customer);
		} else {
			throw new CouponSystemException("[x] OPERATION FAILED >>> add customer: already exists");
		}
	}

	public void updateCustomer(Customer customer) throws CouponSystemException {

	}

	public void deleteCustomer(Customer customer) throws CouponSystemException {

	}

	public Customer getOneCustomer(Integer id) throws CouponSystemException {
		return null;
	}

	public List<Customer> getAllCustomers() throws CouponSystemException {
		return null;
	}

}
