package core.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import core.entities.Coupon;

public interface CouponRepository extends JpaRepository<Coupon, Integer> {

}
