package eu.dedalus.service.exception;

/**
 * @author Vanja Bisanovic
 */
public class FhirResourceTransferException extends RuntimeException {

	private static final long serialVersionUID = -8025715403917065258L;

	private static final String MESSAGE = "Error during processing of Fhir endpoint response";
	
	public FhirResourceTransferException() {
		super(MESSAGE);
	}

	public FhirResourceTransferException(String message, Throwable cause) {
		super(message, cause);
	}

	public FhirResourceTransferException(String message) {
		super(message);
	}

	public FhirResourceTransferException(Throwable cause) {
		super(MESSAGE, cause);
	}
}
