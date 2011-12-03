package com.vcl.util;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;

import com.vcl.application.VclManagerApplication;

public class MessageBoxUtil {
	
	private static Shell getApplicationShell() {
		return VclManagerApplication.getInstance().getWindowHandle();
	}
	
	public static void showError(String msg) {
		showMessageBox(msg, SWT.ICON_ERROR);
	}
	
	public static void showWarning(String msg) {
		showMessageBox(msg, SWT.ICON_WARNING);
	}
	
	public static void showInformation(String msg) {
		showMessageBox(msg, SWT.ICON_INFORMATION);
	}
	
	public static void showQuestion(String msg) {
		showMessageBox(msg, SWT.ICON_QUESTION);
	}
	
	public static int showMessageBox(String msg, int style) {
		MessageBox msgBox = new MessageBox(getApplicationShell(), style);
		msgBox.setMessage(msg);
		return msgBox.open();
	}
	
}
