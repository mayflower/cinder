package org.art_core.dev.cinder.model;

import org.eclipse.core.runtime.IAdaptable;

public interface IItem 
	extends IAdaptable
{	
	String getName();
	String getLocation();
	int getLine();
	int getOffset();
	ItemType getType();
	
	
	static IItem[] NONE = new IItem[] {};
}
