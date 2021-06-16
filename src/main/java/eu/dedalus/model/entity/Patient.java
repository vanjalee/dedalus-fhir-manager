package eu.dedalus.model.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;

import eu.dedalus.model.enumeration.Gender;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author Vanja Bisanovic
 */
@Data
@Entity
@Table(name = "patient")
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Patient extends AbstractEntity {

	@Column(name = "fhir_url", nullable = false, unique = true)
	private String fhirUrl;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
	private List<HumanName> names = new ArrayList<>();

	@Enumerated(EnumType.STRING)
	private Gender gender;

	private LocalDate birthday;

}
