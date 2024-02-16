package com.codigo.msregistro.domain.impl;

import com.codigo.msregistro.domain.aggregates.response.ResponseReniec;
import com.codigo.msregistro.domain.ports.in.ReniecServiceIn;
import com.codigo.msregistro.domain.ports.in.SunatServiceIn;
import com.codigo.msregistro.domain.ports.out.RestReniecOut;
import com.codigo.msregistro.domain.ports.out.SunatServiceOut;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SunatServiceImpl implements SunatServiceIn {

    private final SunatServiceOut sunat;

    @Override
    public ResponseReniec getInfoIn(String numero) {
        return sunat.getInfoOut(numero);
    }
}
