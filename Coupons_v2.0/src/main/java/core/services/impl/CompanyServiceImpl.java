package core.services.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import core.services.CompanyService;

@Service
@Transactional
public class CompanyServiceImpl implements CompanyService {

	@Override
	public void login(String email, String password) {
		// TODO Auto-generated method stub

	}

}
