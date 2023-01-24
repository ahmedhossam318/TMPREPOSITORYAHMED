package mts.ftth.vc4.models;

import java.util.ArrayList;
import java.util.Date;

import org.springframework.context.annotation.Configuration;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@Configuration
@RequiredArgsConstructor
public class UpLineCardResponse {
	    private int id;
	    private int ddfOdfId;
	    private int ddfOdfShelfId;
	    private int ddfOdfPatchPointDefinitionId;
	    private Object cableCircuitId;
	    private String frontBack;
	    private int projectStatus;
	    private int directionId;
	    private String comments;
	    private String extraInfo;
	    private int otherSidePositionId;
	    private Object orderId;
	    private Object ddfOdf;
	    private Object ddfOdfPatchPointDefinition;
	    private Object ddfOdfShelf;
	    private Object cableCircuit;
	    private Object ddfOdfPositionFromTo;
	    private Object ddfOdfPositionCableDetails;
	    private Object ddfOdfPositionCableDetailsOtherSide;
	    private Object ddfOdfPositionNameDetails;
	    private Object orderRelates;
	    private Object order;
	    private Object otherSidePosition;
	    private Date rowVersion;
	    private boolean hasErrors;
	    public ArrayList<Error> errors;
	    
}
