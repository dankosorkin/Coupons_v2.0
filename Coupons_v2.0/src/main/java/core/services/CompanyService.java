package core.services;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import core.entities.Category;
import core.entities.Company;
import core.entities.Coupon;
import core.exceptions.CouponSystemException;

@Service
@Transactional
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class CompanyService extends ClientService {

	private Integer id;

	@PersistenceContext
	private EntityManager em;

	@Override
	public boolean login(String email, String password) throws CouponSystemException {
		try {
			Company company = companyRepository.findByEmailAndPassword(email, password);
			if (company != null)
				this.id = company.getId();
			return true;
		} catch (Exception e) {
			throw new CouponSystemException("[x] OPERATION FAILED >>> company not found");
		}
	}

	public Coupon addCoupon(Coupon coupon) throws CouponSystemException {
		Coupon couponDB = couponRepository.findByTitle(coupon.getTitle());
		if (couponDB == null || couponDB.getCompany().getId() != this.id) {
			Optional<Company> opt = companyRepository.findById(this.id);
			if (opt.isPresent()) {
				Company company = opt.get();
//				em.refresh(company);
				company.addCoupon(coupon);
			}
			return coupon;
		}
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

	public Coupon getOneCoupon(Integer id) throws CouponSystemException {
		Coupon coupon = couponRepository.getOne(id);
		if (coupon != null && coupon.getCompany().getId() == this.id)
			return coupon;
		throw new CouponSystemException("[X] OPERATION FAILED >>> get coupon: not found");
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
		Optional<Company> opt = companyRepository.findById(id);
		if (opt.isPresent())
			return opt.get();
		return null;
	}

}
