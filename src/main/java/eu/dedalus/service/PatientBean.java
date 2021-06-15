package eu.dedalus.service;

import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.commons.lang3.StringUtils;
import org.jboss.logging.Logger;

import eu.dedalus.model.entity.Patient;
import eu.dedalus.model.repository.PatientRepository;
import eu.dedalus.service.builder.PatientBuilder;
import eu.dedalus.service.dto.PatientDto;

/**
 * 
 * Bean that does the job of data management in our database, including the
 * coordination with the external services (Fhir) where the data is loaded from.
 * This service is meant to be used by dedalus-fhir-manager endpoints.
 * 
 * @author Vanja Bisanovic
 */
@ApplicationScoped
public class PatientBean {

	@Inject
	Logger log;

	@Inject
	PatientRepository patientRepository;

	@Inject
	FhirPatientBean fhirPatientBean;

	/**
	 * Method that retrieves the patient from the Fhir service, and saves it into
	 * our database. In case the patient is completely new to our database, it will
	 * be normally persisted. In case there already is a patient with this unique
	 * URL in our database, this entry will be updated with any possible changes. We
	 * hold the patient's fhirUrl as a unique identifier apart from our own UIID.
	 * This method also runs in a transaction, as it is an important operation where
	 * we change the data in our db. In case the provided URL is not valid on Fhir
	 * server, appropriate status codes will be returned:
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
	@Transactional(Transactional.TxType.REQUIRES_NEW)
	public Response transferPatient(String fhirUrl) {

		if (StringUtils.isBlank(fhirUrl)) {
			return Response.status(Status.BAD_REQUEST).build();
		}

		PatientDto patientDto = fhirPatientBean.getPatient(fhirUrl);

		if (patientDto != null) {
			Optional<Patient> patientOpt = patientRepository.findByFhirUrl(fhirUrl);
			if (patientOpt.isPresent()) {
				log.debug("Updating patient data with the fresh value from Fhir server");
				patientRepository.save(PatientBuilder.updatePatient(patientOpt.get(), patientDto));
			} else {
				log.debug("Saving new patient data transfered from the Fhir server");
				patientRepository.save(PatientBuilder.createPatient(fhirUrl, patientDto));
			}
			return Response.status(Status.OK).build();
		}

		return Response.status(Status.NOT_FOUND).build();
	}

	/**
	 * Retrieves the patient from our database in case it can be found there. If the
	 * database hold no such entry, 404 will be returned as a response. Invalid
	 * requests will result with 400. In case entity is found and loaded, it will be
	 * returned as a JSON string.
	 * 
	 * @param fhirUrl as a location of the patient on the Fhir server
	 * @return HTTP Response holding info about the operation (status code)
	 */
	public Response retrievePatient(String fhirUrl) {

		if (StringUtils.isBlank(fhirUrl)) {
			return Response.status(Status.BAD_REQUEST).build();
		}

		Optional<Patient> patient = patientRepository.findByFhirUrl(fhirUrl);
		if (patient.isPresent()) {
			return Response.ok().entity(patient.get()).build();
		}

		return Response.status(Status.NOT_FOUND).build();
	}
}
