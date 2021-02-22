package core.test;

import java.time.LocalDate;
import java.util.List;

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

	public void login() throws CouponSystemException {
		service = (CompanyService) manager.login(email, password, ClientType.COMPANY);
		System.out.println("===== Company test ======");
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
				"2Tb capacity", LocalDate.of(2021, 02, 01), LocalDate.of(2021, 03, 17), 10, 9.9, null));
		Coupon c6 = service.addCoupon(new Coupon(service.loggedInCompany(), Category.ELECTRICITY, "MP3 player6",
				"2Tb capacity", LocalDate.of(2021, 02, 01), LocalDate.of(2021, 04, 17), 10, 9.9, null));
		Coupon c7 = service.addCoupon(new Coupon(service.loggedInCompany(), Category.ELECTRICITY, "MP3 player7",
				"2Tb capacity", LocalDate.of(2021, 02, 01), LocalDate.of(2021, 02, 17), 10, 9.9, null));
		Coupon c8 = service.addCoupon(new Coupon(service.loggedInCompany(), Category.ELECTRICITY, "MP3 player8",
				"2Tb capacity", LocalDate.of(2021, 02, 01), LocalDate.of(2021, 02, 17), 10, 9.9, null));
		Coupon c9 = service.addCoupon(new Coupon(service.loggedInCompany(), Category.ELECTRICITY, "MP3 player9",
				"2Tb capacity", LocalDate.of(2021, 02, 01), LocalDate.of(2021, 02, 17), 10, 9.9, null));
		Coupon c10 = service.addCoupon(new Coupon(service.loggedInCompany(), Category.ELECTRICITY, "MP3 player10",
				"2Tb capacity", LocalDate.of(2021, 02, 01), LocalDate.of(2021, 02, 17), 10, 9.9, null));

		System.out.println(c1);
		System.out.println(c2);
		System.out.println(c3);
		System.out.println(c4);
		System.out.println(c5);
		System.out.println(c6);
		System.out.println(c7);
		System.out.println(c8);
		System.out.println(c9);
		System.out.println(c10);

	}

	public void updateCoupon() throws CouponSystemException {
		Coupon coupon = service.getOneCoupon(2);

		coupon.setCategory(Category.RESTAURANT);
		coupon.setTitle("AAAAAAAAA");
		coupon.setDescription("*** Description ***");
		coupon.setStartDate(LocalDate.of(2021, 02, 15));
		coupon.setEndDate(LocalDate.of(2021, 03, 10));
		coupon.setAmount(5);
		coupon.setPrice(2.99);
		service.updateCoupon(coupon);

		System.out.println("Updated coupon: " + coupon);
	}

	public void deleteCoupon(Integer id) throws CouponSystemException {
		Coupon coupon = service.getOneCoupon(2);
		if (coupon != null) {
			System.out.println("Delete coupon: " + coupon);
			service.deleteCoupon(id);
		}
	}

	public void getAllCoupons() throws CouponSystemException {
		List<Coupon> coupons = service.getAllCoupons();

		if (coupons != null) {
			System.out.println(">>> All company coupons");
			for (Coupon coupon : coupons) {
				System.out.println(coupon);
			}
		}
	}

	public void getAllByCategory(Category category) throws CouponSystemException {
		List<Coupon> coupons = service.getAllByCategory(category);

		System.out.println(">>> All company coupons by category " + category);
		for (Coupon coupon : coupons) {
			System.out.println(coupon);
		}
	}

	public void getAllByPrice(double price) throws CouponSystemException {
		List<Coupon> coupons = service.getAllByPrice(price);

		System.out.println(">>> All company coupons by max price");
		for (Coupon coupon : coupons) {
			System.out.println(coupon);
		}
	}

	public void testAll() {
		try {
			login();
			addCoupons();
			updateCoupon();
			deleteCoupon(2);
			getAllCoupons();
			getAllByCategory(Category.ELECTRICITY);
			getAllByPrice(8.0);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
