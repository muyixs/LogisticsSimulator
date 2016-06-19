package loaders.network;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import exceptions.InvalidParamException;
import network.distance.Edge;
import network.distance.Graph;
import network.distance.Vertex;

public class NetworkLoadXMLImpl implements NetworkLoadFileType {
	@Override
	public Graph loadFile(String File) {
		List<Vertex> nodes = new ArrayList<Vertex>();
		List<Edge> edges = new ArrayList<Edge>();
		try {
			File logisticsXML = new File(File);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(logisticsXML);
			doc.getDocumentElement().normalize();
			NodeList nList = doc.getElementsByTagName("facility");
			for (int temp = 0; temp < nList.getLength(); temp++) {
				Node nNode = nList.item(temp);
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;
					String facilityName = eElement.getElementsByTagName("name").item(0).getTextContent();
					Vertex facility = new Vertex(facilityName, facilityName);
					nodes.add(facility);
					NodeList linksList = eElement.getElementsByTagName("links");
					NodeList distancesList = eElement.getElementsByTagName("distances");
					for (int tempTwo = 0; tempTwo < linksList.getLength(); tempTwo++) {
						NodeList childList = linksList.item(tempTwo).getChildNodes();
						NodeList distancesChildList = distancesList.item(tempTwo).getChildNodes();
						for (int j = 0; j < childList.getLength(); j++) {
							Node childNode = childList.item(j);
							Node distancesChildNode = distancesChildList.item(j);
							if ("value".equals(childNode.getNodeName())
									|| "value".equals(distancesChildNode.getNodeName())) {
								String connectedFacilityName = childList.item(j).getTextContent().trim();
								Vertex connectedFacility = new Vertex(connectedFacilityName, connectedFacilityName);
								Integer distance = Integer.parseInt(distancesChildList.item(j).getTextContent().trim());
								String edgeId = facilityName + "To" + connectedFacilityName;
								Edge connectedEdge = new Edge(edgeId, facility, connectedFacility, distance);
								edges.add(connectedEdge);
							}
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		Graph graph = new Graph();
		try {
			graph = new Graph(nodes, edges);
		} catch (InvalidParamException e) {
			e.getMessage();
			e.printStackTrace();
		}
		return graph;
	}
}
