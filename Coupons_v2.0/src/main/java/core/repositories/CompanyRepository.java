package core.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import core.entities.Company;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Integer> {

	Optional<Company> findById(Integer id);

	List<Company> findAll();

}
