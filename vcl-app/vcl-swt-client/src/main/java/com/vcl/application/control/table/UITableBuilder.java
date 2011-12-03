package com.vcl.application.control.table;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.databinding.beans.BeanProperties;
import org.eclipse.core.databinding.beans.IBeanValueProperty;
import org.eclipse.core.databinding.observable.list.WritableList;
import org.eclipse.jface.databinding.viewers.ViewerSupport;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;

import com.vcl.application.model.table.TableDataModelBinding;
import com.vcl.application.model.table.TableMetaColumn;

public class UITableBuilder {
	
	private Table table;
	private Composite parent; 
	private List<TableViewerColumn> columns = new ArrayList<TableViewerColumn>();
	private TableViewer tableViewer;
	private TableDataModelBinding tModelBinding;
	private WritableList writableList;
	private IBeanValueProperty[] beanValueProperties; 
	
	public void setTableDataModelBinding(TableDataModelBinding tModelList) {
		this.tModelBinding = tModelList;
	}
	
	public void setParent(Composite parent) {
		this.parent = parent;
	}
	
	public TableViewer getTableViewer() {
		return tableViewer;
	}
	
	public Table build() {
		if (tModelBinding == null || parent == null) {
			throw new RuntimeException("TableModelList or parent composite are required");
		}
		
		return build(tModelBinding, parent);
	}
	
	public Table build(TableDataModelBinding tModelList, Composite parent) {
		this.tModelBinding = tModelList; 
		this.parent = parent;
		
		table = new Table(parent, SWT.BORDER | SWT.FULL_SELECTION);
		table.setLinesVisible(true);
		table.setHeaderVisible(true);
		
		tableViewer = new TableViewer(table);
		
		for (TableMetaColumn tmc : tModelList.getTableModelMetaData().getTableMetaColumns()) {
			TableViewerColumn column = createTableViewerColumn(tmc.getColName(), tmc.getWidthOrDefault());
			columns.add(column);
		}
		
		return table;
	}
	
	public void bind() {
		
		writableList = new WritableList(
				tModelBinding.getDataList(), 
				tModelBinding.getDataModelClass());

		beanValueProperties = BeanProperties.values(tModelBinding.getDataModelClass(), 
				tModelBinding.getTableModelMetaData().getDataListBindingProperties());
		
		ViewerSupport.bind(tableViewer, writableList, beanValueProperties);
		
		for(TableViewerColumn viewCol : columns) {
			if (viewCol.getColumn().getWidth() == 0) {
				viewCol.getColumn().pack();
			}
		}
	}
	
	private TableViewerColumn createTableViewerColumn(String title, int bound) {
		final TableViewerColumn viewerColumn = new TableViewerColumn(tableViewer,
				SWT.NONE);
		final TableColumn column = viewerColumn.getColumn();
		column.setText(title);
		column.setWidth(bound);
		column.setResizable(true);
		column.setMoveable(true);
		return viewerColumn;

	}
	
}
