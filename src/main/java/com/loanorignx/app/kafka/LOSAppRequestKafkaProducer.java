package com.loanorignx.app.kafka;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Mono;
import reactor.kafka.sender.KafkaSender;
import reactor.kafka.sender.SenderRecord;

@Log4j2
@Configuration
@RequiredArgsConstructor
public class LOSAppRequestKafkaProducer {

    private final KafkaSender<String, String> kafkaSender;

    @Value("${topic.los.application-request-updates}")
    private final String TOPIC_LOS_APPLICATION_REQUEST = "los-application-request-updates";

    public Mono<Void> publishMessage(String msgJSON, String id){


        return kafkaSender.send(Mono.fromCallable(() -> msgJSON)
                                    .map(jsonPayload -> SenderRecord.create(TOPIC_LOS_APPLICATION_REQUEST, null, null, id, jsonPayload, null)))
                          .doOnError(log::info)
                          .then();
    }

}