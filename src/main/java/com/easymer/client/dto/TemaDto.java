package com.easymer.client.dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
@Setter
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "tipo",
        visible = true)
@JsonSubTypes({
        @JsonSubTypes.Type(value = TemaTeoricoDto.class, name = "TEORICO"),
        @JsonSubTypes.Type(value = TemaMultipleOpcionDto.class, name = "MULTIPLE_OPCION"),
        @JsonSubTypes.Type(value = TemaMerDto.class, name = "MER")
})
public class TemaDto {

    private String id;
    private String titulo;
    private String descripcion;
    private String contenido;
    private String tipo;
    private int orden;
}
