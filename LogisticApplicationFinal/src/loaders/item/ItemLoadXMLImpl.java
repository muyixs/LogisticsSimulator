package loaders.item;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import item.Item;
import item.ItemFactory;

public class ItemLoadXMLImpl implements ItemLoadFileType {
	@Override
	public Map<String, Item> loadFile(String ItemFile) {
		Map<String, Item> items = new HashMap<String, Item>();
		try {
			File ItemsXML = new File(ItemFile);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(ItemsXML);
			doc.getDocumentElement().normalize();
			NodeList nList = doc.getElementsByTagName("item");
			for (int temp = 0; temp < nList.getLength(); temp++) {
				Node nNode = nList.item(temp);
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;
					String itemId = eElement.getElementsByTagName("id").item(0).getTextContent();
					Integer itemCost = Integer.parseInt(eElement.getElementsByTagName("cost").item(0).getTextContent());
					ItemFactory ItemFactory = new ItemFactory();
					Item item = ItemFactory.getItem("Simple");
					item.setId(itemId);
					item.setCost(itemCost);
					items.put(itemId, item);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return items;
	}
}
