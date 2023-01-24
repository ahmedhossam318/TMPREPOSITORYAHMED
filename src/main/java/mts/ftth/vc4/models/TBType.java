package mts.ftth.vc4.models;

import org.springframework.context.annotation.Configuration;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@Configuration
@RequiredArgsConstructor
public class TBType {
	private String id;
	private String capacity;
	private String odfcat;
	private String odftype;
	private String rowVersion;
	private String errors;
	private String hasErrors;
}
