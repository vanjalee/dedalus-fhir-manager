package eu.dedalus.endpoint.exceptionmapper;

import javax.inject.Inject;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.jboss.logging.Logger;

import eu.dedalus.service.exception.FhirClientCreationException;

/**
 * @author Vanja Bisanovic
 */
@Provider
public class FhirClientCreationExceptionMapper implements ExceptionMapper<FhirClientCreationException> {

	@Inject
	Logger log;

	@Override
	public Response toResponse(FhirClientCreationException exception) {

		log.error(exception.getMessage(), exception.getCause());
		return Response.status(Status.BAD_REQUEST).entity(exception.getMessage()).build();
	}
}
