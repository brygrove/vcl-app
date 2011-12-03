package com.vcl.product.category;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.vcl.product.ProductDao;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {

	@Inject
	private CategoryDao dao;

	@Inject
	private ProductDao prodDao;

	@Override
	public Category persist(Category c) {
		return dao.persist(c);
	}

	@Override
	public int renameCategory(String catNoFrom, String catNoTo) {

		Category catFrom = findByCatNo(catNoFrom);
		Assert.notNull(catFrom, "The category " + catNoFrom + " must exist!");

		Category catTo = findByCatNo(catNoTo);

		if (catTo == null) {
			catTo = dao.persistAndFlush(new Category(catNoTo));
		}

		int totalProductsEffected = prodDao.changeCategory(catFrom, catTo);

		dao.remove(catFrom);

		return totalProductsEffected;
	}

	@Override
	public void remove(Category c) {
		dao.removeById(c.getCatNo());
	}

	@Override
	public Category findByCatNo(String catNo) {
		return dao.findById(catNo);
	}

	@Override
	public List<String> findAllIds() {
		return dao.findAllIds();
	}

	@Override
	public List<String> findIds(int firstResult, int maxResult) {
		return dao.findIds(firstResult, maxResult);
	}

	@Override
	public List<Category> findAllPaged(int firstResult, int maxResult) {
		return dao.findAllPaged(firstResult, maxResult);
	}

}
