package com.vcl.sample;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.core.databinding.observable.Realm;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;

import com.vcl.application.action.search.EntityDataSearchRequest;
import com.vcl.application.action.search.borrower.BorrowerDataSearchProvider;
import com.vcl.application.action.search.borrower.BorrowerSearchFactory;
import com.vcl.application.action.search.datatable.EntityTableDataSearch;
import com.vcl.application.borrower.BorrowerModel;
import com.vcl.application.borrower.BorrowerView;
import com.vcl.application.control.SearchModel;
import com.vcl.application.control.table.UITableBuilder;
import com.vcl.application.model.table.TableDataModelBinding;
import com.vcl.application.model.table.TableModelMetaData;
import com.vcl.borrower.Borrower;

/**
 * Demonstrates binding a TableViewer to a collection.
 */
public class Snippet009TableViewer {
	public static void main(String[] args) {
		final Display display = Display.getDefault();

		// In an RCP application, the threading Realm will be set for you
		// automatically by the Workbench. In an SWT application, you can do
		// this once, wrpping your binding method call.
		Realm.runWithDefault(SWTObservables.getRealm(display), new Runnable() {
			public void run() {

				ViewModel viewModel = new ViewModel();
				Shell shell = new View(viewModel).createShell();

				// The SWT event loop
				while (!shell.isDisposed()) {
					if (!display.readAndDispatch()) {
						display.sleep();
					}
				}
			}
		});
	}

	// Minimal JavaBeans support
	public static abstract class AbstractModelObject {
		private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

		public void addPropertyChangeListener(PropertyChangeListener listener) {
			propertyChangeSupport.addPropertyChangeListener(listener);
		}

		public void addPropertyChangeListener(String propertyName, PropertyChangeListener listener) {
			propertyChangeSupport.addPropertyChangeListener(propertyName, listener);
		}

		public void removePropertyChangeListener(PropertyChangeListener listener) {
			propertyChangeSupport.removePropertyChangeListener(listener);
		}

		public void removePropertyChangeListener(String propertyName, PropertyChangeListener listener) {
			propertyChangeSupport.removePropertyChangeListener(propertyName, listener);
		}

		protected void firePropertyChange(String propertyName, Object oldValue, Object newValue) {
			propertyChangeSupport.firePropertyChange(propertyName, oldValue, newValue);
		}
	}

	// The data model class. This is normally a persistent class of some sort.
	static class Person extends AbstractModelObject {
		// A property...
		String name = "";
		String email = "";

		public Person(String name, String email) {
			this.name = name;
			this.email = email;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

	}

	// The View's model--the root of our Model graph for this particular GUI.
	//
	// Typically each View class has a corresponding ViewModel class.
	// The ViewModel is responsible for getting the objects to edit from the
	// data access tier. Since this snippet doesn't have any persistent objects
	// ro retrieve, this ViewModel just instantiates a model object to edit.
	static class ViewModel {
		// The model to bind
		private List people = new LinkedList();
		{
			people.add(new Person("Steve Northover", "foobar@gmail.com"));
			people.add(new Person("Grant Gayed", "foobar@gmail.com"));
			people.add(new Person("Veronika Irvine", "foobar@gmail.com"));
			people.add(new Person("Mike Wilson", "foobar@gmail.com"));
			people.add(new Person("Christophe Cornu", "foobar@gmail.com"));
			people.add(new Person("Lynne Kues", "foobar@gmail.com"));
			people.add(new Person("Silenio Quarti", "foobar@gmail.com"));
		}

		public List getPeople() {
			return people;
		}
	}

	// The GUI view
	static class View {
		private ViewModel viewModel;
		private Table committers;
		private Shell shell = null;

		public View(ViewModel viewModel) {
			this.viewModel = viewModel;
		}

		private TableDataModelBinding tmbl = null;
		private UITableBuilder builder = null;
		
		private EntityDataSearchRequest buildRequest() {
			Borrower model = new Borrower();
			model.setFirstName("Bryant");
			model.setLastName("Grove");
			
			BorrowerView borrowerView = new BorrowerView();
			borrowerView.setModel(model);
			
			SearchModel searchModel = new SearchModel();
			searchModel.setModelOwner(borrowerView);
		
			TableModelMetaData metaData = BorrowerSearchFactory.createTableMetaData();
			
			return BorrowerSearchFactory.createForMetaDataAndSearchModel(metaData, searchModel);
		}
		
		public Shell createShell() {
			// Build a UI
			Display display = Display.getDefault();
			shell = new Shell(display);
			shell.setLayout(new FillLayout());

			Button show = new Button(shell, SWT.NONE);
			show.setText("Show");

			show.addSelectionListener(new SelectionAdapter() {
				public void widgetSelected(SelectionEvent event) {
					
					EntityTableDataSearch search = new EntityTableDataSearch();
					search.setDialogParent(shell);
					search.searchEntity(buildRequest());
					
				}
			});

			// Open and return the Shell
			shell.setSize(100, 300);
			shell.open();
			return shell;
		}
	}
}