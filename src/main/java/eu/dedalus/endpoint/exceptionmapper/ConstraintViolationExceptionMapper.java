package eu.dedalus.endpoint.exceptionmapper;

import java.util.stream.Collectors;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import eu.dedalus.endpoint.util.Headers;

/**
 * @author Vanja Bisanovic
 */
@Provider
public class ConstraintViolationExceptionMapper implements ExceptionMapper<ConstraintViolationException> {

	@Override
	public Response toResponse(ConstraintViolationException exception) {

		String reason = exception.getConstraintViolations().stream().map(ConstraintViolation::getMessage)
				.collect(Collectors.joining(" and "));
		return Response.status(Response.Status.BAD_REQUEST).header(Headers.REASON, reason).build();
	}
}