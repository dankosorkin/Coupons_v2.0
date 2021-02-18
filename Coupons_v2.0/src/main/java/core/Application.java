package core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import core.test.AdminTest;
import core.test.CompanyTest;
import core.test.CustomerTest;
import core.util.DailyTask;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {

		try (ConfigurableApplicationContext ctx = SpringApplication.run(Application.class, args);) {

			DailyTask task = ctx.getBean(DailyTask.class);
//			task.start();

			AdminTest admin = ctx.getBean(AdminTest.class);
			admin.testAll();

			CompanyTest company = ctx.getBean(CompanyTest.class);
			company.testAll();

			CustomerTest customer = ctx.getBean(CustomerTest.class);
			customer.testAll();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
