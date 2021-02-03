package core.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import core.entities.Category;
import core.entities.Coupon;
import core.exceprtions.CouponSystemException;

public interface CouponRepository extends JpaRepository<Coupon, Integer> {

	Coupon findByTitle(String title) throws CouponSystemException;

	List<Coupon> findAllByCategory(Category category) throws CouponSystemException;

	List<Coupon> findAllByPrice(double price) throws CouponSystemException;

}
