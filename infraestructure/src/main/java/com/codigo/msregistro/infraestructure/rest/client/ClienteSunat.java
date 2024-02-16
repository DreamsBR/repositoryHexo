package com.codigo.msregistro.infraestructure.rest.client;

import com.codigo.msregistro.domain.aggregates.response.ResponseSunat;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "cliente-sunat", url = "https://api.apis.net.pe/v2/sunat/ruc/")
public interface ClienteSunat {

    ResponseSunat getInfoSunat(
            @RequestParam("numero") String numero,
            @RequestHeader("Authorization") String authorization);
}
