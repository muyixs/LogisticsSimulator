package loaders.item;
import java.util.Map;

import item.Item;

public interface ItemLoadFileType {
	Map<String, Item> loadFile(String ItemFile);
}
