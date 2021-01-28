package core.test;

import core.entities.Company;
import core.entities.Customer;
import core.services.AdminService;

public class AdminTest {

	private static AdminService service = new AdminService();

	public static void test() {

		System.out.println("============ Admin test =============");
		// create companies
		Company com1 = new Company("FlyingCarpet", "carpet@mail.com", "1234");
		Company com2 = new Company("ElAl", "elal@mail.com", "1234");
		Company com3 = new Company("Sony", "sony@mail.com", "1234");
		Company com4 = new Company("Samsung", "samsung@mail.com", "1234");
		Company com5 = new Company("LG", "lg@mail.com", "1234");
		Company com6 = new Company("LG", "lg@mail.com", "1234");

		// add companies to database
		service.addCompany(com1);
		service.addCompany(com2);
		service.addCompany(com3);
		service.addCompany(com4);
		service.addCompany(com5);

		// adding company that already exist -> exception
//		com6.setId(admin.addCompany(com6));
		System.out.println("All companies: " + service.getAllCompanies());

		// update company
		com1.setName("NewName");
		com1.setEmail("update@mail.com");
		com1.setPassword("pass1");
		facade.updateCompany(com1);
		System.out.println("All companies(update company 1): " + facade.getAllCompanies());

		// delete company
		facade.deleteCompany(com3.getId());
		System.out.println("All companies (delete company 3): " + facade.getAllCompanies());

		// get one company by its id
		System.out.println("One company: " + facade.getOneCompany(2));

		// ==============================================
		Customer cs1 = new Customer("Shay", "Mizrahi", "shay@test.com", "shay1234");
		Customer cs2 = new Customer("Eden", "Yefet", "eden@test.com", "eden1234");
		Customer cs3 = new Customer("Daniel", "Sorkin", "or@test.com", "or1234");
		Customer cs4 = new Customer("Eldad", "Gold", "eldad@test.com", "eldad1234");
		Customer cs5 = new Customer("Gal", "Halperin", "gal@test.com", "gal1234");
		Customer cs6 = new Customer("Gal", "Halperin", "gal@test.com", "gal1234");

		cs1.setId(facade.addCustomer(cs1));
		cs2.setId(facade.addCustomer(cs2));
		cs3.setId(facade.addCustomer(cs3));
		cs4.setId(facade.addCustomer(cs4));
		cs5.setId(facade.addCustomer(cs5));

		// adding customer that already exists -> exception
//		cs6.setId(facade.addCustomer(cs6));
		System.out.println("All customers: " + facade.getAllCustomers());

		// update customer
		cs3.setFirstName("Netanel");
		cs3.setLastName("Hai");
		cs3.setEmail("new@mail.com");
		cs3.setPassword("pass1234");
		facade.updateCustomer(cs3);
		System.out.println("All customers (update customer 3): " + facade.getAllCustomers());

		// delete customer
		facade.deleteCustomer(cs5.getId());
		System.out.println("All customers (delete customer 5): " + facade.getAllCustomers());

		// get one company by its id
		System.out.println("One customer: " + facade.getOneCustomer(4));

	}
}
