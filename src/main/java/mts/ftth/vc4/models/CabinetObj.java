package mts.ftth.vc4.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CabinetObj {
    @JsonProperty("EXCH_CODE")
    private String exchCode;

    @JsonProperty("CABINET_NO")
    private String cabinetNo;

    @JsonProperty("INSTALLATION_LOCATION")
    private String instaillationLocation;

    @JsonProperty("LONGITUDE")
    private String longitude;
    
    @JsonProperty("LATITUDE")
    private String latitude;

    @JsonProperty("POPID")
    private String popId;

    @JsonProperty("POPNAME")
    private String popName;
}
