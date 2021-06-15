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
public class IdentifierDto {
	
	private String use;
	
	private TypeDto type;
	
	private String system;
	
	private String value;
	
	private PeriodDto period;
	
	private AssignerDto assigner;
	
}
