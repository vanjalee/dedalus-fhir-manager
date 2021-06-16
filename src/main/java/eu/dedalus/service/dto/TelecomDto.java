package eu.dedalus.service.dto;

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
public class TelecomDto {

	private String use;

	private String system;

	private String value;

	private Integer rank;

	private PeriodDto period;
}
