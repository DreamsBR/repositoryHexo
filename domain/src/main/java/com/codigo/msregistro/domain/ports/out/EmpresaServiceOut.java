package com.codigo.msregistro.domain.ports.out;

import com.codigo.msregistro.domain.aggregates.dto.EmpresaDTO;
import com.codigo.msregistro.domain.aggregates.request.RequestEmpresa;

import java.util.List;
import java.util.Optional;

public interface EmpresaServiceOut {

    EmpresaDTO save (RequestEmpresa empresa);

    List<EmpresaDTO> getAll();

    EmpresaDTO deleteById (Long id);

    EmpresaDTO updateEmpresa (Long id, RequestEmpresa empresa);

    Optional<EmpresaDTO > obtenerPersonaOut (Long id);

}
