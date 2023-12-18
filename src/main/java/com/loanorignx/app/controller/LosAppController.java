package com.loanorignx.app.controller;


import com.loanorignx.app.service.LOSAppService;
import com.loanorignx.app.util.Endpoints;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping(Endpoints.ROOT)
public class LosAppController {

    private final LOSAppService losAppService;

    @PostMapping(path = Endpoints.LOS_APP_SUBMIT, consumes = {MediaType.APPLICATION_NDJSON_VALUE, MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_NDJSON_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public Mono<Void> submitRequest(@RequestBody Mono<Map<String, Object>> losAppRequestMono, @PathVariable("vendor-id") Integer vendorId, @PathVariable("config-id") Integer configId) {
        return losAppService.submitRequest(losAppRequestMono);
    }

    @PatchMapping(path = Endpoints.LOS_APP_UPDATE, consumes = {MediaType.APPLICATION_NDJSON_VALUE, MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_NDJSON_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public Mono<Void> updateRequest(@RequestBody Mono<Map<String, Object>> losAppRequestMono, @PathVariable("vendor-id") Integer vendorId, @PathVariable("config-id") Integer configId) {
        return losAppService.updateRequest(losAppRequestMono);
    }
}
