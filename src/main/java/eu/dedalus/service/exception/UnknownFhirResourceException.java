package eu.dedalus.service.exception;

/**
 * @author Vanja Bisanovic
 */
public class UnknownFhirResourceException extends RuntimeException {

	private static final long serialVersionUID = -8025715403917065258L;

	private static final String MESSAGE = "Resource URL is valid, but the resource does not seem to exist";
	
	public UnknownFhirResourceException() {
		super(MESSAGE);
	}

	public UnknownFhirResourceException(String message, Throwable cause) {
		super(message, cause);
	}

	public UnknownFhirResourceException(String message) {
		super(message);
	}

	public UnknownFhirResourceException(Throwable cause) {
		super(MESSAGE, cause);
	}
}
