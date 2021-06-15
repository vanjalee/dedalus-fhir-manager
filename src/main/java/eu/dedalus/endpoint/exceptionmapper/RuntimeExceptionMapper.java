package eu.dedalus.endpoint.exceptionmapper;

import javax.inject.Inject;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.jboss.logging.Logger;

/**
 * @author Vanja Bisanovic
 */
@Provider
public class RuntimeExceptionMapper implements ExceptionMapper<RuntimeException> {

	@Inject
	Logger log;

	@Override
	public Response toResponse(RuntimeException exception) {

		log.error("internal server error", exception);
		return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
	}
}