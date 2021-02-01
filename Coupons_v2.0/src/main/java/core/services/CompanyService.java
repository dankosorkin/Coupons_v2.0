package core.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import core.entities.Category;
import core.entities.Company;
import core.entities.Coupon;
import core.exceprtions.CouponSystemException;

@Service
@Transactional
public class CompanyService extends ClientService {

	private Integer id;

	@Override
	public boolean login(String email, String password) {
		return false;
	}

	public void addCoupon(Coupon coupon) throws CouponSystemException {
	}

	public void updateCoupon(Coupon coupon) throws CouponSystemException {
	}

	public void deleteCoupon(Integer id) throws CouponSystemException {
	}

	public List<Coupon> getAllCoupons() throws CouponSystemException {
		return null;
	}

	public List<Coupon> getAllByCategory(Category category) throws CouponSystemException {
		return null;
	}

	public List<Coupon> getAllByPrice(double maxPrice) throws CouponSystemException {
		return null;
	}

	public Company loggedInCompany() throws CouponSystemException {
		return null;
	}

}
