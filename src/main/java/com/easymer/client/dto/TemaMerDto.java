package com.easymer.client.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class TemaMerDto extends AbstractTemaDto implements Serializable {

    private String merCorrecto;
    private String merIncompleto;
    private String tipoMer;

}
