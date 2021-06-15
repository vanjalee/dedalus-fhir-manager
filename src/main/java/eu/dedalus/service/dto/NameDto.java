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
public class NameDto {
	
	private String use;
	
	private String family;
	
	private List<String> given;
	
	private List<String> prefix;
	
	private List<String> suffix;
	
	private PeriodDto period;
		
}
