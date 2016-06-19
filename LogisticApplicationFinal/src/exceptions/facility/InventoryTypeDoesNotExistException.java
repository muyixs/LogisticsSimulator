package exceptions.facility;

public class InventoryTypeDoesNotExistException extends Exception {
	public InventoryTypeDoesNotExistException(String msg) {
		super(msg);
	}
}
