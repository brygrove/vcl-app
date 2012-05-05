package com.vcl.application;

import java.util.Properties;

import org.eclipse.core.databinding.observable.Realm;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.ExpandBar;
import org.eclipse.swt.widgets.ExpandItem;
import org.eclipse.swt.widgets.Link;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.part.ViewPart;

import com.vcl.application.events.ApplicationEvents;
import com.vcl.application.events.EventName;
import com.vcl.application.events.GlobalApplicationEvents;


public class VclManagerApplication implements ApplicationResources {

	protected Shell shell;
	protected Display display = Display.getDefault();
	
	protected GlobalApplicationEvents globalAppEvents; 
	protected Properties applicationSettings; 
	
	private static VclManagerApplication instance; 
	
	public static VclManagerApplication getInstance() {
		if (instance == null) {
			instance = new VclManagerApplication();
		}
		return instance; 
	}

	@Override
	public Shell getWindowHandle() {
		return shell; 
	}

	@Override
	public Properties getApplicationSettings() {
		return applicationSettings;
	}
	
	@Override
	public ApplicationEvents<EventName> getGlobalApplicationEvents() {
		return globalAppEvents;
	}

	private void createApp() {
		createContents();
		createActions();
		shell.open();
		shell.layout();
	}
	
	/**
	 * Open the window.
	 */
	public void open() {
		
		Realm.runWithDefault(SWTObservables.getRealm(display), new Runnable() {
			public void run() {
				createApp(); 
				while (!shell.isDisposed()) {
					if (!display.readAndDispatch()) {
						display.sleep();
					}
				}
				display.dispose();
			}
		});
		
	}

	private void createActions() {
		globalAppEvents = new GlobalApplicationEvents(this);
		
		this.mntmExit.addSelectionListener(globalAppEvents.getExitAppEvent());
		this.linkViewBorrowerMgr.addSelectionListener(globalAppEvents.getShowEditBorrowerView());
		this.linkViewCategoryMgr.addSelectionListener(globalAppEvents.getShowEditCategoryView());
		this.linkViewProductMgr.addSelectionListener(globalAppEvents.getShowEditProductView());
	}

	private Menu menu; 
	private MenuItem mntmFile; 
	private Menu menu_2;
	private MenuItem mntmExit;
	private SashForm sashForm;
	private ScrolledComposite navigationComposite; 
	private ScrolledComposite workspaceComposite; 
	private ExpandBar navExbandBar;
	private ExpandItem xpndtmManagers;
	private ExpandItem xpndtmReports;
	private ExpandItem xpndtmSearch;
	private Composite composite;
	private Composite composite_1;
	private Composite composite_2;
	private Link linkViewProductMgr;
	private Link linkViewCategoryMgr;
	private Link linkViewUserMgr;
	private Link linkViewBorrowerMgr;
	private Link linkViewCheckInOutMgr;
	private Link linkProductSearch;
	private Link linkBorrowerSearch;
	private final FormToolkit formToolkit = new FormToolkit(Display.getDefault());
	private CTabFolder tabFolder;
	
	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		this.shell = new Shell();
		this.shell.setSize(800, 600);
		this.shell.setText("SWT Application");
		this.shell.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		this.sashForm = new SashForm(shell,SWT.HORIZONTAL);
		this.sashForm.setLayout(new FillLayout());
		
