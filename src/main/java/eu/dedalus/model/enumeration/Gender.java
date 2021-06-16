package eu.dedalus.model.enumeration;

import org.apache.commons.lang3.StringUtils;

import lombok.Getter;

/**
 * @author Vanja Bisanovic
 */
@Getter
public enum Gender {

	MALE("male"), FEMALE("female"), OTHER("other"), UNKNOWN("unknown");

	private String value;

	private Gender(String value) {
		this.value = value;
	}

	public static Gender fromValue(String value) {

		if (StringUtils.isBlank(value)) {
			return null;
		}
		for (Gender gender : Gender.values()) {
			if (gender.getValue().equals(value)) {
				return gender;
			}
		}
		return null;
	}
}