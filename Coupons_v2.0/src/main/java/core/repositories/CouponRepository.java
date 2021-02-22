package core.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import core.entities.Category;
import core.entities.Coupon;
import core.exceptions.CouponSystemException;

public interface CouponRepository extends JpaRepository<Coupon, Integer> {

	Coupon findByTitle(String title) throws CouponSystemException;

	List<Coupon> findByEndDateBefore(LocalDate date) throws CouponSystemException;

	@Query(value = "select c from Coupon c where c.company.id = :id")
	List<Coupon> findByCompanyId(@Param("id") Integer id) throws CouponSystemException;

	@Query(value = "select c from Coupon c where c.category = :category")
	List<Coupon> findByCategory(@Param("category") Category category) throws CouponSystemException;

	@Query(value = "select c from Coupon c where c.company.id = :id and c.price <= :price")
	List<Coupon> findAllByCompanyAndPrice(@Param("id") Integer id, @Param("price") double price)
			throws CouponSystemException;

}
