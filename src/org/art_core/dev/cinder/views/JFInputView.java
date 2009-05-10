package org.art_core.dev.cinder.views;

import org.art_core.dev.cinder.model.ItemManager;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.ui.part.*;
import org.eclipse.jface.viewers.*;
import org.eclipse.jface.action.*;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.ui.*;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.SWT;

public class JFInputView extends ViewPart {
	private static final String MONKEY_VIEW_ID = 
		"org.art_core.dev.cinder.views.JFMonkeyView";
	
	private final String[] colNames = {"", "Name", "Status", "Line", "Offset"};
	
	private TableViewer viewer;
	private TableColumn tCol, nCol, sCol, lineCol, offCol;
	
	private Action action1;
	private Action action2;
	private Action doubleClickAction;

	/**
	 * The constructor.
	 */
	public JFInputView() {
	}

	/**
	 * This is a callback that will allow us
	 * to create the viewer and initialize it.
	 */
	public void createPartControl(Composite parent) {
		createTableViewer(parent);
		makeActions();
		hookContextMenu();
		hookDoubleClickAction();
		contributeToActionBars();
	}

	/**
	 * Creates the TableViewer
	 * @param parent
	 */
	private void createTableViewer(Composite parent) {
		viewer = new TableViewer(
			parent, 
			SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL | SWT.FULL_SELECTION
		);
		final Table table = viewer.getTable();
		
		// icon column
		tCol = new TableColumn(table, SWT.LEFT);
		tCol.setText(colNames[0]);
		tCol.setWidth(20);
		
		// name column
		nCol = new TableColumn(table, SWT.LEFT);
		nCol.setText(colNames[1]);
		nCol.setWidth(150);
		
		// status column
		sCol = new TableColumn(table, SWT.LEFT);
		sCol.setText(colNames[2]);
		sCol.setWidth(150);
		
		// line number column
		lineCol = new TableColumn(table, SWT.LEFT);
		lineCol.setText(colNames[3]);
		lineCol.setWidth(50);
		
		// offset column
		offCol = new TableColumn(table, SWT.LEFT);
		offCol.setText(colNames[4]);
		offCol.setWidth(50);
		
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		viewer.setContentProvider(new JFContentProvider());
		viewer.setLabelProvider(new JFLabelProvider());
		viewer.setSorter(new JFSorter());
		viewer.setInput(ItemManager.getManager());
	}
	
	/**
	 * Adds actions to the context menu
	 */
	private void hookContextMenu() {
		MenuManager menuMgr = new MenuManager("#PopupMenu");
		menuMgr.setRemoveAllWhenShown(true);
		menuMgr.addMenuListener(new IMenuListener() {
			public void menuAboutToShow(IMenuManager manager) {
				JFInputView.this.fillContextMenu(manager);
			}
		});
		Menu menu = menuMgr.createContextMenu(viewer.getControl());
		viewer.getControl().setMenu(menu);
		getSite().registerContextMenu(menuMgr, viewer);
	}
	
	/**
	 * Adds actions to a double click
	 */
	private void hookDoubleClickAction() {
		viewer.addDoubleClickListener(new IDoubleClickListener() {
			public void doubleClick(DoubleClickEvent event) {
				doubleClickAction.run();
			}
		});
	}

	private void contributeToActionBars() {
		IActionBars bars = getViewSite().getActionBars();
		fillLocalPullDown(bars.getMenuManager());
		fillLocalToolBar(bars.getToolBarManager());
	}

	private void fillLocalPullDown(IMenuManager manager) {
		manager.add(action1);
		manager.add(new Separator());
		manager.add(action2);
	}

	private void fillContextMenu(IMenuManager manager) {
		manager.add(action1);
		manager.add(action2);
		// Other plug-ins can contribute there actions here
		manager.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS));
	}
	
	private void fillLocalToolBar(IToolBarManager manager) {
		manager.add(action1);
		manager.add(action2);
	}

	/**
	 * Initialize the actions needed
	 */
	private void makeActions() {
		// action1 - generic
		action1 = new Action() {
			public void run() {
				showMessage("Action 1 executed", "Action Title");
			}
		};
		action1.setText("Action 1");
		action1.setToolTipText("Action 1 tooltip");
		action1.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().
			getImageDescriptor(ISharedImages.IMG_OBJS_INFO_TSK));
		
		// action2 - generic
		action2 = new Action() {
			public void run() {
				showMessage("Action 2 executed", "Action Title");
			}
		};
		action2.setText("Action 2");
		action2.setToolTipText("Action 2 tooltip");
		action2.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().
				getImageDescriptor(ISharedImages.IMG_OBJS_INFO_TSK));
		
		// doubleclick - generic
		doubleClickAction = new Action() {
			public void run() {
				ISelection selection = viewer.getSelection();
				Object obj = ((IStructuredSelection)selection).getFirstElement();
				showMessage("Double-click detected on "+obj.toString(), "Action Title");
			}
		};
	}

	/**
	 * Show a popup message
	 * @param message
	 * @param title
	 */
	private void showMessage(String message, String title) {
		MessageDialog.openInformation(
			viewer.getControl().getShell(),
			title,
			message);
	}

	/**
	 * Passing the focus request to the viewer's control.
	 */
	public void setFocus() {
		viewer.getControl().setFocus();
	}
}