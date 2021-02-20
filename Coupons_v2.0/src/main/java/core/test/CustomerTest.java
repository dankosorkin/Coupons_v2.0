package core.test;

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
		System.out.println("===== Customer test ======");
	}

	private void purchaseCoupon(Coupon coupon) throws CouponSystemException {
		service.purchaseCoupon(coupon);
		System.out.println("===== Customer purchases ======");
		System.out.println(coupon);
	}

	public void testAll() {
		try {
			login();
			purchaseCoupon(repository.getOne(1));
//			purchaseCoupon(repository.getOne(1));
		} catch (CouponSystemException e) {
			e.printStackTrace();
		}
	}

}
