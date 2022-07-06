package mts.ftth.vc4.models;

import lombok.Data;

@Data
public class SplitterType {
	private String id;
	private String portstart;
	private String capacity;
	private String type;
	private String rowVersion;
	private String errors;
	private String hasErrors;
}
