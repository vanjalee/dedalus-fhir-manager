package eu.dedalus.service.dto;

import java.time.LocalDate;
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
public class PatientDto {
	
	private String resourceType;
	
	private String id;
			
	private List<IdentifierDto> identifier;
	
	private boolean active;
	
	private List<NameDto> name;
	
	private List<TelecomDto> telecom;
	
	private String gender;
	
	private LocalDate birthDate;
		
	private List<AddressDto> address;
	
	private List<ContactDto> contact;
	
	private ManagingOrganizationDto managingOrganization;
	
}
