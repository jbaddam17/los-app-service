package com.loanorignx.app.service;

import reactor.core.publisher.Mono;

import java.util.Map;

public interface LOSAppService {
    Mono<Void> submitRequest(Mono<Map<String, Object>> requestMapMono);
    Mono<Void> updateRequest(Mono<Map<String, Object>> requestMapMono);
}
