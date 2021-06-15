package eu.dedalus.service;

import java.net.SocketTimeoutException;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.ProcessingException;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.commons.lang3.Validate;
import org.jboss.logging.Logger;

import eu.dedalus.service.dto.PatientDto;
import eu.dedalus.service.exception.FhirResourceTransferException;
import eu.dedalus.service.exception.UnknownFhirResourceException;
import eu.dedalus.service.util.RestClient;

/**
 * Bean that holds the methods for communication with the external Fhir service.
 * 
 * @author Vanja Bisanovic
 */
@ApplicationScoped
public class FhirPatientBean {

	@Inject
	Logger log;

	@Inject
	RestClient restClient;

	/**
	 * Retrieves the patientDto object from Fhir server given a correct URL, or
	 * throws exceptions if the process could not complete successfully
	 * 
	 * @param fhirUrl as the full URL path where the patient is located on Fhir
	 *                server
	 * @return DTO object with the patient data
	 * @throws FhirResourceTransferException in case there was a problem during
	 *                                       processing of the request that was
	 *                                       triggered by the fhirUrl parameter
	 * @throws UnknownFhirResourceException  in case the invocation of the endpoint
	 *                                       is taking to long to complete. Check
	 *                                       {@link RestClient} for the specified
	 *                                       timeouts
	 */
	public PatientDto getPatient(String fhirUrl) throws FhirResourceTransferException, UnknownFhirResourceException {

		Validate.notBlank(fhirUrl, "FhirUrl must not be blank");

		WebTarget webTarget = restClient.createWebTargetForUrl(fhirUrl);
		PatientDto result = null;
		try {
			log.debugf("Building and sending a request for URL %s", fhirUrl);
			Response response = webTarget.request(MediaType.APPLICATION_JSON).get();
			log.debugf("Response status code: %d", response.getStatus());
			if (response.getStatus() == Status.OK.getStatusCode()) {
				result = response.readEntity(PatientDto.class);
			}
			response.close();
		} catch (ProcessingException e) {
			if (e.getCause() instanceof SocketTimeoutException) {
				throw new UnknownFhirResourceException(
						"Resource transfer takes longer than expected, timeout expired: Resource might not exist", e);
			}
			throw new FhirResourceTransferException("Error during invocation of the Fhir endpoint", e);
		} catch (IllegalStateException e) {
			throw new FhirResourceTransferException("Error during reading the data from the Fhir endpooint response",
					e);
		}
		return result;
	}

}
