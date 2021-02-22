package core.test;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import core.entities.Coupon;
import core.exceptions.CouponSystemException;
import core.login.ClientType;
import core.login.LoginManager;
import core.repositories.CouponRepository;
import core.services.CustomerService;

@Component
public class CustomerTest {

	private static final String email = "or@mail";
	private static final String password = "or1234";

	@Autowired
	private LoginManager manager;

	@Autowired
	private CouponRepository repository;

	private CustomerService service;

	public void login() throws CouponSystemException {
		service = (CustomerService) manager.login(email, password, ClientType.CUSTOMER);
		System.out.println("\n===== Customer test ======");
	}

	public void purchaseCoupon(Coupon coupon) throws CouponSystemException {
		service.purchaseCoupon(coupon);
		System.out.println("===== Customer purchases ======");
		System.out.println(coupon);
	}

	public void getAllCoupons() throws CouponSystemException {
		List<Coupon> coupons = service.getAllCoupons();

		if (coupons != null) {
			System.out.println(">>> All customer coupons");
			for (Coupon coupon : coupons) {
				System.out.println(coupon);
			}
		}
	}

	public void testAll() {
		try {
			login();
			Optional<Coupon> opt = repository.findById(1);
			Coupon coupon = null;
			if (opt.isPresent()) {

				coupon = opt.get();
			}
			purchaseCoupon(coupon);
			// add expired coupon to check thread job
			opt = repository.findById(10);
			coupon = null;
			if (opt.isPresent()) {

				coupon = opt.get();
			}
			purchaseCoupon(coupon);
//			purchaseCoupon(repository.getOne(1));
			getAllCoupons();
		} catch (CouponSystemException e) {
			e.printStackTrace();
		}
	}

}
