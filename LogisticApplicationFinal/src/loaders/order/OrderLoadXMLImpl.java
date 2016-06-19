package loaders.order;

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
import managers.item.ItemManager;
import managers.item.SimpleItemManager;
import order.Order;
import order.OrderFactory;

public class OrderLoadXMLImpl implements OrderLoadFileType {
	@Override
	public Map<String, Order> loadFile(String File) {
		Map<String, Order> Orders = new HashMap<String, Order>();
		try {
			File logisticsXML = new File(File);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(logisticsXML);
			doc.getDocumentElement().normalize();
			NodeList nList = doc.getElementsByTagName("order");
			ItemManager itemManager = SimpleItemManager.getInstance();
			for (int temp = 0; temp < nList.getLength(); temp++) {
				Node nNode = nList.item(temp);
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;
					String orderId = eElement.getElementsByTagName("id").item(0).getTextContent();
					int orderTime = Integer.parseInt(eElement.getElementsByTagName("time").item(0).getTextContent());
					String orderDestination = eElement.getElementsByTagName("destination").item(0).getTextContent();
					String orderPriority = eElement.getElementsByTagName("priority").item(0).getTextContent();
					OrderFactory orderFactory = new OrderFactory();
					Order order = orderFactory.getOrder("Simple");
					order.setId(orderId);
					order.setTime(orderTime);
					order.setDestination(orderDestination);
					order.setPriority(orderPriority);
					NodeList linksList = eElement.getElementsByTagName("items");
					NodeList quantitiesList = eElement.getElementsByTagName("quantity");
					for (int tempTwo = 0; tempTwo < linksList.getLength(); tempTwo++) {
						NodeList childList = linksList.item(tempTwo).getChildNodes();
						NodeList quantitiesChildList = quantitiesList.item(tempTwo).getChildNodes();
						for (int j = 0; j < childList.getLength(); j++) {
							Node childNode = childList.item(j);
							Node quantitiesChildNode = quantitiesChildList.item(j);
							if ("value".equals(childNode.getNodeName())
									|| "value".equals(quantitiesChildNode.getNodeName())) {
								String item = childList.item(j).getTextContent().trim();
								int quantity = Integer.parseInt(quantitiesChildList.item(j).getTextContent().trim());
								if (itemManager.itemExists(item) == true) {
									order.addItem(item, quantity);

								} else {
									throw new ItemDoesNotExist(
											"Item does not exist, please check order file for errors:" + item);
								}
							}
						}
					}
					Orders.put(order.getId(), order);
				}
			}
		} catch (Exception e) {
			e.getMessage();
			e.printStackTrace();
		}
		return Orders;
	}
}
