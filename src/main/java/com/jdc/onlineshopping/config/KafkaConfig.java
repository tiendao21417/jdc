package com.jdc.onlineshopping.config;

/**
 * @author tiendao on 17/07/2021
 */
// @EnableKafka
// @Configuration
// @EnableRetry
public class KafkaConfig {

//    @Value("${jdc.kafka.bootstrap-server:localhost:9092}")
//    private String bootStrapServers;
//
//    @Value("${jdc.kafka.security.protocol:SSL}")
//    private String protocol;
//
//    @Value("${jdc.kafka.security.ssl.keystore-location:}")
//    private String keyStoreLocation;
//
//    @Value("${jdc.kafka.security.ssl.keystore-password:}")
//    private String keyStorePassword;
//
//    @Value("${jdc.kafka.security.ssl.key-store-type:}")
//    private String keyStoreType;
//
//    @Value("${onedriver.kafka.security.ssl.truststore-location:}")
//    private String trustStoreLocaltion;
//
//    @Value("${jdc.kafka.security.ssl.truststore-password:}")
//    private String trustStorePassword;
//
//    @Value("${integration.kafka.producer.retries}")
//    private int producerRetries;
//
//    @Bean
//    public ProducerFactory<String, String> producerFactory() {
//        Map<String, Object> props = getConfigurationMap();
//        return new DefaultKafkaProducerFactory<>(props);
//    }
//
//    @Bean
//    public KafkaTemplate<String, String> kafkaTemplate() {
//        return new KafkaTemplate<>(producerFactory());
//    }
//
//    @Bean
//    public ConsumerFactory<String, String> consumerFactory() {
//        Map<String, Object> props = getConfigurationMap();
//        return new DefaultKafkaConsumerFactory<>(props);
//    }
//
//    @Bean
//    public ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactory() {
//        ConcurrentKafkaListenerContainerFactory<String, String> factory =
//                new ConcurrentKafkaListenerContainerFactory<>();
//        factory.setConsumerFactory(consumerFactory());
//        return factory;
//    }
//
//    private Map<String, Object> getConfigurationMap() {
//        Map<String, Object> props = new HashMap<>();
//        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootStrapServers);
//        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
//        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
//        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
//        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
//        props.put(ProducerConfig.RETRIES_CONFIG, producerRetries);
//        props.put("security.protocol", this.protocol);
//        props.put("ssl.truststore.location", this.trustStoreLocaltion);
//        props.put("ssl.truststore.password", this.trustStorePassword);
//        props.put("ssl.keystore.location", this.keyStoreLocation);
//        props.put("ssl.keystore.password", this.keyStorePassword);
//        props.put("ssl.truststore.type", this.keyStoreType);
//        props.put("ssl.keystore.type", this.keyStoreType);
//        return props;
////    }
}
