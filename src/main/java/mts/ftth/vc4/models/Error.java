package mts.ftth.vc4.models;

import lombok.Data;

@Data
public class Error {
	private String errorMessage;
	private String memberName;
	private int errorCode;
	private int errorMode;
}
