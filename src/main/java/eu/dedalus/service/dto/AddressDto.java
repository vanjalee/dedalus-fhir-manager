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
public class AddressDto {

	private String use;

	private String type;

	private String text;

	private List<String> line;

	private String city;

	private String district;

	private String state;

	private String postalCode;

	private PeriodDto period;

}
