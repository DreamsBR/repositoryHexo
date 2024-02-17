package com.codigo.msregistro.domain.ports.in;

import com.codigo.msregistro.domain.aggregates.dto.EmpresaDTO;
import com.codigo.msregistro.domain.aggregates.request.RequestEmpresa;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface EmpresaServiceIn {

    EmpresaDTO saveEmpresa (RequestEmpresa empresa);

    List<EmpresaDTO> getAll();


    EmpresaDTO deleteById (Long id);

    EmpresaDTO updateEmpresa (Long id, RequestEmpresa empresa);

    Optional<EmpresaDTO > obtenerPersonaOut (Long id);


}
