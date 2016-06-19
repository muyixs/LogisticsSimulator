package driver;

import loaders.Loader;
import loaders.LoaderFactory;
import loaders.facility.FacilityLoaderInterface;
import loaders.item.ItemLoaderInterface;
import loaders.network.NetworkLoaderInterface;
import loaders.order.OrderLoaderInterface;
import managers.facility.FacilityManager;
import managers.facility.FacilityManagerFactory;
import managers.item.ItemManager;
import managers.item.ItemManagerFactory;
import managers.order.OrderManager;
import managers.order.OrderManagerFactory;
import network.NetworkProcessor;
import network.SimpleNetworkProcessor;
import orderprocessor.OrderProcessor;
import orderprocessor.OrderProcessorFactory;
import output.Output;
import output.OutputFactory;

public class Main {

	public static void main(String[] args) {
		try {
			LoaderFactory loaderFactory = new LoaderFactory();
			Loader networkLoader = loaderFactory.getLoader("Network");
			networkLoader.load("Logistics.XML");
			NetworkProcessor processor = SimpleNetworkProcessor.getInstance();
			processor.setFacilityNetwork((NetworkLoaderInterface) networkLoader);
			processor.setHoursPerDay(8);
			processor.setmilesPerHour(50);
			Loader itemLoader = loaderFactory.getLoader("Item");
			itemLoader.load("Items.xml");
			ItemManagerFactory itemManagerFactory = new ItemManagerFactory();
			ItemManager itemManager = itemManagerFactory.getItemManager("Simple");
			itemManager.setListOfItems((ItemLoaderInterface) itemLoader);
			// System.out.println(itemManager.displayItems());
			Loader facilityLoader = loaderFactory.getLoader("Facility");
			facilityLoader.load("Logistics.xml", "Inventory.xml");
			FacilityManagerFactory facilityManagerFactory = new FacilityManagerFactory();
			FacilityManager facilityManager = facilityManagerFactory.getFacilityManager("Simple");
			facilityManager.setListOfFacilities((FacilityLoaderInterface) facilityLoader);
			Loader orderLoader = loaderFactory.getLoader("Order");
			///// Time Priority Implementation
			orderLoader.load("Orders.xml");
			OrderManagerFactory orderManagerFactory = new OrderManagerFactory();
			OrderManager orderManager = orderManagerFactory.getOrderManager("Simple");
			orderManager.setListOfOrders((OrderLoaderInterface) orderLoader);
			OrderProcessorFactory orderProcessorFactory = new OrderProcessorFactory();
			OrderProcessor orderProcessor = orderProcessorFactory.getOrderProcessor("Simple");
			orderProcessor.processOrders(facilityManager, processor, itemManager, orderManager);
			OutputFactory outputFactory = new OutputFactory();
			Output orderOutput = outputFactory.getOutput("Order");
			orderOutput.setOrderOutput(orderProcessor);
			System.out.println(
					"<------------------------------Time Priority Implementation--------------------------------->\n");
			System.out.println("1) Order Output\n");
			orderOutput.displayOutput();
			Output facilityOutput = outputFactory.getOutput("Facility");
			facilityOutput.setFacilityOutput(facilityManager, processor);
			System.out.println("1) Facility Output \n");
			facilityOutput.displayOutput();
			System.out.println(
					"<---------------------------------------------------------------------------------------------->\n");
			////// Reset data for cost priority
			facilityLoader.load("Logistics.xml", "Inventory.xml");
			facilityManager.setListOfFacilities((FacilityLoaderInterface) facilityLoader);
			///// Cost Priority Implementation
			orderLoader.load("OrderCostPriority.xml");
			orderManager.setListOfOrders((OrderLoaderInterface) orderLoader);
			orderProcessor.processOrders(facilityManager, processor, itemManager, orderManager);
			orderOutput.setOrderOutput(orderProcessor);
			System.out.println(
					"<------------------------------Cost Priority Implementation--------------------------------->\n");
			System.out.println("2) Order Output \n");
			orderOutput.displayOutput();
			System.out.println("2) Facility Output \n");
			facilityOutput.displayOutput();
			System.out.println(
					"<---------------------------------------------------------------------------------------------->\n");

			/*
			 * System.out.printf("The distance in travel days from Seattle, WA
			 * to Nashville, TN is: %.2f days \n", processor.cost("Seattle, WA",
			 * "Nashville, TN")); System.out.printf("The distance in travel days
			 * from New York City, NY to Phoenix, AZ is: %.2f days \n",
			 * processor.cost("New York City, NY", "Phoenix, AZ"));
			 * System.out.printf("The distance in travel days from Fargo, ND to
			 * Austin, TX is: %.2f days \n", processor.cost("Fargo, ND",
			 * "Austin, TX")); System.out.printf("The distance in travel days
			 * from Denver, CO to Miami, FL is: %.2f days \n", processor.cost(
			 * "Denver, CO", "Miami, FL"));
			 */
		} catch (Exception e) {
			e.getMessage();
			e.printStackTrace();
		}

	}
}
