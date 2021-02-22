package core.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import core.entities.Category;
import core.entities.Company;
import core.entities.Coupon;
import core.exceptions.CouponSystemException;

/*
 * The class described business logic for company methods
 */
@Service
@Transactional
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
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
			throw new CouponSystemException("Failed to log you in as company");
	}

	/**
	 * The method adds coupon to logged in company. Method also should check
	 * expiration date; but for the learning purpose and test of the thread for
	 * expired coupons, date check is disabled.
	 * 
	 * @param Coupon coupon
	 * @return Coupon coupon
	 * @throws CouponSystemException
	 * 
	 */
	public Coupon addCoupon(Coupon coupon) throws CouponSystemException {

		if (!validateCouponByTitleAndCompanyId(coupon)) {
			throw new CouponSystemException("add coupon failed: already exists");
		}

//		if (!validateCouponEndDate(coupon)) {
//			throw new CouponSystemException("add coupon failed: expired coupon");
//		}

		Company company = loggedInCompany();
		company.addCoupon(coupon);

		return coupon;

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
		Coupon couponDB = findCoupon(coupon.getId());

		if (couponDB == null)
			throw new CouponSystemException("update coupon failed: not found");

		if (!validateCouponEndDate(coupon))
			throw new CouponSystemException("update coupon failed: expired coupon");

		if (!validateCouponByTitleAndCompanyId(coupon))
			throw new CouponSystemException("update coupon failed: already exists - title should be unique");

		if (coupon.getAmount() < 1)
			throw new CouponSystemException("update coupon failed: amount should be grater then 0");

		if (coupon.getPrice() < 0)
			throw new CouponSystemException("update coupon failed: price should be positive number");

		couponDB.setCategory(coupon.getCategory());
		couponDB.setTitle(coupon.getTitle());
		couponDB.setDescription(coupon.getDescription());
		couponDB.setStartDate(coupon.getStartDate());
		couponDB.setEndDate(coupon.getEndDate());
		couponDB.setAmount(coupon.getAmount());
		couponDB.setPrice(coupon.getPrice());
		couponDB.setImage(coupon.getImage());

		return couponRepository.save(couponDB) != null;
	}

	/**
	 * The method delete coupon of a company.
	 * 
	 * @param Integer id
	 * @return boolean
	 * @throws CouponSystemException
	 * 
	 */
	public boolean deleteCoupon(Integer id) throws CouponSystemException {
		if (findCoupon(id) == null)
			throw new CouponSystemException("delete coupon failed: coupon not found");

		if (findCoupon(id).getCompany().getId() != this.id)
			throw new CouponSystemException("delete coupon failed: coupon doesnt belong to the company");

		couponRepository.delete(findCoupon(id));
		return true;
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
		Coupon coupon = findCoupon(id);

		if (coupon == null)
			throw new CouponSystemException("get coupon failed: coupon not found");

		if (coupon.getCompany().getId() != this.id)
			throw new CouponSystemException("get coupon failed: coupon doesnt belong to the company");

		return coupon;
	}

	/**
	 * The method returns collection of all the coupons belonging to the company.
	 * 
	 * @return List<Coupon> coupons
	 * @throws CouponSystemException
	 * 
	 */
	public List<Coupon> getAllCoupons() throws CouponSystemException {

		List<Coupon> coupons = couponRepository.findAllByCompanyId(this.id);

		if (coupons == null)
			throw new CouponSystemException("The company have no coupons");

		return coupons;
	}

	/**
	 * The method returns collection of all the coupons from specific category
	 * belonging to the company.
	 * 
	 * @param Category category
	 * @return List<Coupon> coupons
	 * @throws CouponSystemException
	 * 
	 */
	public List<Coupon> getAllByCategory(Category category) throws CouponSystemException {

		List<Coupon> coupons = couponRepository.findAllByCategory(category);

		if (coupons == null)
			throw new CouponSystemException("The company have no coupons in selected category");

		return coupons;
	}

	/**
	 * The method gets all coupons belonging to a company by max price.
	 * 
	 * @param double maxPrice
	 * @return List<Coupon> coupons
	 * @throws CouponSystemException
	 * 
	 */
	public List<Coupon> getAllByPrice(double price) throws CouponSystemException {

		List<Coupon> coupons = couponRepository.findAllByCompanyAndPrice(this.id, price);

		if (coupons == null)
			throw new CouponSystemException("The company have no coupons in selected price range");

		return coupons;
	}

	private boolean validateCouponByTitleAndCompanyId(Coupon coupon) throws CouponSystemException {
		Coupon couponDB = couponRepository.findByTitle(coupon.getTitle());
		return (couponDB == null || couponDB.getCompany().getId() != this.id);
	}

	private boolean validateCouponEndDate(Coupon coupon) {
		return coupon.getEndDate().isAfter(LocalDate.now());
	}

	private Coupon findCoupon(Integer id) {
		Coupon coupon = null;

		Optional<Coupon> opt = couponRepository.findById(id);
		if (opt.isPresent())
			coupon = opt.get();
		return coupon;
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
		throw new CouponSystemException("not logged in");
	}

}
