package mts.ftth.vc4.models.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Parameters   {

    @JsonProperty("STATUS")
    private String status;

    @JsonProperty("SMESSAGE")
    private String sMessage;

    @JsonProperty("Result_Code")
    private String resultCode;

    @JsonProperty("Result_Message")
    private String resultMessage;

    @JsonProperty("Cabinet_Name")
    private String cabinetName;

    @JsonProperty("Cabinet_IMS_ID")
    private String cabinetImsId;

    @JsonProperty("Cabinet_IMS_Name")
    private String cabinetImsName;

    @JsonProperty("ROWVERSION")
    private String rowVersion;
}
