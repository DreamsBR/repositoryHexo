package com.codigo.msregistro.domain.impl;

import com.codigo.msregistro.domain.aggregates.constants.Constants;
import com.codigo.msregistro.domain.aggregates.dto.EmpresaDTO;
import com.codigo.msregistro.domain.aggregates.request.RequestEmpresa;
import com.codigo.msregistro.domain.ports.in.EmpresaServiceIn;
import com.codigo.msregistro.domain.ports.out.EmpresaServiceOut;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmpresaServiceImpl implements EmpresaServiceIn {

    private final EmpresaServiceOut empresaServiceOut;

    @Override
    public EmpresaDTO saveEmpresa(RequestEmpresa empresa) {
        return empresaServiceOut.save(empresa);
    }

    @Override
    public List<EmpresaDTO> getAll() {
        return empresaServiceOut.getAll();
    }

    @Override
    public EmpresaDTO deleteById(Long id) {
        return empresaServiceOut.deleteById(id);
    }


    @Override
    public EmpresaDTO updateEmpresa(Long id, RequestEmpresa empresa) {
        return empresaServiceOut.updateEmpresa(id, empresa);
    }
}
