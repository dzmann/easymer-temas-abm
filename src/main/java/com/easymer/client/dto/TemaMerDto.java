package com.easymer.client.dto;


import com.easymer.client.rest.CustomDeserializer;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonRawValue;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
@Setter
public class TemaMerDto extends TemaDto {

    @JsonDeserialize(using = CustomDeserializer.class)
    private String merCorrecto;
    @JsonDeserialize(using = CustomDeserializer.class)
    private String merIncompleto;
    private String tipoMer;

}
