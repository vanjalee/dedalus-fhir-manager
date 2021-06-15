package eu.dedalus.endpoint.exceptionmapper;

import javax.inject.Inject;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.jboss.logging.Logger;

import eu.dedalus.endpoint.util.HttpStatus;
import eu.dedalus.service.exception.FhirResourceTransferException;

/**
 * @author Vanja Bisanovic
 */
@Provider
public class FhirResourceTransferExceptionMapper implements ExceptionMapper<FhirResourceTransferException> {

	@Inject
	Logger log;

	@Override
	public Response toResponse(FhirResourceTransferException exception) {
		
		log.error(exception.getMessage(), exception.getCause());
		return Response.status(HttpStatus.UNPROCESSABLE_ENTITY).entity(exception.getMessage()).build();
	}
}
