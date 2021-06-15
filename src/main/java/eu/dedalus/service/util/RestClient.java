package eu.dedalus.service.util;

import java.util.concurrent.TimeUnit;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.client.WebTarget;

import org.apache.commons.lang3.Validate;
import org.jboss.logging.Logger;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;

import eu.dedalus.service.exception.FhirClientCreationException;

/**
 * Generic Bean that instantiates a Rest Client with a web target given a valid
 * URL. It also sets connect and read timeouts during the creation of the client
 * object.
 * 
 * @author Vanja Bisanovic
 */
@ApplicationScoped
public class RestClient {

	private static final Integer CONNECT_TIMEOUT_IN_SEC = 5;
	private static final Integer READ_TIMEOUT_IN_SEC = 5;

	@Inject
	Logger log;

	/**
	 * Method used to create a client and its web target give an URL parameter
	 * 
	 * @param url used as the base for the client creation
	 * @return WebTarget object that is ready to send requests to a specified URL
	 * @throws FhirClientCreationException in case the supplied URL was not a valid
	 *                                     URI template, of any of the parameters
	 *                                     used in the creation of the client are
	 *                                     null or invalid
	 */
	public WebTarget createWebTargetForUrl(String url) throws FhirClientCreationException {

		Validate.notBlank(url, "FhirUrl must not be blank");

		log.debugf("Creating client for URL %s", url);
		try {
			return ResteasyClientBuilder.newBuilder().connectTimeout(CONNECT_TIMEOUT_IN_SEC, TimeUnit.SECONDS)
					.readTimeout(READ_TIMEOUT_IN_SEC, TimeUnit.SECONDS).build().target(url);
		} catch (IllegalArgumentException e) {
			throw new FhirClientCreationException("Error creating web target for url parameter", e);
		}
	}
}
