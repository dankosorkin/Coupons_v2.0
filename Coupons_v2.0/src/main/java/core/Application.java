package core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import core.test.AdminTest;
import core.test.CompanyTest;
import core.test.CustomerTest;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {

		try (ConfigurableApplicationContext ctx = SpringApplication.run(Application.class, args);) {

			AdminTest admin = ctx.getBean(AdminTest.class);
			admin.testAll();

			CompanyTest company = ctx.getBean(CompanyTest.class);
			company.testAll();

			CustomerTest customer = ctx.getBean(CustomerTest.class);
			customer.testAll();

//			company.getAllCoupons();

			Thread.sleep(10000);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
