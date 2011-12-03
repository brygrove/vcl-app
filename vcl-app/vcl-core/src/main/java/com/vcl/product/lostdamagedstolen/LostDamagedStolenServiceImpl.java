package com.vcl.product.lostdamagedstolen;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class LostDamagedStolenServiceImpl implements LostDamagedStolenService {

	@Inject
	private LostDamagedStolenDao dao;

	@Override
	public LostDamagedStolen persist(LostDamagedStolen l) {
		return dao.persist(l);
	}

	@Override
	public LostDamagedStolen merge(LostDamagedStolen l) {
		return dao.merge(l);
	}

	@Override
	public void remove(LostDamagedStolen l) {
		dao.removeById(l.getIndexNo());
	}

	@Override
	public LostDamagedStolen findByIndexNo(String indexNo) {
		return dao.findById(indexNo);
	}

	@Override
	public List<LostDamagedStolen> findAllPaged(int firstResult, int maxResult) {
		return dao.findAllPaged(firstResult, maxResult);
	}

}
