package org.art_core.dev.cinder.views;

import org.art_core.dev.cinder.model.ItemManager;
import org.art_core.dev.cinder.model.ItemManagerEvent;
import org.art_core.dev.cinder.model.ItemManagerListener;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.Viewer;


/*
 * The content provider class is responsible for
 * providing objects to the view. It can wrap
 * existing objects in adapters or simply return
 * objects as-is. These objects may be sensitive
 * to the current input of the view, or ignore
 * it and always show the same content 
 * (like Task List, for example).
 */
 
public class JFContentProvider 
	implements IStructuredContentProvider, ItemManagerListener
{
	private TableViewer viewer;
	private ItemManager manager;

	public void inputChanged(Viewer v, Object oldInput, Object newInput) {
		this.viewer = (TableViewer) v;
		if (manager != null)
			manager.removeItemManagerListener(this);
		manager = (ItemManager) newInput;
		if (manager != null)
			manager.addItemManagerListener(this);
	}
	
	public void dispose() {
	}
	
	public Object[] getElements(Object parent) {
		return manager.getItems();
	}
	
	public void itemsChanged(ItemManagerEvent event) {
		viewer.getTable().setRedraw(false);
		try {
			viewer.remove(event.getItemsRemoved());
			viewer.add(event.getItemsAdded());
		} finally {
			viewer.getTable().setRedraw(true);
		}
	}
}