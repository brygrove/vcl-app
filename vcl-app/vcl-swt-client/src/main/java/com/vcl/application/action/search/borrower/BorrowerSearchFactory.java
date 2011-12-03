package com.vcl.application.action.search.borrower;

import com.vcl.application.action.search.EntityDataSearchRequest;
import com.vcl.application.borrower.BorrowerModel;
import com.vcl.application.control.SearchModel;
import com.vcl.application.model.table.TableDataModelBinding;
import com.vcl.application.model.table.TableModelMetaData;

public class BorrowerSearchFactory {

	public static TableModelMetaData createTableMetaData() {
		
		TableModelMetaData metaData = new TableModelMetaData()
			.addTableMetaColumn("First Name", "firstName")
			.addTableMetaColumn("Last Name", "lastName", 100)
			.addTableMetaColumn("Phone Number", "phoneNo");
		
		return metaData;
	}
	
	public static EntityDataSearchRequest createForMetaDataAndSearchModel(TableModelMetaData metaData, SearchModel searchModel) {
		
		TableDataModelBinding modelBinding = new TableDataModelBinding(BorrowerModel.class)
				.setTableModelMetaData(metaData);
		
		return EntityDataSearchRequest.create(
				searchModel, 
				modelBinding, 
				new BorrowerDataSearchProvider());
	}

	public static TableDataModelBinding createTableModelBinding() {
		TableDataModelBinding modelBinding = new TableDataModelBinding(BorrowerModel.class)
			.setTableModelMetaData(createTableMetaData());
		
		return modelBinding;
	}
}
