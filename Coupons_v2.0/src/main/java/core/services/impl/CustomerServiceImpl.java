package core.services.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import core.services.CustomerService;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

	@Override
	public void login(String email, String password) {
		// TODO Auto-generated method stub

	}

}
