package mts.ftth.vc4.models.response.GponNumberListResponse;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class GPONNumbersListResponse {
    private String message;
    private String action;
    private Parameters parameters;

    @Setter
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Parameters {
        @JsonProperty("ONT_LIST")
        private List<ONT> ontList;


            @Setter
            @Getter
            @AllArgsConstructor
            @NoArgsConstructor
            public static class ONT {
                @JsonProperty("CARD_PORT")
                private String cardPort;

                @JsonProperty("ONT_ID")
                private int ontId;

                @JsonProperty("ONT_SN")
                private String ontSn;

                @JsonProperty("DN_NO")
                private String dnNO;

        }
    }
}
