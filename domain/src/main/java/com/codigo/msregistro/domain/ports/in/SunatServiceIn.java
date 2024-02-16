package com.codigo.msregistro.domain.ports.in;

import com.codigo.msregistro.domain.aggregates.response.ResponseReniec;

public interface SunatServiceIn {
    ResponseReniec getInfoIn(String numero);
}
