package loaders.item;
import java.util.Map;

import item.Item;
import loaders.Loader;

public class ItemLoader implements ItemLoaderInterface, Loader {
	private ItemLoadFileType loadbyFileType;
	private Map<String, Item> items;

	private void setFileTypeLoader(String fileName) {
		String extension = "";
		int i = fileName.lastIndexOf('.');
		if (i > 0) {
			extension = fileName.substring(i + 1);
		}
		if (extension.equalsIgnoreCase("xml")) {
			this.loadbyFileType = new ItemLoadXMLImpl();
		} else {
			this.loadbyFileType = new ItemLoadNullImpl();
		}
	}

	@Override
	public void load(String file) {
		setFileTypeLoader(file);
		setItems(loadbyFileType.loadFile(file));
	}

	@Override
	public void load(String file, String inventory) {

	}

	@Override
	public Map<String, Item> getItems() {
		return items;
	}

	private void setItems(Map<String, Item> items) {
		this.items = items;
	}
}
