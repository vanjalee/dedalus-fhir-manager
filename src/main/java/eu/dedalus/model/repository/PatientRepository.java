package eu.dedalus.model.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import eu.dedalus.model.entity.Patient;

/**
 * Reepository to house the database operations over our patient
 * 
 * @author Vanja Bisanovic
 */
public interface PatientRepository extends JpaRepository<Patient, String> {

	Optional<Patient> findByFhirUrl(String fhirUrl);
	
	boolean existsByFhirUrl(String fhirUrl);

}
