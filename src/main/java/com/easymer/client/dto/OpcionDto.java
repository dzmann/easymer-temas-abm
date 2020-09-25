package com.easymer.client.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class OpcionDto {
    private String tag;
    private String descripcion;

    @Override
    public String toString() {
        return "TAG = " + tag + "\n" +
                "DESCRIPCION = " + descripcion + "\n" +
                "---------------";
    }
}
