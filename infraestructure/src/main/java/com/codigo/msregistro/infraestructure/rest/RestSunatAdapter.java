package com.codigo.msregistro.infraestructure.rest;

import com.codigo.msregistro.domain.aggregates.response.ResponseSunat;
import com.codigo.msregistro.infraestructure.rest.client.ClienteSunat;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RestSunatAdapter {

    private final ClienteSunat clienteSunat;

    @Value("${token.api}")
    private String tokenApi;

    ResponseSunat getInfoSunat(String numDoc){
        String authToken = "Bearer "+ tokenApi;
        ResponseSunat responseClient = clienteSunat.getInfoSunat(numDoc, authToken);
        return responseClient;

    }

}
