package com.loanorignx.app.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.loanorignx.app.entity.appreq.LOSAppRequest;
import com.loanorignx.app.kafka.LOSAppRequestKafkaProducer;
import com.loanorignx.app.repository.LOSAppRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.Map;

@Log4j2
@Service
@RequiredArgsConstructor
public class LOSAppServiceImpl implements LOSAppService {

    private final LOSAppRepository losAppRepository;
    private final LOSAppRequestKafkaProducer losAppRequestKafkaProducer;

    @Override
    public Mono<Void> submitRequest(Mono<Map<String, Object>> requestMapMono) {

        return requestMapMono.flatMap(requestMap -> losAppRepository.save(new LOSAppRequest(null, requestMap))
                .flatMap(savedLOSAppRequest -> {
                    return Mono.fromCallable(() -> savedLOSAppRequest).publishOn(Schedulers.boundedElastic()).doAfterTerminate(() -> {
                        Mono.fromCallable(() -> new ObjectMapper().writeValueAsString(savedLOSAppRequest))
                                .flatMap(jsonPayload -> losAppRequestKafkaProducer.publishMessage(jsonPayload, savedLOSAppRequest.getId())).subscribe();
                    });
                }).then());
    }

    @Override
    public Mono<Void> updateRequest(Mono<Map<String, Object>> requestMapMono) {

        return requestMapMono.flatMap(requestMap -> {
            return losAppRepository.findByLosApplicationId(Long.parseLong(String.valueOf(requestMap.get("losApplicationId"))))
                    .flatMap(losAppRequest -> {
                        return losAppRepository.save(new LOSAppRequest(losAppRequest.getId(), requestMap))
                                .flatMap(updatedLOSRequestAppDTO -> {
                                    return Mono.fromCallable(() -> updatedLOSRequestAppDTO)
                                            .publishOn(Schedulers.boundedElastic())
                                            .doAfterTerminate(() -> {
                                                Mono.fromCallable(() -> new ObjectMapper().writeValueAsString(updatedLOSRequestAppDTO))
                                                        .flatMap(jsonPayload -> losAppRequestKafkaProducer.publishMessage(jsonPayload, updatedLOSRequestAppDTO.getId())).subscribe();
                                            });
                                }).then();
                    });
        });
    }
}
