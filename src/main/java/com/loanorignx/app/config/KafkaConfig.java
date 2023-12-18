package com.loanorignx.app.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.kafka.receiver.KafkaReceiver;
import reactor.kafka.receiver.ReceiverOptions;
import reactor.kafka.sender.KafkaSender;
import reactor.kafka.sender.SenderOptions;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConfig {

    @Value("${topic.los.application-request-updates}")
    private String TOPIC_LOS_APPLICATION_REQUEST = "los-application-request-updates";

    @Value("${spring.kafka.bootstrap-servers}")
    private String kafkaServerURL;

    @Bean
    public NewTopic appUpdatesTopic() {
        return new NewTopic(TOPIC_LOS_APPLICATION_REQUEST, 1, (short) 1);
    }

    @Bean
    KafkaSender<String, String> createKafkaSender() {
        Map<String, Object> producerProps = new HashMap<>();
        producerProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaServerURL);
        producerProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        producerProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        SenderOptions<String, String> senderOptions = SenderOptions.<String, String>create(producerProps).maxInFlight(1024);
        return KafkaSender.create(senderOptions);
    }
    @Bean
    KafkaReceiver<String, String> createKafkaReceiver() {
        Map<String, Object> consumerProps = new HashMap<>();
        consumerProps.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaServerURL);
        consumerProps.put(ConsumerConfig.GROUP_ID_CONFIG, "sample-group");
        consumerProps.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        consumerProps.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        ReceiverOptions<String, String> receiverOptions = ReceiverOptions.<String, String> create(consumerProps).subscription(Collections.singleton(TOPIC_LOS_APPLICATION_REQUEST));
        return KafkaReceiver.create(receiverOptions);
    }
}
