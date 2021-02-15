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

	private static final String email = "apple@mail";
	private static final String password = "pass1234";

	@Autowired
	private LoginManager manager;

	private CompanyService service;

	public CompanyTest() {
	}

	public void login() throws CouponSystemException {
		service = (CompanyService) manager.login(email, password, ClientType.COMPANY);
	}

	public void addCoupons() throws CouponSystemException {
		Coupon c1 = service.addCoupon(new Coupon(service.loggedInCompany(), Category.ELECTRICITY, "MP3 player1",
				"128Gb capacity", LocalDate.of(2021, 02, 01), LocalDate.of(2021, 03, 28), 10, 5.9, null));
		Coupon c2 = service.addCoupon(new Coupon(service.loggedInCompany(), Category.ELECTRICITY, "MP3 player2",
				"256Gb capacity", LocalDate.of(2021, 02, 01), LocalDate.of(2021, 03, 28), 10, 6.9, null));
		Coupon c3 = service.addCoupon(new Coupon(service.loggedInCompany(), Category.ELECTRICITY, "MP3 player3",
				"512Gb capacity", LocalDate.of(2021, 02, 01), LocalDate.of(2021, 03, 28), 10, 7.9, null));
		Coupon c4 = service.addCoupon(new Coupon(service.loggedInCompany(), Category.ELECTRICITY, "MP3 player4",
				"1Tb capacity", LocalDate.of(2021, 02, 01), LocalDate.of(2021, 03, 28), 10, 8.9, null));
		Coupon c5 = service.addCoupon(new Coupon(service.loggedInCompany(), Category.ELECTRICITY, "MP3 player5",
				"2Tb capacity", LocalDate.of(2021, 02, 01), LocalDate.of(2021, 03, 28), 10, 9.9, null));

		System.out.println(c1);
		System.out.println(c2);
		System.out.println(c3);
		System.out.println(c4);
		System.out.println(c5);

	}

	public void updateCoupon() throws CouponSystemException {
		Coupon coupon = service.getOneCoupon(2);

		// TODO update coupon fields

		System.out.println("Updated coupon: " + coupon);
	}

	public void testAll() {
		try {
			login();
			addCoupons();
			updateCoupon();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
