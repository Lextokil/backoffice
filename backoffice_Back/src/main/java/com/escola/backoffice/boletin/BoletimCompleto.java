package com.escola.backoffice.boletin;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BoletimCompleto {


    private String materias;

    private Double bim1;

    private Double bim2;

    private Double bim3;

    private Double bim4;

    private Double media;
}
