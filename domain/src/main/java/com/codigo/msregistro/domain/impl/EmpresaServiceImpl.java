package com.codigo.msregistro.domain.impl;

import com.codigo.msregistro.domain.aggregates.dto.EmpresaDTO;
import com.codigo.msregistro.domain.ports.in.EmpresaServiceIn;
import com.codigo.msregistro.domain.ports.out.EmpresaServiceOut;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class EmpresaServiceImpl implements EmpresaServiceIn {

    private final EmpresaServiceOut empresaServiceOut;

    @Override
    public EmpresaDTO saveEmpresa(EmpresaDTO empresaDTO) {
        return empresaServiceOut.save(empresaDTO);
    }

    @Override
    public List<EmpresaDTO> getAll() {
        return empresaServiceOut.getAll();
    }

    @Override
    public Optional<EmpresaDTO> findById(Long id) {
        return empresaServiceOut.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        empresaServiceOut.deleteById(id);
    }

    @Override
    public EmpresaDTO updateEmpresa(Long id, EmpresaDTO empresaDTO) {
        return empresaServiceOut.updateEmpresa(id, empresaDTO);
    }
}
