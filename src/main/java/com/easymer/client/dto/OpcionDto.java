package com.easymer.client.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class OpcionDto {
    private String id;
    private String descripcion;
    private String imagen;

    @Override
    public String toString() {
        return "ID = " + id + "\n" +
                "DESCRIPCION = " + descripcion + "\n" +
                "---------------";
    }
}
