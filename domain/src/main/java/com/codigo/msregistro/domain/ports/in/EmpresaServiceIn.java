package com.codigo.msregistro.domain.ports.in;

import com.codigo.msregistro.domain.aggregates.dto.EmpresaDTO;

import java.util.List;
import java.util.Optional;

public interface EmpresaServiceIn {

    EmpresaDTO saveEmpresa (EmpresaDTO empresaDTO);

    List<EmpresaDTO> getAll();

    Optional<EmpresaDTO> findById (Long id);

    void deleteById (Long id);

    EmpresaDTO updateEmpresa (Long id, EmpresaDTO empresaDTO);

}
