package com.easymer.client.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AbstractTemaDto {

    private String id;
    private String titulo;
    private String descripcion;
    private String contenido;
    private String tipo;
    private int orden;
}
