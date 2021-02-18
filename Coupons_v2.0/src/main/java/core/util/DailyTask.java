package core.util;

import java.time.LocalDate;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import core.entities.Coupon;
import core.exceptions.CouponSystemException;
import core.repositories.CouponRepository;

@Component
public class DailyTask extends Thread {

	private boolean quit;
	private Thread appThread;

	@Autowired
	private CouponRepository repository;

	@PostConstruct
	public void post() {
		this.quit = false;
		this.appThread = new Thread();
		System.out.println(" <<<<<<<<<< Daily task started >>>>>>>>>>");
	}

	@Override
	public void run() {
		this.appThread = Thread.currentThread();
		try {
			while (!quit) {
				List<Coupon> coupons = repository.findByEndDateAfter(LocalDate.now());
				synchronized (coupons) {
					if (coupons != null) {
						for (Coupon coupon : coupons) {
							repository.deleteById(coupon.getId());
						}
					}
					wait(500);
				}
			}
		} catch (CouponSystemException | InterruptedException e) {
			e.printStackTrace();
		}
	}

	@PreDestroy
	public void pre() {
		this.quit = true;
		System.out.println(" <<<<<<<<<< Daily task ended >>>>>>>>>>");
	}

}
