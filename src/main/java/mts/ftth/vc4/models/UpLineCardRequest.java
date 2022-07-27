package mts.ftth.vc4.models;

import java.util.ArrayList;
import lombok.Data;

@Data
public class UpLineCardRequest {
	public String id;
    public ArrayList<PropertyAndValue> propertyAndValues;
}
