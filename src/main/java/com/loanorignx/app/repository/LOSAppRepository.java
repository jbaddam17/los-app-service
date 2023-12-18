package com.loanorignx.app.repository;

import com.loanorignx.app.entity.appreq.LOSAppRequest;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface LOSAppRepository extends ReactiveMongoRepository<LOSAppRequest, Long> {

    @Query(value = "{'requestData.losApplicationId': ?0}")
    Mono<LOSAppRequest> findByLosApplicationId(Long losApplicationId);
}