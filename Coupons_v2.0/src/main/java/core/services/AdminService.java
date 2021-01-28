package core.services;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import core.entities.Company;
import core.exceprtions.CouponSystemException;

@Service
@Transactional
public class AdminService extends ClientService {

	@Override
	protected boolean login(String email, String password) throws CouponSystemException {
		// TODO Auto-generated method stub
		return false;
	}

	public void addCompany(Company company) {
		if (company != null) {

		}

	}

}
