package org.art_core.dev.cinder.model;

import org.eclipse.core.resources.IFile;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.IDE;

public abstract class ItemType implements Comparable<ItemType> {
	private static final ISharedImages PLATFORM_IMAGES =
		PlatformUI.getWorkbench().getSharedImages();
	
	private final String id;
	private final String printName;
	private final int ordinal;
	
	private ItemType(String id, String name, int position) {
		this.id = id;
		this.printName = name;
		this.ordinal = position;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return printName;
	}
	
	public abstract Image getImage();
	public abstract IItem newItem(Object obj);
	public abstract IItem loadItem(String info);
	
	public int compareTo(ItemType other) {
		return this.ordinal - other.ordinal;
	}
	
	public static final ItemType UNKNOWN 
		= new ItemType("Unknown", "Unknown", 0) 
	{
		public Image getImage() {
			return null;
		}
		public IItem newItem(Object obj) {
			return null;
		}
		public IItem loadItem(String info) {
			return null;
		}
	};
	
	public static final ItemType WORKBENCH_FILE 
		= new ItemType("WBFile", "Workbench File", 1) 
	{
		public Image getImage() {
			return PLATFORM_IMAGES
				.getImage(org.eclipse.ui.ISharedImages.IMG_OBJ_FILE);
		}

		public IItem newItem(Object obj) {
			if (!(obj instanceof IFile))
				return null;
			return null;
		}

		public IItem loadItem(String info) {
			return null;
		}
	};
	
	public static final ItemType WORKBENCH_FOLDER 
	= new ItemType("WBFolder", "Workbench Folder", 2)
{
	public Image getImage() {
		return PLATFORM_IMAGES
				.getImage(org.eclipse.ui.ISharedImages.IMG_OBJ_FOLDER);
	}

	public IItem newItem(Object obj) {
		return null;
	}

	public IItem loadItem(String info) {
		return null;
	}
};

public static final ItemType WORKBENCH_PROJECT 
	= new ItemType("WBProj", "WorkbenchProject", 3) 
{
	public Image getImage() {
		return PLATFORM_IMAGES
				.getImage(IDE.SharedImages.IMG_OBJ_PROJECT);
	}

	public IItem newItem(Object obj) {
		return null;
	}

	public IItem loadItem(String info) {
		return null;
	}
};

public static final ItemType JAVA_PROJECT 
	= new ItemType("JProj", "Java Project", 4) 
{
	public Image getImage() {
		return PLATFORM_IMAGES
				.getImage(IDE.SharedImages.IMG_OBJ_PROJECT);
	}

	public IItem newItem(Object obj) {
		return null;
	}

	public IItem loadItem(String info) {
		return null;
	}
};

public static final ItemType JAVA_PACKAGE_ROOT 
	= new ItemType("JPkgRoot", "Java Package Root", 5) 
{
	public Image getImage() {
		return PLATFORM_IMAGES
				.getImage(org.eclipse.ui.ISharedImages.IMG_OBJ_FOLDER);
	}

	public IItem newItem(Object obj) {
		return null;
	}

	public IItem loadItem(String info) {
		return null;
	}
};

public static final ItemType JAVA_PACKAGE 
	= new ItemType("JPkg", "Java Package", 6) 
{
	public Image getImage() {
		return org.eclipse.jdt.ui.JavaUI.getSharedImages()
				.getImage(org.eclipse.jdt.ui.ISharedImages.IMG_OBJS_PACKAGE);
	}

	public IItem newItem(Object obj) {
		return null;
	}

	public IItem loadItem(String info) {
		return null;
	}
};

public static final ItemType JAVA_CLASS_FILE 
	= new ItemType("JClass", "Java Class File", 7) 
{
	public Image getImage() {
		return org.eclipse.jdt.ui.JavaUI.getSharedImages()
				.getImage(org.eclipse.jdt.ui.ISharedImages.IMG_OBJS_CFILE);
	}

	public IItem newItem(Object obj) {
		return null;
	}

	public IItem loadItem(String info) {
		return null;
	}
};

public static final ItemType JAVA_COMP_UNIT 
	= new ItemType("JCompUnit", "Java Compilation Unit", 8) 
{
	public Image getImage() {
		return org.eclipse.jdt.ui.JavaUI.getSharedImages()
				.getImage(org.eclipse.jdt.ui.ISharedImages.IMG_OBJS_CUNIT);
	}

	public IItem newItem(Object obj) {
		return null;
	}

	public IItem loadItem(String info) {
		return null;
	}
};

public static final ItemType JAVA_INTERFACE 
	= new ItemType("JInterface", "Java Interface", 9) 
{
	public Image getImage() {
		return org.eclipse.jdt.ui.JavaUI.getSharedImages()
				.getImage(org.eclipse.jdt.ui.ISharedImages.IMG_OBJS_INTERFACE);
	}

	public IItem newItem(Object obj) {
		return null;
	}

	public IItem loadItem(String info) {
		return null;
	}
};

	public static final ItemType JAVA_CLASS 
	= new ItemType("JClass", "Java Class", 10) 
{
	public Image getImage() {
		return org.eclipse.jdt.ui.JavaUI.getSharedImages()
				.getImage(org.eclipse.jdt.ui.ISharedImages.IMG_OBJS_CLASS);
	}

	public IItem newItem(Object obj) {
		return null;
	}

	public IItem loadItem(String info) {
		return null;
	}
};
	
	private static final ItemType[] TYPES = { UNKNOWN,  
		WORKBENCH_FILE, WORKBENCH_FOLDER, WORKBENCH_PROJECT,
        JAVA_PROJECT, JAVA_PACKAGE_ROOT, JAVA_PACKAGE,
        JAVA_CLASS_FILE, JAVA_COMP_UNIT, JAVA_INTERFACE, JAVA_CLASS,};
  
	public static ItemType[] getTypes() {
		return TYPES;
	}
}