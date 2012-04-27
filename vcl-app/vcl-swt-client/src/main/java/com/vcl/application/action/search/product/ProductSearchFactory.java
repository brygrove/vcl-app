package com.vcl.application.action.search.product;

import com.vcl.application.action.search.EntityDataSearchRequest;
import com.vcl.application.borrower.BorrowerModel;
import com.vcl.application.control.SearchModel;
import com.vcl.application.model.table.TableDataModelBinding;
import com.vcl.application.model.table.TableModelMetaData;

public class ProductSearchFactory {

	public static TableModelMetaData createTableMetaData() {
		
		TableModelMetaData metaData = new TableModelMetaData()
			.addTableMetaColumn("Index No", "indexNo")
			.addTableMetaColumn("Title", "indexNo")
			.addTableMetaColumn("Sub Title", "indexNo")
			.addTableMetaColumn("Author", "author", 100)
			.addTableMetaColumn("Category", "category.catNo")
			.addTableMetaColumn("", "phoneNo");
		
		return metaData;
	}
	
/*	private String indexNo;
	private String author = "";
	private Category category;
	@Column(name="prod_condition")
	private String condition;
	private String cost = "";
    @Temporal( TemporalType.DATE)
	private Date datePurchased;
	private String donation = "";
	private String fiction = "";
	@Column(name="ISBN")
	private String isbn;
	private String itemType = "";
	private String noPages = "";
    @Lob()
	private String notes = "";
	private String publisher;
	private String series = "";
	private String subTitle = "";
	private String title = "";
	private String type = "";*/
	
	
	public static EntityDataSearchRequest createForMetaDataAndSearchModel(TableModelMetaData metaData, SearchModel searchModel) {
		
		TableDataModelBinding modelBinding = new TableDataModelBinding(BorrowerModel.class)
				.setTableModelMetaData(metaData);
		
		return EntityDataSearchRequest.create(
				searchModel, 
				modelBinding, 
				new ProductDataSearchProvider());
	}

	public static TableDataModelBinding createTableModelBinding() {
		TableDataModelBinding modelBinding = new TableDataModelBinding(BorrowerModel.class)
			.setTableModelMetaData(createTableMetaData());
		
		return modelBinding;
	}
}
