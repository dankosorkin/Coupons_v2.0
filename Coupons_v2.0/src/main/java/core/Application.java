package core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import core.test.AdminTest;
import core.test.CompanyTest;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {

		try (ConfigurableApplicationContext ctx = SpringApplication.run(Application.class, args);) {

			AdminTest admin = ctx.getBean(AdminTest.class);
			admin.testAll();

			CompanyTest company = ctx.getBean(CompanyTest.class);
			company.testAll();

//			admin.deleteCompany(1);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
