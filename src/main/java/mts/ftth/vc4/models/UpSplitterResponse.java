package mts.ftth.vc4.models;

import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@Configuration
@RequiredArgsConstructor
public class UpSplitterResponse {
  
	private String message;
    private String action;
    
}
