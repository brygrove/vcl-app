package com.vcl.application.action.search.category;

import com.vcl.application.action.search.EntityDataSearchRequest;
import com.vcl.application.category.CategoryModel;
import com.vcl.application.control.SearchModel;
import com.vcl.application.model.table.TableDataModelBinding;
import com.vcl.application.model.table.TableModelMetaData;

public class CategorySearchFactory {

	public static TableModelMetaData createTableMetaData() {
		
		TableModelMetaData metaData = new TableModelMetaData()
			.addTableMetaColumn("Category", "catNo");
		
		return metaData;
	}
	
	public static EntityDataSearchRequest createForMetaDataAndSearchModel(TableModelMetaData metaData, SearchModel searchModel) {
		
		TableDataModelBinding modelBinding = new TableDataModelBinding(CategoryModel.class)
				.setTableModelMetaData(metaData);
		
		return EntityDataSearchRequest.create(
				searchModel, 
				modelBinding, 
				new CategoryDataSearchProvider());
	}

	public static TableDataModelBinding createTableModelBinding() {
		TableDataModelBinding modelBinding = new TableDataModelBinding(CategoryModel.class)
			.setTableModelMetaData(createTableMetaData());
		
		return modelBinding;
	}
}
