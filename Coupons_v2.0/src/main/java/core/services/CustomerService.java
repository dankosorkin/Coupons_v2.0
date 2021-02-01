package core.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CustomerService extends ClientService {

	@Override
	public void login(String email, String password) {
		// TODO Auto-generated method stub

	}

}
