package com.vcl.product.lostdamagedstolen;

import java.util.List;

public interface LostDamagedStolenService {

	LostDamagedStolen persist(LostDamagedStolen l);

	LostDamagedStolen merge(LostDamagedStolen l);

	void remove(LostDamagedStolen l);

	LostDamagedStolen findByIndexNo(String indexNo);

	List<LostDamagedStolen> findAllPaged(int firstResult, int maxResult);

}