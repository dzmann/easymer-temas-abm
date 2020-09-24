package com.easymer.client.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class TemaMultipleOpcionDto extends AbstractTemaDto {
    private String correcta;
    private List<OpcionDto> opciones;
}
