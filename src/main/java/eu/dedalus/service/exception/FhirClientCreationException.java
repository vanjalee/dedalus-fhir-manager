package eu.dedalus.service.exception;

/**
 * @author Vanja Bisanovic
 */
public class FhirClientCreationException extends RuntimeException {

	private static final long serialVersionUID = 2472376757017843125L;
	
	private static final String MESSAGE = "Error during creation of Fhir client and its web target";

	public FhirClientCreationException() {
        super(MESSAGE);
    }

	public FhirClientCreationException(String message, Throwable cause) {
		super(message, cause);
	}

	public FhirClientCreationException(String message) {
		super(message);
	}

	public FhirClientCreationException(Throwable cause) {
		super(MESSAGE, cause);
	}
}
