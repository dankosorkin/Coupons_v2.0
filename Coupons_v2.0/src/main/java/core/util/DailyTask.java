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

	@Autowired
	private CouponRepository repository;

	@PostConstruct
	public void startTask() {
		this.start();
		System.out.println(" <<<<<<<<<< Daily task start >>>>>>>>>>");
	}

	@Override
	public void run() {
		while (!quit) {
			try {
				Thread.sleep(5000);
				List<Coupon> coupons = repository.findByEndDateBefore(LocalDate.now());
				for (Coupon coupon : coupons) {
					repository.delete(coupon);
				}
			} catch (CouponSystemException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				break;
			}
		}
	}

	@PreDestroy
	public void stopTask() {
		this.quit = true;
		this.interrupt();
		System.out.println(" <<<<<<<<<< Daily task ended >>>>>>>>>>");
	}

}
