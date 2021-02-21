package core.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import core.entities.Category;
import core.entities.Coupon;
import core.exceptions.CouponSystemException;

public interface CouponRepository extends JpaRepository<Coupon, Integer> {

	Coupon findByTitle(String title) throws CouponSystemException;

	List<Coupon> findByEndDateBefore(LocalDate date) throws CouponSystemException;

	@Query("select c from Coupon c where c.company=id")
	List<Coupon> findAllByComapnyId(Integer id) throws CouponSystemException;

	@Query("select c from Coupon c where c.category=category")
	List<Coupon> findAllByCategory(Category category) throws CouponSystemException;

}
