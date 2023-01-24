package mts.ftth.vc4.models;

import java.io.Serializable;

import org.springframework.context.annotation.Configuration;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Configuration
@RequiredArgsConstructor
public class SplitterPortResponse implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8891048205440570120L;
	private String message;
	private String action;
	private Prameters parameters;
}
