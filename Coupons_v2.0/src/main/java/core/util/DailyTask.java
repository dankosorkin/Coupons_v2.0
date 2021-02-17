package core.util;

import java.time.LocalDate;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import core.entities.Coupon;
import core.repositories.CouponRepository;

@Component
public class DailyTask extends Thread {

	@Autowired
	private CouponRepository repository;

	@PostConstruct
	public void post() {

	}

	@Override
	public void run() {
		try {
			while (true) {
				List<Coupon> coupons = repository.findAll();
				for (Coupon coupon : coupons)
					if (coupon.getEndDate().isAfter(LocalDate.now()))
						repository.delete(coupon);
				sleep(24 * 60 * 60 * 1000);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@PreDestroy
	public void pre() {

	}

}
