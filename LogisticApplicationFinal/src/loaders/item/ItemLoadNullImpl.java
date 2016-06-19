package loaders.item;
import java.util.HashMap;
import java.util.Map;

import item.Item;

public class ItemLoadNullImpl implements ItemLoadFileType {

	@Override
	public Map<String, Item> loadFile(String facilityFile) {
		Map<String, Item> facilities = new HashMap<String, Item>();
		System.out.println("Implementation to open this file format does not yet exist.");
		return facilities;
	}
}
