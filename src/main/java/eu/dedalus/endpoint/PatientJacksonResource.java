package eu.dedalus.endpoint;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import eu.dedalus.endpoint.dto.FhirUrl;
import eu.dedalus.service.PatientBean;

/**
 * Endpoint analog to {@link PatientResource}, with the difference that it only
 * accepts JSON objects of the form {"url" : "url_path_to_requested_resource"}
 * 
 * @author Vanja Bisanovic
 *
 */
@Path("/patientjackson")
public class PatientJacksonResource {

	@Inject
	PatientBean patientBean;

	/**
	 * Service that consumes patient Fhir URL as application/json value of the form:
	 * {"url" : "url_path_to_requested_resource"} It etrieves the patient from the
	 * Fhir service, finally saving it to our database. In case the patient is
	 * completely new to our database, it will be normally persisted. In case there
	 * already is a patient with this unique URL in our database, this entry will be
	 * updated with any possible changes. We hold the patient's fhirUrl as a unique
	 * identifier apart from our own UIID. In case the provided URL is not valid on
	 * Fhir server, appropriate status codes will be returned:
	 * <p>
	 * 400 - in case the request URL was invalid
	 * </p>
	 * <p>
	 * 404 - in case the resource could not be found using this URL
	 * </p>
	 * <p>
	 * 422 - in case the requesting URL or the resource that it is referring to
	 * could have not been processed
	 * </p>
	 * <p>
	 * 200 - everything went fine, resource has been transfered
	 * </p>
	 * 
	 * @param fhirUrl as a location of the patient on the Fhir server
	 * @return HTTP Response holding info about the operation (status code)
	 */
	@POST
	public Response transferFhirPatient(@NotNull @Valid FhirUrl fhirUrl) {

		return patientBean.transferPatient(fhirUrl.getUrl());
	}

	/**
	 * Service that consumes patient Fhir URL as application/json value of the form:
	 * {"url" : "url_path_to_requested_resource"} It retrieves the patient from our
	 * database in case it can be found there. If the database hold no such entry,
	 * 404 will be returned as a response. Invalid requests will result with 400. In
	 * case entity is found and loaded, it will be returned as a JSON string.
	 * 
	 * @param fhirUrl as a location of the patient on the Fhir server
	 * @return HTTP Response holding info about the operation (status code)
	 */
	@GET
	public Response transferedPatient(@NotNull @Valid FhirUrl fhirUrl) {

		return patientBean.retrievePatient(fhirUrl.getUrl());
	}

}