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

	List<Coupon> findAllByEndDateBefore(LocalDate date) throws CouponSystemException;

	@Query(value = "select distinct c from Coupon c where c.company.id = :id")
	List<Coupon> findAllByCompanyId(@Param("id") Integer id) throws CouponSystemException;

	@Query(value = "select distinct c from Coupon c where c.category = :category")
	List<Coupon> findAllByCompanyAndCategory(@Param("category") Category category) throws CouponSystemException;

	@Query(value = "select distinct c from Coupon c where c.company.id = :id and c.price <= :price")
	List<Coupon> findAllByCompanyAndPrice(@Param("id") Integer id, @Param("price") double price)
			throws CouponSystemException;

	@Query(value = "select distinct c from Coupon c inner join c.customers cs where cs.id = :id")
	List<Coupon> findAllByCustomerId(@Param("id") Integer id) throws CouponSystemException;

	@Query(value = "select distinct c from Coupon c inner join c.customers cs where cs.id = :id and c.category = :category")
	List<Coupon> findAllByCustomerAndCategory(@Param("id") Integer id, @Param("category") Category category)
			throws CouponSystemException;

	@Query(value = "select distinct c from Coupon c inner join c.customers cs where cs.id = :id and c.price <= :price")
	List<Coupon> findAllByCustomerAndPrice(@Param("id") Integer id, @Param("price") double price)
			throws CouponSystemException;

}
