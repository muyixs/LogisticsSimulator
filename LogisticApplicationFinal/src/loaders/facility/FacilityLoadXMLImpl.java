package loaders.facility;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import exceptions.item.ItemDoesNotExist;
import facility.Facility;
import facility.FacilityFactory;
import managers.item.ItemManager;
import managers.item.SimpleItemManager;

public class FacilityLoadXMLImpl implements FacilityLoadFileType {
	@Override
	public Map<String, Facility> loadFile(String facilityFile, String InventoryFile) {
		Map<String, Facility> facilities = new HashMap<String, Facility>();
		try {
			File logisticsXML = new File(facilityFile);
			File InventoryXML = new File(InventoryFile);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document facilityDoc = dBuilder.parse(logisticsXML);
			Document inventoryDoc = dBuilder.parse(InventoryXML);
			facilityDoc.getDocumentElement().normalize();
			inventoryDoc.getDocumentElement().normalize();
			NodeList facilityList = facilityDoc.getElementsByTagName("facility");
			NodeList inventoryList = inventoryDoc.getElementsByTagName("facility");
			ItemManager itemManager = SimpleItemManager.getInstance();
			for (int temp = 0; temp < facilityList.getLength(); temp++) {
				Node facilityNode = facilityList.item(temp);
				Node inventoryNode = inventoryList.item(temp);
				if (facilityNode.getNodeType() == Node.ELEMENT_NODE
						&& inventoryNode.getNodeType() == Node.ELEMENT_NODE) {
					Element facilityElement = (Element) facilityNode;
					Element inventoryElement = (Element) inventoryNode;
					String facilityName = facilityElement.getElementsByTagName("name").item(0).getTextContent();
					Integer facilityRate = Integer
							.parseInt(facilityElement.getElementsByTagName("rate").item(0).getTextContent());
					Integer facilityCost = Integer
							.parseInt(facilityElement.getElementsByTagName("cost").item(0).getTextContent());
					Integer facilityLimit = Integer
							.parseInt(facilityElement.getElementsByTagName("limit").item(0).getTextContent());
					FacilityFactory facilityFactory = new FacilityFactory();
					Facility facility = facilityFactory.getFacility("Simple");
					facility.setInventory("simple");
					facility.setSchedule("simple");
					facility.setName(facilityName);
					facility.setRate(facilityRate);
					facility.setCost(facilityCost);
					facility.setDays(50);
					facility.setLimit(facilityLimit);
					NodeList itemIdList = inventoryElement.getElementsByTagName("itemId");
					NodeList quantityList = inventoryElement.getElementsByTagName("quantity");
					for (int tempTwo = 0; tempTwo < itemIdList.getLength(); tempTwo++) {
						NodeList childList = itemIdList.item(tempTwo).getChildNodes();
						NodeList quantityChildList = quantityList.item(tempTwo).getChildNodes();
						for (int j = 0; j < childList.getLength(); j++) {
							Node childNode = childList.item(j);
							Node distancesChildNode = quantityChildList.item(j);
							if ("value".equals(childNode.getNodeName())
									|| "value".equals(distancesChildNode.getNodeName())) {
								String item = childList.item(j).getTextContent().trim();
								Integer quantity = Integer.parseInt(quantityChildList.item(j).getTextContent().trim());
								if (itemManager.itemExists(item) == true) {
									facility.addItem(item, quantity);

								} else {
									throw new ItemDoesNotExist(
											"Item does not exist, please check facility file for errors:" + item);
								}
							}
						}
					}
					facilities.put(facility.getName(), facility);
				}
			}
		} catch (Exception e) {
			e.getMessage();
			e.printStackTrace();
		}
		return facilities;
	}
}
