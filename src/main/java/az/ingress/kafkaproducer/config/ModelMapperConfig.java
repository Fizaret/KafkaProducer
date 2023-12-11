package az.ingress.kafkaproducer.config;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.converter.StringJsonMessageConverter;

import java.util.HashMap;
import java.util.Map;

public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }
    @Value("${spring.kafka.producer.bootstrap-servers}")

    private String bootstrapServers;

    @Bean
    Map<String,Object> producerConfigs(){
        Map<String ,Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,bootstrapServers);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerialize.class);
        return props;
    }
    @Bean
    public ProducerFactory<String ,Object> objectProducerFactory(){
        return new DefaultKafkaProducerFactory<>(producerConfigs());
    }
    @Bean
    public KafkaTemplate<String,Object> objectKafkaTemplate(){
        return new KafkaTemplate<>(objectProducerFactory());
    }
    @Bean
    ProducerFactory<String,String> producerFactory(){
        return new DefaultKafkaProducerFactory<>(producerConfigs());
    }

    @Bean
    KafkaTemplate<String ,String> kafkaTemplate(){
        KafkaTemplate<String ,String> kafkaTemplate = new KafkaTemplate<>(producerFactory());
        kafkaTemplate.setMessageConverter(new StringJsonMessageConverter());
        return kafkaTemplate;
    }
}
