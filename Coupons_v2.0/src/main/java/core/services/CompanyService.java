package core.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import core.entities.Category;
import core.entities.Company;
import core.entities.Coupon;
import core.exceptions.CouponSystemException;

/*
 * The class described business logic for company allowed methods
 */
@Service
@Transactional
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class CompanyService extends ClientService {

	private Integer id;

	/**
	 * Login method check credentials of a company in a database.
	 * 
	 * @param String email
	 * @param String password
	 * @return boolean
	 * @throws CouponSystemException
	 */
	@Override
	public boolean login(String email, String password) throws CouponSystemException {
		Company company = companyRepository.findByEmailAndPassword(email, password);
		if (company != null) {
			this.id = company.getId();
			return true;
		} else
			throw new CouponSystemException("[x] OPERATION FAILED >>> company not found");
	}

	/**
	 * The method adds coupon to logged in company. Method also should check
	 * expiration date; but for the learning purpose and test of the thread for
	 * expired coupons, date check is disabled.
	 * 
	 * && coupon.getEndDate().isAfter(LocalDate.now())
	 * 
	 * @param Coupon coupon
	 * @return Coupon coupon
	 * @throws CouponSystemException
	 * 
	 */
	public Coupon addCoupon(Coupon coupon) throws CouponSystemException {
		Coupon couponDB = couponRepository.findByTitle(coupon.getTitle());
		if ((couponDB == null || couponDB.getCompany().getId() != this.id)) {
			Optional<Company> opt = companyRepository.findById(this.id);
			if (opt.isPresent()) {
				Company company = opt.get();
				company.addCoupon(coupon);
			}
			return coupon;
		}
		throw new CouponSystemException("[x] OPERATION FAILED >>> add coupon: already exists");
	}

	/**
	 * The method updates coupon of a company. Method also should check expiration
	 * date; but for the learning purpose and test of the thread for expired
	 * coupons, date check is disabled.
	 * 
	 * && coupon.getEndDate().isAfter(LocalDate.now())
	 * 
	 * @param Coupon coupon
	 * @return boolean
	 * @throws CouponSystemException
	 * 
	 */
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

	/**
	 * The method delete coupon of a company.
	 * 
	 * @param Integer id
	 * @return Coupon coupon
	 * @throws CouponSystemException
	 * 
	 */
	public Coupon deleteCoupon(Integer id) throws CouponSystemException {
		Optional<Coupon> opt = couponRepository.findById(id);
		if (opt.isPresent()) {
			couponRepository.delete(opt.get());
			return opt.get();
		} else {
			throw new CouponSystemException("[X] OPERATION FAILED >>> delete coupon: not found");
		}
	}

	/**
	 * The method gets specific coupon of a company by its id.
	 * 
	 * @param Integer id
	 * @return Coupon coupon
	 * @throws CouponSystemException
	 * 
	 */
	public Coupon getOneCoupon(Integer id) throws CouponSystemException {
		Coupon coupon = couponRepository.getOne(id);
		if (coupon != null && coupon.getCompany().getId() == this.id)
			return coupon;
		throw new CouponSystemException("[X] OPERATION FAILED >>> get coupon: not found");
	}

	/**
	 * The method gets all coupons belonging to a company.
	 * 
	 * @return List<Coupon> coupons
	 * @throws CouponSystemException
	 * 
	 */
	public List<Coupon> getAllCoupons() throws CouponSystemException {
		Company company = loggedInCompany();
		List<Coupon> coupons = company.getCoupons();

		if (coupons == null)
			throw new CouponSystemException("Not found coupons belonging to the company");

		return coupons;
	}

	/**
	 * The method gets all coupons belonging to a company by specific category.
	 * 
	 * @param Category category
	 * @return List<Coupon> coupons
	 * @throws CouponSystemException
	 * 
	 */
	public List<Coupon> getAllByCategory(Category category) throws CouponSystemException {

		Company company = loggedInCompany();

		List<Coupon> companyCoupons = company.getCoupons();
		List<Coupon> categoryCoupons = new ArrayList<Coupon>();

		for (Coupon coupon : companyCoupons)
			if (coupon.getCategory().equals(category))
				categoryCoupons.add(coupon);

		return categoryCoupons;
	}

	/**
	 * The method gets all coupons belonging to a company by max price.
	 * 
	 * @param double maxPrice
	 * @return List<Coupon> coupons
	 * @throws CouponSystemException
	 * 
	 */
	public List<Coupon> getAllByPrice(double maxPrice) throws CouponSystemException {
		Company company = loggedInCompany();

		List<Coupon> companyCoupons = company.getCoupons();
		List<Coupon> priceCoupons = new ArrayList<Coupon>();

		for (Coupon coupon : companyCoupons)
			if (coupon.getPrice() <= maxPrice)
				priceCoupons.add(coupon);
		return priceCoupons;
	}

	/**
	 * The method returns logged in company.
	 * 
	 * @return Company company
	 * @throws CouponSystemException
	 * 
	 */
	public Company loggedInCompany() throws CouponSystemException {
		Optional<Company> opt = companyRepository.findById(id);
		if (opt.isPresent())
			return opt.get();
		throw new CouponSystemException("Try re-login");
	}

}
