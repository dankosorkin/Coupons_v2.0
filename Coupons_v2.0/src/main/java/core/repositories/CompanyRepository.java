package core.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import core.entities.Company;
import core.exceprtions.CouponSystemException;

public interface CompanyRepository extends JpaRepository<Company, Integer> {

	Company findByName(String name) throws CouponSystemException;

	Company findByEmail(String email) throws CouponSystemException;

}