		this.navigationComposite = new ScrolledComposite(this.sashForm, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
		this.navigationComposite.setExpandHorizontal(true);
		this.navigationComposite.setExpandVertical(true);
		this.navigationComposite.setLayout(new FillLayout());
		
		this.navExbandBar = new ExpandBar(this.navigationComposite, SWT.NONE);
		this.navExbandBar.setSpacing(8);
		this.navExbandBar.setToolTipText("navigation bar");
		
		this.xpndtmManagers = new ExpandItem(this.navExbandBar, SWT.NONE);
		this.xpndtmManagers.setExpanded(true);
		this.xpndtmManagers.setText("Managers");
		
		this.composite = new Composite(this.navExbandBar, SWT.NONE);
		this.xpndtmManagers.setControl(this.composite);
		
		this.linkViewProductMgr = new Link(this.composite, SWT.NONE);
		this.linkViewProductMgr.setBounds(20, 10, 153, 13);
		this.linkViewProductMgr.setText("<a>Product</a>");
		
		this.linkViewCategoryMgr = new Link(this.composite, 0);
		this.linkViewCategoryMgr.setText("<a>Category</a>");
		this.linkViewCategoryMgr.setBounds(20, 29, 153, 13);
		
		this.linkViewUserMgr = new Link(this.composite, 0);
		this.linkViewUserMgr.setText("<a>User</a>");
		this.linkViewUserMgr.setBounds(20, 86, 153, 13);
		
		this.linkViewBorrowerMgr = new Link(this.composite, 0);
		this.linkViewBorrowerMgr.setText("<a>Borrower</a>");
		this.linkViewBorrowerMgr.setBounds(20, 48, 153, 13);
		
		this.linkViewCheckInOutMgr = new Link(this.composite, 0);
		this.linkViewCheckInOutMgr.setText("<a>Check In / Out</a>");
		this.linkViewCheckInOutMgr.setBounds(20, 67, 153, 13);
		this.xpndtmManagers.setHeight(160);
		
		this.xpndtmSearch = new ExpandItem(this.navExbandBar, SWT.NONE);
		this.xpndtmSearch.setExpanded(true);
		this.xpndtmSearch.setText("Search");
		
		this.composite_1 = new Composite(this.navExbandBar, SWT.NONE);
		this.xpndtmSearch.setControl(this.composite_1);
		
		this.linkProductSearch = new Link(this.composite_1, SWT.NONE);
		this.linkProductSearch.setBounds(20, 10, 128, 13);
		this.linkProductSearch.setText("<a>Product Search</a>");
		
		this.linkBorrowerSearch = new Link(this.composite_1, 0);
		this.linkBorrowerSearch.setText("<a>Borrower Search</a>");
		this.linkBorrowerSearch.setBounds(20, 29, 128, 13);
		this.xpndtmSearch.setHeight(150);
		
		this.xpndtmReports = new ExpandItem(this.navExbandBar, SWT.NONE);
		this.xpndtmReports.setExpanded(true);
		this.xpndtmReports.setText("Reports");
		
		this.composite_2 = new Composite(this.navExbandBar, SWT.NONE);
		this.xpndtmReports.setControl(this.composite_2);
		this.xpndtmReports.setHeight(70);
		this.navigationComposite.setContent(this.navExbandBar);
		this.navigationComposite.setMinSize(this.navExbandBar.computeSize(SWT.DEFAULT, SWT.DEFAULT));
		
		this.workspaceComposite = new ScrolledComposite(this.sashForm, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
		this.workspaceComposite.setExpandHorizontal(true);
		this.workspaceComposite.setExpandVertical(true);
		this.workspaceComposite.setLayout(new FillLayout());
		
		this.tabFolder = new CTabFolder(this.workspaceComposite, SWT.BORDER | SWT.CLOSE | SWT.FLAT);
		this.tabFolder.setBorderVisible(false);
		formToolkit.adapt(this.tabFolder);
		formToolkit.paintBordersFor(this.tabFolder);
		this.tabFolder.setSelectionBackground(Display.getCurrent().getSystemColor(SWT.COLOR_TITLE_INACTIVE_BACKGROUND_GRADIENT));
		
		
		this.workspaceComposite.setContent(this.tabFolder);
		this.workspaceComposite.setMinSize(this.tabFolder.computeSize(SWT.DEFAULT, SWT.DEFAULT));
		this.sashForm.setWeights(new int[] {204, 585});
		
		this.menu = new Menu(shell, SWT.BAR);
		this.shell.setMenuBar(menu);
		
		this.mntmFile = new MenuItem(menu, SWT.CASCADE);
		this.mntmFile.setText("File");
		
		this.menu_2 = new Menu(mntmFile);
		this.mntmFile.setMenu(menu_2);
		
		this.mntmExit = new MenuItem(this.menu_2, SWT.NONE);
		this.mntmExit.setText("Exit");
		
	}
	
	public void addViewPartTab(String tabName, ViewPart viewPart) {
		addViewPartToTabFolderAndSetFocus(tabName, viewPart);
	}
	
	private Composite addViewPartToTabFolderAndSetFocus(String tabName, ViewPart viewPart) {
		CTabItem tab = new CTabItem(this.tabFolder, SWT.NONE);
		tab.setText(tabName);
		
		Composite composite = new Composite(this.tabFolder, SWT.NONE);
		composite.setLayout(new FillLayout());
		tab.setControl(composite);
		viewPart.createPartControl(composite);
		
		formToolkit.paintBordersFor(composite);
		
		tabFolder.setFocus();
		tabFolder.setSelection(tab);
		composite.setFocus();

		return composite;
	}

	/**
	 * Launch the application.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			VclManagerApplication window = VclManagerApplication.getInstance();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
