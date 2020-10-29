package com.easymer.client;

import com.easymer.client.dto.TemaDto;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class TemaResponse {
    private String message;
    private int status;
    private TemaDto result;
}
