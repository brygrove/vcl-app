package com.vcl.application.action.search.borrower;

import java.util.ArrayList;
import java.util.List;

import com.vcl.application.action.search.EntityDataSearchProvider;
import com.vcl.application.action.search.EntityDataSearchRequest;
import com.vcl.application.borrower.BorrowerModel;
import com.vcl.application.control.SearchModel;
import com.vcl.borrower.Borrower;
import com.vcl.borrower.BorrowerSearchArg;
import com.vcl.borrower.BorrowerService;
import com.vcl.client.VclClient;

public class BorrowerDataSearchProvider implements EntityDataSearchProvider<BorrowerModel> {

	private BorrowerService borrowerService = VclClient.getServiceLocator().getBorrowerService();
	
	@Override
	public List<BorrowerModel> searchEntity(EntityDataSearchRequest request) {
		
		SearchModel searchModel = request.getSearchModel();
		String keyword = searchModel.getKeywordText();
		String keywordSearchStr = keyword;
		if (keywordSearchStr == null) {
			keywordSearchStr = "%";
		}
		else {
			keywordSearchStr = "%" + keywordSearchStr + "%";
		}
		
		BorrowerSearchArg search = new BorrowerSearchArg();
		search.setKeyword(keywordSearchStr);
		search.setFirstResult(searchModel.getFirstResult());
		search.setMaxResult(searchModel.getMaxResults());
		
		return createModelList(borrowerService.searchBorrower(search));
	}
	
	private List<BorrowerModel> createModelList(List<Borrower> borrowers) {
		List<BorrowerModel> borrowerModels = new ArrayList<BorrowerModel>();
		
		for (Borrower b : borrowers) {
			borrowerModels.add(new BorrowerModel(b));
		}
		
		return borrowerModels;
	}

}
