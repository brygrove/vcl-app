package com.vcl.application.action.search;

import com.vcl.application.model.dialog.EntityTableDialog;

public class DialogConfig {

	private int dialogWidth;
	private int dialogHeight;
	private int tableWidth;
	private int tableHeight;
	
	public static final DialogConfig createDefaultEntitySearchDlgCfg() {
		return new DialogConfig(
				EntityTableDialog.DLG_WIDTH, 
				EntityTableDialog.DLG_HEIGHT,
				EntityTableDialog.TABLE_WIDTH, 
				EntityTableDialog.TABLE_HEIGHT);
	}

	public DialogConfig(int dialogWidth, int dialogHeight, int tableWidth, int tableHeight) {
		this.dialogWidth = dialogWidth;
		this.dialogHeight = dialogHeight;
		this.tableWidth = tableWidth;
		this.tableHeight = tableHeight;
	}

	public int getTableWidth() {
		return tableWidth;
	}

	public void setTableWidth(int tableWidth) {
		this.tableWidth = tableWidth;
	}

	public int getTableHeight() {
		return tableHeight;
	}

	public void setTableHeight(int tableHeight) {
		this.tableHeight = tableHeight;
	}

	public int getDialogWidth() {
		return dialogWidth;
	}

	public void setDialogWidth(int dialogWidth) {
		this.dialogWidth = dialogWidth;
	}

	public int getDialogHeight() {
		return dialogHeight;
	}

	public void setDialogHeight(int dialogHeight) {
		this.dialogHeight = dialogHeight;
	}

}
