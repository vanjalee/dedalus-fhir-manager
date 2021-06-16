package eu.dedalus.service.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Vanja Bisanovic
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ContactDto {

	private List<RelationshipDto> relationship;

	private NameDto name;

	private List<TelecomDto> telecom;

	private AddressDto address;

	private String gender;

	private PeriodDto period;

}
