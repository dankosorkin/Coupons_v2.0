package core.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import core.entities.Coupon;

@Repository
public interface CouponRepository extends JpaRepository<Coupon, Integer> {

	Optional<Coupon> findById(Integer id);

	List<Coupon> findAll();

}
