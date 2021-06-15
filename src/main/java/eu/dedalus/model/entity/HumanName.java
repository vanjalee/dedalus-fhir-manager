package eu.dedalus.model.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author Vanja Bisanovic
 */
@Data
@Entity
@Table(name = "human_name")
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class HumanName extends AbstractEntity {

	@Column(name = "family")
	private String family;

	@ElementCollection
	@CollectionTable(name = "human_name_given", joinColumns = @JoinColumn(name = "name_id"))
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<String> given = new ArrayList<>();

	@ElementCollection
	@CollectionTable(name = "human_name_prefix", joinColumns = @JoinColumn(name = "name_id"))
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<String> prefix = new ArrayList<>();

	@ElementCollection
	@CollectionTable(name = "human_name_suffix", joinColumns = @JoinColumn(name = "name_id"))
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<String> suffix = new ArrayList<>();

}
