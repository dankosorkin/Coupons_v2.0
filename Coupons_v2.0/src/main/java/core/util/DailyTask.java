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
	public void post() {
		this.start();
		System.out.println(" <<<<<<<<<< Daily task start >>>>>>>>>>");
	}

	@Override
	public void run() {
		while (!quit) {
			try {
				Thread.sleep(3000);
				List<Coupon> coupons = repository.findByEndDateBefore(LocalDate.now());
				for (Coupon coupon : coupons) {
					repository.deleteById(coupon.getId());
				}
			} catch (CouponSystemException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				break;
			}
		}
	}

	@PreDestroy
	public void pre() {
		this.quit = true;
		this.interrupt();
		System.out.println(" <<<<<<<<<< Daily task ended >>>>>>>>>>");
	}

}
