package core.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import core.entities.Company;
import core.exceprtions.CouponSystemException;

public interface CompanyRepository extends JpaRepository<Company, Integer> {

	Company findByEmailAndPassword(String email, String password);

	Company findByNameAndEmail(String name, String email) throws CouponSystemException;

}
