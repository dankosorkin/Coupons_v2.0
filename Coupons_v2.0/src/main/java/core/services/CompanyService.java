package core.services;

import java.util.List;
import java.util.Optional;

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
	public boolean login(String email, String password) throws CouponSystemException {
		Company company = companyRepository.findByEmailAndPassword(email, password);
		if (company != null) {
			this.id = company.getId();
			return true;
		}
		throw new CouponSystemException("[x] OPERATION FAILED >>> failed to login");
	}

	public Coupon addCoupon(Coupon coupon) throws CouponSystemException {
		Coupon couponDB = couponRepository.findByTitle(coupon.getTitle());
		if (couponDB == null || coupon.getCompany_id() != this.id)
			return couponRepository.save(coupon);
		throw new CouponSystemException("[x] OPERATION FAILED >>> add coupon: already exists");
	}

	public boolean updateCoupon(Coupon coupon) throws CouponSystemException {
		Optional<Coupon> opt = couponRepository.findById(coupon.getId());
		if (opt.isPresent()) {
			Coupon couponDB = opt.get();
			couponDB.setCategory(coupon.getCategory());
			couponDB.setTitle(coupon.getTitle());
			couponDB.setDescription(coupon.getDescription());
			couponDB.setStartDate(coupon.getStartDate());
			couponDB.setEndDate(coupon.getEndDate());
			couponDB.setAmount(coupon.getAmount());
			couponDB.setPrice(coupon.getPrice());
			couponDB.setImage(coupon.getImage());
			return couponRepository.save(couponDB) != null;
		} else {
			throw new CouponSystemException("[x] OPERATION FAILED >>> update coupon: not found");
		}
	}

	public Coupon deleteCoupon(Integer id) throws CouponSystemException {
		Optional<Coupon> opt = couponRepository.findById(id);
		if (opt.isPresent()) {
			couponRepository.delete(opt.get());
			return opt.get();
		} else {
			throw new CouponSystemException("[X] OPERATION FAILED >>> delete coupon: not found");
		}
	}

	public List<Coupon> getAllCoupons() throws CouponSystemException {
		return couponRepository.findAll();
	}

	public List<Coupon> getAllByCategory(Category category) throws CouponSystemException {
		return couponRepository.findAllByCategory(category);
	}

	public List<Coupon> getAllByPrice(double maxPrice) throws CouponSystemException {
		return couponRepository.findAllByPrice(maxPrice);
	}

	public Company loggedInCompany() throws CouponSystemException {
		Optional<Company> opt = companyRepository.findById(this.id);
		if (opt.isPresent())
			return opt.get();
		else
			throw new CouponSystemException("[X] OPERATION FAILED");
	}

}
