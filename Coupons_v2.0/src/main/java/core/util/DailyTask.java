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
//	private Thread appThread;

	@Autowired
	private CouponRepository repository;

	@PostConstruct
	public void post() {
		this.start();
	}

	@Override
	public void run() {
//		this.appThread = Thread.currentThread();
		while (!quit) {
			try {
				Thread.sleep(3000);
				List<Coupon> coupons = repository.findByEndDateBefore(LocalDate.now());
				for (Coupon coupon : coupons) {
					System.out
							.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>" + coupon);
					repository.deleteById(coupon.getId());
				}
			} catch (CouponSystemException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				break;
			}
			System.out.println("***********************");
		}
	}

	@PreDestroy
	public void pre() {
		this.quit = true;
		this.interrupt();
		System.out.println(" <<<<<<<<<< Daily task ended >>>>>>>>>>");
	}

}
