package se.astrom.complexjava.messaging.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Configuration
public class JMSConfig {

    public static final String QUEUE_NAME = "m365licenses-queue";

    @Bean
    public MessageConverter messageConverter() {
        var converter = new MappingJackson2MessageConverter();
        converter.setTargetType(MessageType.TEXT);
        converter.setTypeIdPropertyName("_type");

        ObjectMapper objectMapper = new ObjectMapper();
        JavaTimeModule timeModule = new JavaTimeModule();

        timeModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        objectMapper.registerModule(timeModule);
        converter.setObjectMapper(objectMapper);

        return converter;
    }
}
