package core.util;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import core.repositories.CouponRepository;

@Component
public class PopulateDB extends Thread {

	private boolean quit;

	@Autowired
	CouponRepository couponRepo;

	@PostConstruct
	public void startTask() {

	}

	@Override
	public void run() {

	}

	@PreDestroy
	public void stopTask() {

	}

}
