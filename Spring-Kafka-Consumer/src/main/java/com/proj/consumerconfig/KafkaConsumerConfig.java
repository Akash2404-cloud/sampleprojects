package com.proj.consumerconfig;

import java.util.HashMap;
import java.util.Map;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import com.proj.entity.User;


@Configuration
@EnableKafka
public class KafkaConsumerConfig {

	@Bean
	public ConsumerFactory<String, User> consumerfactory(){
		Map<String , Object> config = new HashMap<String,Object>();
		config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,"localhost:9092");
		config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,StringDeserializer.class );
		config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,JsonDeserializer.class );
		config.put(ConsumerConfig.GROUP_ID_CONFIG,"userconsumer");
		return new DefaultKafkaConsumerFactory<>(config, new StringDeserializer() , new JsonDeserializer<>(User.class) );
	}
	
	@Bean
	public ConcurrentKafkaListenerContainerFactory<String,User> kafkalistener(){
		ConcurrentKafkaListenerContainerFactory<String, User> listener = new ConcurrentKafkaListenerContainerFactory<String , User>();
		listener.setConsumerFactory(consumerfactory());
		return listener;
	}
}
