package com.easymer.client.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class AbstractTemaDto implements Serializable {

    private String id;
    private String titulo;
    private String descripcion;
    private String contenido;
    private String tipo;
    private int orden;
}
