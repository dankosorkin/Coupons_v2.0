package core.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import core.entities.Category;
import core.entities.Coupon;
import core.exceptions.CouponSystemException;

public interface CouponRepository extends JpaRepository<Coupon, Integer> {

	Coupon findByTitle(String title) throws CouponSystemException;

	@Query(value = "select * from coupon where category=:category", nativeQuery = true)
	List<Coupon> findAllByCategory(@Param("category") Category category) throws CouponSystemException;

	List<Coupon> findAllByPrice(double price) throws CouponSystemException;

}
