package com.vcl.product.lostdamagedstolen;

import javax.inject.Inject;

import org.springframework.stereotype.Component;

@Component
public class LostDamagedStolenServiceSteps {

	@Inject private LostDamagedStolenService ldsService;
	
	public LostDamagedStolen createLostDamagedStolen(String indexNo, String description) {
		LostDamagedStolen l = new LostDamagedStolen();
		l.setIndexNo(indexNo);
		l.setDescription(description);
		return ldsService.persist(l);
	}
	
	public void remove(LostDamagedStolen l) {
		ldsService.remove(l);
	}
}
