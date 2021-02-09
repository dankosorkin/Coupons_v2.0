package core.test;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import core.entities.Category;
import core.entities.Coupon;
import core.exceptions.CouponSystemException;
import core.login.ClientType;
import core.login.LoginManager;
import core.services.CompanyService;

@Component
public class CompanyTest {

	@Autowired
	private LoginManager manager;
	private CompanyService service;

	public CompanyTest() {
	}

	public void login(String email, String password) {
		try {
			service = (CompanyService) manager.login(email, password, ClientType.COMPANY);
		} catch (CouponSystemException e) {
			e.printStackTrace();
		}
	}

	public void aaCoupons() {
		try {
			Coupon c1 = service.addCoupon(new Coupon(service.loggedInCompany(), Category.VACATION, "Germany",
					"5 nights 6 days", LocalDate.of(2021, 02, 01), LocalDate.of(2021, 03, 28), 10, 9.9, null));
			Coupon c2 = service
					.addCoupon(new Coupon(service.loggedInCompany(), null, null, null, null, null, null, null, null));
			Coupon c3 = service
					.addCoupon(new Coupon(service.loggedInCompany(), null, null, null, null, null, null, null, null));
			Coupon c4 = service
					.addCoupon(new Coupon(service.loggedInCompany(), null, null, null, null, null, null, null, null));
			Coupon c5 = service
					.addCoupon(new Coupon(service.loggedInCompany(), null, null, null, null, null, null, null, null));

			System.out.println(c1);
			System.out.println(c2);
			System.out.println(c3);
			System.out.println(c4);
			System.out.println(c5);
		} catch (CouponSystemException e) {
			e.printStackTrace();
		}
	}

}
