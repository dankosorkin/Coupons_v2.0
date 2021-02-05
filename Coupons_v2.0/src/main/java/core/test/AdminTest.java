package core.test;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import core.entities.Company;
import core.entities.Customer;
import core.exceprtions.CouponSystemException;
import core.login.ClientType;
import core.login.LoginManager;
import core.services.AdminService;

@Component
public class AdminTest {

	private static String email = "admin";
	private static String password = "admin1234";

	@Autowired
	private static LoginManager manager;
	private static AdminService service;

	public AdminTest() {
	}

	public void login() {
		try {
			service = (AdminService) manager.login(email, password, ClientType.ADMINISTRATOR);
		} catch (CouponSystemException e) {
			e.printStackTrace();
		}
	}

	public void addCompanies() {
		try {
			Company c1 = service.addCompany(new Company("aaa", "aaa@mail", "pass1234"));
			Company c2 = service.addCompany(new Company("bbb", "bbb@mail", "pass1234"));
			Company c3 = service.addCompany(new Company("ccc", "ccc@mail", "pass1234"));
			Company c4 = service.addCompany(new Company("ddd", "ddd@mail", "pass1234"));
			Company c5 = service.addCompany(new Company("eee", "eee@mail", "pass1234"));

			System.out.println(c1);
			System.out.println(c2);
			System.out.println(c3);
			System.out.println(c4);
			System.out.println(c5);
		} catch (CouponSystemException e) {
			e.printStackTrace();
		}
	}

	public void updateCompany(int id) {
		try {
			Company company = service.getOneCompany(id);

			company.setName("LG");
			company.setEmail("lg@mail");
			company.setPassword("lg1234");
			if (service.updateCompany(company))
				System.out.println(service.getOneCompany(company.getId()));

		} catch (CouponSystemException e) {
			e.printStackTrace();
		}

	}

	public void deleteCompany(int id) {
		Company deletedCompany;
		try {
			deletedCompany = service.deleteCompany(id);
			System.out.println("Deleted company: " + deletedCompany);
		} catch (CouponSystemException e) {
			e.printStackTrace();
		}
	}

	public void getCompany(int id) {
		try {
			System.out.println(service.getOneCompany(id));
		} catch (CouponSystemException e) {
			e.printStackTrace();
		}
	}

	public void getCompanies() {
		List<Company> companies;
		try {
			companies = service.getAllComapnies();
			if (companies != null) {
				System.out.println(">>> List of companies");
				for (Company company : companies) {
					System.out.println(company);
				}
			} else {
				System.out.println(">>> List of companies is empty");
			}
		} catch (CouponSystemException e) {
			e.printStackTrace();
		}
	}

	public void addCustomers() {
		try {
			Customer cs1 = service.addCustomer(new Customer("Avi", "Aaa", "avi@mail", "avi1234"));
			Customer cs2 = service.addCustomer(new Customer("Beny", "Bbb", "beny@mail", "beny1234"));
			Customer cs3 = service.addCustomer(new Customer("Eldad", "Ccc", "eldad@mail", "eldad1234"));
			Customer cs4 = service.addCustomer(new Customer("Dana", "Ddd", "dana@mail", "dana1234"));
			Customer cs5 = service.addCustomer(new Customer("Or", "Eee", "or@mail", "or1234"));

			System.out.println(cs1);
			System.out.println(cs2);
			System.out.println(cs3);
			System.out.println(cs4);
			System.out.println(cs5);
		} catch (CouponSystemException e) {
			e.printStackTrace();
		}
	}

	public void updateCustomer(int id) {
		Customer customer;
		try {
			customer = service.getOneCustomer(id);
			customer.setFirstName("Roman");
			customer.setLastName("Nemirovski");
			customer.setEmail("raspizdyai@forever");
			customer.setPassword("18cm");
			if (service.updateCustomer(customer))
				System.out.println(service.getOneCustomer(customer.getId()));
		} catch (CouponSystemException e) {
			e.printStackTrace();
		}
	}

	public void deleteCustomer(int id) {
		Customer customer;
		try {
			customer = service.deleteCustomer(id);
			System.out.println("Deleted customer: " + customer);
		} catch (CouponSystemException e) {
			e.printStackTrace();
		}
	}

	public void getCustomer(int id) {
	}

	public void getCustomers() {
		List<Customer> customers;
		try {
			customers = service.getAllCustomers();
			if (customers != null) {
				System.out.println(">>> List of customers");
				for (Customer customer : customers) {
					System.out.println(customer);
				}
			} else {
				System.out.println(">>> List of customers is empty");

			}
		} catch (CouponSystemException e) {
			e.printStackTrace();
		}
	}

	public void testAll() {
		login();
		addCompanies();
		updateCompany(2);
		deleteCompany(3);
		getCompany(1);
		getCompanies();
		addCustomers();
		updateCustomer(2);
		deleteCustomer(3);

	}

}
