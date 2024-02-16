package com.codigo.msregistro.infraestructure.adapters;


import com.codigo.msregistro.domain.aggregates.constants.Constants;
import com.codigo.msregistro.domain.aggregates.dto.EmpresaDTO;
import com.codigo.msregistro.domain.aggregates.request.RequestEmpresa;
import com.codigo.msregistro.domain.aggregates.request.RequestPersona;
import com.codigo.msregistro.domain.aggregates.response.ResponseReniec;
import com.codigo.msregistro.domain.aggregates.response.ResponseSunat;
import com.codigo.msregistro.infraestructure.entity.EmpresaEntity;
import com.codigo.msregistro.infraestructure.entity.TipoDocumentoEntity;
import com.codigo.msregistro.infraestructure.mapper.EmpresaMapper;
import com.codigo.msregistro.infraestructure.repository.EmpresaRepository;
import com.codigo.msregistro.infraestructure.repository.TipoDocumentoRepository;
import com.codigo.msregistro.infraestructure.rest.client.ClienteSunat;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class EmpresaAdapter {

    private final EmpresaRepository empresaRepository;

    private final ClienteSunat clienteSunat;

    private final EmpresaMapper empresaMapper;

    private final TipoDocumentoRepository tipoDocumentoRepository;

    @Value("${token.api}")
    private String authToken;

    public EmpresaDTO createEmpresa(RequestEmpresa requesEmpresa) {
        return null;
    }


    /*Helpers*/

    public ResponseSunat getInfoSunat(String numero){
        String auth = "Bearer " + authToken;
        ResponseSunat sunatResponse =  clienteSunat.getInfoSunat(numero, auth);
        return sunatResponse;
    }

    public EmpresaEntity getEntity(ResponseSunat empresa, RequestEmpresa responseSunat){
        TipoDocumentoEntity tipoDocumento = tipoDocumentoRepository.findByCodTipo(responseSunat.getTipoDoc());
        EmpresaEntity entity = new EmpresaEntity();
        entity.setIdEmpres(empresa.getIdEmpres());
        entity.setNumRuc(empresa.getNumRuc());
        entity.setRazonSocial(empresa.getRazonSocial());
        entity.setTipoDocumento(tipoDocumento);
        entity.setNumeroDocumento(empresa.getNumeroDocumento());
        entity.setEstado(empresa.getEstado());
        entity.setCondicion(empresa.getCondicion());
        entity.setDireccion(empresa.getDireccion());
        entity.setUbigeo(empresa.getUbigeo());
        entity.setViaTipo(empresa.getViaTipo());
        entity.setViaNombre(empresa.getViaNombre());
        entity.setZonaCodigo(empresa.getZonaCodigo());
        entity.setZonaTipo(empresa.getZonaTipo());
        entity.setNumero(empresa.getNumero());
        entity.setInterior(empresa.getInterior());
        entity.setLote(empresa.getLote());
        entity.setDpto(empresa.getDpto());
        entity.setManzana(empresa.getManzana());
        entity.setKilometro(empresa.getKilometro());
        entity.setDistrito(empresa.getDistrito());
        entity.setProvincia(empresa.getProvincia());
        entity.setDepartamento(empresa.getDepartamento());
        entity.setEsAgenteRetencion(empresa.getEsAgenteRetencion());

        return entity;
    }

    private EmpresaEntity getEntityUpdate(ResponseSunat sunat, EmpresaEntity empresaActualizar){
        empresaActualizar.setIdEmpres(sunat.getIdEmpres());
        empresaActualizar.setNumRuc(sunat.getNumRuc());
        empresaActualizar.setRazonSocial(sunat.getRazonSocial());
        empresaActualizar.setTipoDocumento(sunat.getTipoDocumento());
        empresaActualizar.setNumeroDocumento(sunat.getNumeroDocumento());
        empresaActualizar.setEstado(sunat.getEstado());
        empresaActualizar.setCondicion(sunat.getCondicion());
        empresaActualizar.setDireccion(sunat.getDireccion());
        empresaActualizar.setUbigeo(sunat.getUbigeo());
        empresaActualizar.setViaTipo(sunat.getViaTipo());
        empresaActualizar.setViaNombre(sunat.getViaNombre());
        empresaActualizar.setZonaCodigo(sunat.getZonaCodigo());
        empresaActualizar.setZonaTipo(sunat.getZonaTipo());
        empresaActualizar.setNumero(sunat.getNumero());
        empresaActualizar.setInterior(sunat.getInterior());
        empresaActualizar.setLote(sunat.getLote());
        empresaActualizar.setDpto(sunat.getDpto());
        empresaActualizar.setManzana(sunat.getManzana());
        empresaActualizar.setKilometro(sunat.getKilometro());
        empresaActualizar.setDistrito(sunat.getDistrito());
        empresaActualizar.setProvincia(sunat.getProvincia());
        empresaActualizar.setDepartamento(sunat.getDepartamento());
        empresaActualizar.setEsAgenteRetencion(sunat.getEsAgenteRetencion());
        empresaActualizar.setUsuaModif(Constants.AUDIT_ADMIN);
        return empresaActualizar;

    }



}
