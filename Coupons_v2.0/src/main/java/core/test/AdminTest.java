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

	public static void login() {
		try {
			service = (AdminService) manager.login(email, password, ClientType.ADMINISTRATOR);
		} catch (CouponSystemException e) {
			e.printStackTrace();
		}
	}

	public static void createCompanies() {
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

	public static void updateCompany() {
		try {
			Company company = service.getOneCompany(2);

			company.setName("LG");
			company.setEmail("lg@mail");
			company.setPassword("lg1234");
			if (service.updateCompany(company))
				System.out.println(service.getOneCompany(company.getId()));

		} catch (CouponSystemException e) {
			e.printStackTrace();
		}

	}

	public void testAll() {
		login();
		createCompanies();
		updateCompany();
	}

	Company deletedCompany = adminService
			.deleteCompany(c3.getId());System.out.println("Deleted company: "+deletedCompany);

	List<Company> companies = adminService.getAllComapnies();if(companies!=null)
	{
		System.out.println(">>> List of companies");
		for (Company company : companies) {
			System.out.println(company);
		}
	}else
	{
		System.out.println(">>> List of companies is empty");
	}

	Customer cs1 = adminService.addCustomer(new Customer("Avi", "Aaa", "avi@mail", "avi1234"));
	Customer cs2 = adminService.addCustomer(new Customer("Beny", "Bbb", "beny@mail", "beny1234"));
	Customer cs3 = adminService.addCustomer(new Customer("Eldad", "Ccc", "eldad@mail", "eldad1234"));
	Customer cs4 = adminService.addCustomer(new Customer("Dana", "Ddd", "dana@mail", "dana1234"));
	Customer cs5 = adminService.addCustomer(new Customer("Or", "Eee", "or@mail", "or1234"));

	System.out.println(cs1);System.out.println(cs2);System.out.println(cs3);System.out.println(cs4);System.out.println(cs5);

	cs3.setFirstName("Roman");cs3.setLastName("Nemirovski");cs3.setEmail("raspizdyai@forever");cs3.setPassword("18cm");if(adminService.updateCustomer(cs3))System.out.println(adminService.getOneCustomer(cs3.getId()));

	Customer deletedCustomer = adminService
			.deleteCustomer(cs3.getId());System.out.println("Deleted customer: "+deletedCustomer);

	List<Customer> customers = adminService.getAllCustomers();if(customers!=null)
	{
		System.out.println(">>> List of customers");
		for (Customer customer : customers) {
			System.out.println(customer);
		}
	}else
	{
		System.out.println(">>> List of customers is empty");

	}

}

}
