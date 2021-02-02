package core.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import core.entities.Customer;
import core.exceprtions.CouponSystemException;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

	Customer findByEmail(String email) throws CouponSystemException;

}
