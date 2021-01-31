package core.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import core.entities.Company;

public interface CompanyRepository extends JpaRepository<Company, Integer> {

}
