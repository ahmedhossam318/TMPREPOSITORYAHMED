package mts.ftth.vc4.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name="SC_USER_SCOPE", schema = "SO_SCHEMA")
@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@AllArgsConstructor
@SuperBuilder
public class UserDataScope {
	@Id
	private String USER_NAME;
	private String CODE;
	private String VALUE;
}
