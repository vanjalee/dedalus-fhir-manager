package eu.dedalus.endpoint.util;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status.Family;

/**
 * A class to hold any extension of the list of the HTTP Status codes
 * 
 * @author Vanja Bisanovic
 */
public enum HttpStatus implements Response.StatusType {

	UNPROCESSABLE_ENTITY(422, "Unprocessable Entity");

	private final int statusCode;

	private final String reasonPhrase;

	private final Family family;

	private HttpStatus(int statusCode, String reasonPhrase) {
		this.statusCode = statusCode;
		this.reasonPhrase = reasonPhrase;
		this.family = Family.familyOf(statusCode);
	}

	@Override
	public int getStatusCode() {
		return statusCode;
	}

	@Override
	public Family getFamily() {
		return family;
	}

	@Override
	public String getReasonPhrase() {
		return reasonPhrase;
	}

}
