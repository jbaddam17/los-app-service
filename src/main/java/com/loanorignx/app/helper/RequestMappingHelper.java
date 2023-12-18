package com.loanorignx.app.helper;

import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.Map;

@Component
public class RequestMappingHelper {

    public Mono<Map<String , Object>> mapRequest(Mono<Map<String , Object>> requestMapMono, String schemaDefinition) {

        return null;
    }
}
