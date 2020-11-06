package axiom.app.service.exception;


public class ServiceException extends Exception {

	public ServiceException(String msg) {
		super(msg);  
	}

	public ServiceException(String message, Throwable cause) {
		super(message, cause);
	}

}
