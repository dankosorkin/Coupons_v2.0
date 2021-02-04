package core.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import core.entities.Category;
import core.entities.Coupon;
import core.entities.Customer;
import core.exceprtions.CouponSystemException;

@Service
@Transactional
public class CustomerService extends ClientService {

	private Integer id;

	@Override
	public boolean login(String email, String password) throws CouponSystemException {
		Customer customer = customerRepository.findByEmailAndPassword(email, password);
		if (customer != null) {
			this.id = customer.getId();
			return true;
		}
		throw new CouponSystemException("[x] OPERATION FAILED >>> failed to login");
	}

	public void purchaseCoupon(Coupon coupon) throws CouponSystemException {

	}

	public List<Coupon> getAllCoupons() throws CouponSystemException {
		return null;
	}

	public List<Coupon> gettAllByCategory(Category category) throws CouponSystemException {
		return null;
	}

	public List<Coupon> getAllByPrice(double maxPrice) throws CouponSystemException {
		return null;
	}

	public Customer loggedInCustomer() throws CouponSystemException {
		return null;
	}

}
