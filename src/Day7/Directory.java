package Day7;

import java.util.ArrayList;

public class Directory extends Element {

	private ArrayList<Element> elements = new ArrayList<Element>();

	public Directory(String name) {
		super(name);
	}

	public long calculateSize() {
		long size = 0;

		for (Element el : elements) {

			size += el.calculateSize();
		}

		return size;
	}

	public void addElement(Element element) {
		elements.add(element);
	}

	public boolean checkIfDirectoryExists(Directory dir) {
		for (Element el : elements) {
			if (el.equals(dir))
				return true;

		}
		return false;
	}

	public Directory findDirectory(String name) {
		for (Element el : elements) {
			if (el.getName().equals(name) && el instanceof Directory)
				return (Directory) el;
		}
		return null;
	}
}
