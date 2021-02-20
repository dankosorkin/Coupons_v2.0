package core.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import core.entities.Category;
import core.entities.Coupon;
import core.entities.Customer;
import core.exceptions.CouponSystemException;

@Service
@Transactional
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
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

	public boolean purchaseCoupon(Coupon coupon) throws CouponSystemException {

		Coupon couponToPurchase = null;

		Optional<Coupon> optCoupon = couponRepository.findById(coupon.getId());
		if (optCoupon.isPresent()) {
			couponToPurchase = optCoupon.get();
		}

		// check coupon quantity
		if (couponToPurchase == null)
			throw new CouponSystemException("Coupon not found");

		// check coupon quantity
		if (couponToPurchase.getAmount() < 1)
			throw new CouponSystemException("Selected coupon is out of stock");

		// check coupon date
		if (couponToPurchase.getEndDate().isBefore(LocalDate.now()))
			throw new CouponSystemException("Selected coupon is expired");

		// check customer coupons purchases
		Optional<Customer> opt = customerRepository.findById(this.id);
		if (opt.isPresent()) {
			Customer customer = opt.get();

			List<Coupon> coupons = customer.getCoupons();

			for (Coupon current : coupons) {
				if (current.getId() == couponToPurchase.getId())
					throw new CouponSystemException("You allready both this coupon");
			}
			// add to customer purchases
			customer.addCoupon(couponToPurchase);

			// decrease coupon amount
			coupon.setAmount(coupon.getAmount() - 1);

			return true;
		} else
			throw new CouponSystemException("Failed purchase coupon");
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
