package mts.ftth.vc4.models;

import org.springframework.context.annotation.Configuration;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@Configuration
@RequiredArgsConstructor
public class UpBoxResponse {
	private String message;
    private String action;
}
