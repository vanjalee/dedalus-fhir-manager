package eu.dedalus.service.builder;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.Validate;

import eu.dedalus.model.entity.HumanName;
import eu.dedalus.model.entity.Patient;
import eu.dedalus.model.enumeration.Gender;
import eu.dedalus.service.dto.NameDto;
import eu.dedalus.service.dto.PatientDto;

/**
 * Simple utility class to create patient entity out of the URL and PatientDto,
 * or to update the data in an already existing entity.
 * 
 * @author Vanja Bisanovic
 */
public class PatientBuilder {

	/**
	 * This method creates patient entity out of patient Fhir URL and its dto
	 * returned from Fhir server
	 * 
	 * @param fhirUrl    as a unique identifier for this patient on Fhir server
	 * @param patientDto returned by the Fhir server during the transfer process
	 * @return Patient entity
	 */
	public static Patient createPatient(String fhirUrl, PatientDto patientDto) {

		Validate.notBlank(fhirUrl, "FhirUrl must not be blank");
		Validate.notNull(patientDto, "PatientDto must not be blank");

		Patient patient = new Patient();
		patient.setFhirUrl(fhirUrl);
		patient.setBirthday(patientDto.getBirthDate());
		patient.setGender(Gender.fromValue(patientDto.getGender()));

		if (CollectionUtils.isNotEmpty(patientDto.getName())) {
			for (NameDto nameDto : patientDto.getName()) {
				buildHumanName(nameDto, patient);
			}
		}
		return patient;
	}

	/**
	 * This method updates the fields in our entity with any changes found in fresh
	 * PatientDto object returned from the Fhir server
	 * 
	 * @param patient    retrieved from our database
	 * @param patientDto patientDto returned by the Fhir server during the transfer
	 *                   process
	 * @return Patient entity
	 */
	public static Patient updatePatient(Patient patient, PatientDto patientDto) {

		Validate.notNull(patient, "Patient must not be null");
		Validate.notNull(patientDto, "PatientDto must not be null");

		patient.setBirthday(patientDto.getBirthDate());
		patient.setGender(Gender.fromValue(patientDto.getGender()));

		if (CollectionUtils.isNotEmpty(patientDto.getName())) {
			for (NameDto nameDto : patientDto.getName()) {
				buildHumanName(nameDto, patient);
			}
		}
		return patient;
	}

	private static void buildHumanName(NameDto nameDto, Patient patient) {

		HumanName humanName = new HumanName();
		humanName.setFamily(nameDto.getFamily());

		if (CollectionUtils.isNotEmpty(nameDto.getGiven())) {
			for (String given : nameDto.getGiven()) {
				humanName.getGiven().add(given);
			}
		}

		if (CollectionUtils.isNotEmpty(nameDto.getPrefix())) {
			for (String prefix : nameDto.getPrefix()) {
				humanName.getPrefix().add(prefix);
			}
		}

		if (CollectionUtils.isNotEmpty(nameDto.getSuffix())) {
			for (String suffix : nameDto.getSuffix()) {
				humanName.getSuffix().add(suffix);
			}
		}

		patient.getNames().add(humanName);
	}

}
