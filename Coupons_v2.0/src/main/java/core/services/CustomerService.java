package core.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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

	@PersistenceContext
	private EntityManager em;

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

		// synchronize with database
		em.refresh(coupon);

		// check coupon quantity
		if (coupon.getAmount() < 1)
			throw new CouponSystemException("Selected coupon is out of stock");

		// check coupon date
		if (coupon.getEndDate().isBefore(LocalDate.now()))
			throw new CouponSystemException("Selceted coupon is expired");

		// check customer coupons purchases
		Optional<Customer> opt = customerRepository.findById(this.id);
		if (opt.isPresent()) {
			Customer customer = opt.get();

			List<Coupon> coupons = customer.getCoupons();
			em.refresh(coupons);

			for (Coupon current : coupons) {
				if (current.getId() == coupon.getId())
					throw new CouponSystemException("You allready both this coupon");
			}
			em.merge(coupon);
			customer.addCoupon(coupon);

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
