package com.codigo.msregistro.infraestructure.adapters;


import com.codigo.msregistro.domain.aggregates.constants.Constants;
import com.codigo.msregistro.domain.aggregates.dto.EmpresaDTO;
import com.codigo.msregistro.domain.aggregates.dto.PersonaDTO;
import com.codigo.msregistro.domain.aggregates.request.RequestEmpresa;
import com.codigo.msregistro.domain.aggregates.request.RequestPersona;
import com.codigo.msregistro.domain.aggregates.response.ResponseReniec;
import com.codigo.msregistro.domain.aggregates.response.ResponseSunat;
import com.codigo.msregistro.domain.ports.out.EmpresaServiceOut;
import com.codigo.msregistro.infraestructure.entity.EmpresaEntity;
import com.codigo.msregistro.infraestructure.entity.PersonaEntity;
import com.codigo.msregistro.infraestructure.entity.TipoDocumentoEntity;
import com.codigo.msregistro.infraestructure.mapper.EmpresaMapper;
import com.codigo.msregistro.infraestructure.repository.EmpresaRepository;
import com.codigo.msregistro.infraestructure.repository.TipoDocumentoRepository;
import com.codigo.msregistro.infraestructure.rest.client.ClienteSunat;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class EmpresaAdapter implements EmpresaServiceOut {

    private final EmpresaRepository empresaRepository;

    private final ClienteSunat clienteSunat;

    private final EmpresaMapper empresaMapper;

    private final TipoDocumentoRepository tipoDocumentoRepository;

    @Value("${token.api}")
    private String authToken;

    @Override
    public EmpresaDTO save(RequestEmpresa empresa) {
        ResponseSunat datosReniec = getExecutionSunat(empresa.getNumDoc());
        empresaRepository.save(getEntity(datosReniec, empresa));
        return empresaMapper.mapToDTO(getEntity(datosReniec, empresa));    }

    @Override
    public List<EmpresaDTO> getAll() {
        List<EmpresaDTO> personaDTOList = new ArrayList<>();
        List<EmpresaEntity> entities = empresaRepository.findAll();
        for(EmpresaEntity empresas : entities){
            EmpresaDTO empresaDto = empresaMapper.mapToDTO(empresas);
            personaDTOList.add(empresaDto);
        }
        return personaDTOList;
    }

    @Override
    public EmpresaDTO deleteById(Long id) {
        boolean existe = empresaRepository.existsById(id);

        if(existe){
            Optional<EmpresaEntity> entity = empresaRepository.findById(id);
            entity.get().setEstadoBol(0);
            entity.get().setUsuaDelet(Constants.AUDIT_ADMIN);
            entity.get().setDateDelet(getTimestamp());
            empresaRepository.save(entity.get());
            return empresaMapper.mapToDTO(entity.get());
        }
        return null;
    }


    @Override
    public EmpresaDTO updateEmpresa(Long id, RequestEmpresa requestEmpresa) {
        boolean existe = empresaRepository.existsById(id);
        if(existe){
            Optional<EmpresaEntity> entity = empresaRepository.findById(id);
            ResponseSunat response = getExecutionSunat(requestEmpresa.getNumDoc());
            empresaRepository.save(getEntityUpdate(response,entity.get()));
            return empresaMapper.mapToDTO(getEntityUpdate(response,entity.get()));
        }
        return null;
    }

    @Override
    public Optional<EmpresaDTO> obtenerPersonaOut(Long id) {
        return Optional.ofNullable(empresaMapper.mapToDTO(empresaRepository.findById(id).get()));
    }

    private Timestamp getTimestamp(){
        long currentTime = System.currentTimeMillis();
        Timestamp timestamp = new Timestamp(currentTime);
        return timestamp;
    }


    /*Helpers*/

    public ResponseSunat getExecutionSunat(String numero){
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
        //empresaActualizar.setTipoDocumento(sunat.getTipoDocumento());
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
